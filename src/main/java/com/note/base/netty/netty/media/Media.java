package com.note.base.netty.netty.media;

import com.google.protobuf.ByteString;
import com.note.base.netty.netty.pb.protocol.RequestMsgProbuf;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Media {

    public static Map<String, MethodBean> methodBeans = new HashMap<>();

    public static Object execute(Object obj) {
        Object response = null;
        String cmd = "";
        Object parameterObj = null;
        try {
            if (obj instanceof RequestMsgProbuf.RequestMsg) {
                cmd = ((RequestMsgProbuf.RequestMsg) obj).getCmd();
            }/* else if (obj instanceof RequestParam) {
                cmd = ((RequestParam) obj).getCommand();
            } *//*else if (obj instanceof ThriftRequest) {
                cmd = ((ThriftRequest) obj).getCommand();
            }*/

            MethodBean methodBean = methodBeans.get(cmd);
            Method method = methodBean.getMethod();
            Object bean = methodBean.getBean();
            //获取目标方法参数类型
            Class parameterType = method.getParameterTypes()[0];
            if (obj instanceof RequestMsgProbuf.RequestMsg) {
                //目标方法参数类型的所有构造方法
                Constructor[] constructors = parameterType.getDeclaredConstructors();
                Constructor c = null;
                for (Constructor constructor : constructors) {
                    if (constructor.getParameterTypes()[0].getName().equals("boolean")) {
                        c = constructor;
                    }
                }
                if (c != null) {
                    c.setAccessible(true);
                }
                //初始化参数
                parameterObj = c.newInstance(true);
                ByteString requestParam = ((RequestMsgProbuf.RequestMsg) obj).getRequestParam();
                Method parameterMethod = parameterType.getMethod("parseFrom", ByteString.class);
                //对方法参数赋值
                parameterObj = parameterMethod.invoke(parameterObj, requestParam);
            } /*else if (obj instanceof RequestParam) {
                RequestParam requestParam = (RequestParam) obj;
                if (method.getParameterTypes()[0].getName().equalsIgnoreCase("java.lang.String")) {
                    parameterObj = requestParam.getParameter().toString();
                } else {
                    parameterObj = JsonUtils.jsonToBean(requestParam.getParameter().toString(), parameterType);
                }
            }*/ /*else if (obj instanceof ThriftRequest) {
                byte[] requestParam = ((ThriftRequest) obj).getRequestParam();
                Method parameterMethod = parameterType.getMethod("read", TProtocol.class);
                parameterObj = parameterType.newInstance();
                TMemoryBuffer buffer = new TMemoryBuffer(requestParam.length);
                buffer.write(requestParam);
                TProtocol prot = new TBinaryProtocol(buffer);
                parameterMethod.invoke(parameterObj, prot);
            }*/
            response = method.invoke(bean, parameterObj);

            /*if (obj instanceof HttpRequest) {
                String jsonp = JsonUtils.beanToJson(response);
                FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(jsonp.getBytes("UTF-8")));
                httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
                httpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, httpResponse.content().readableBytes());
                httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                return httpResponse;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    /*else if(obj instanceof ThriftRequest)
    {
        //参数类型转换thrift对象
        Method readObjectMethod = parameterType.getMethod("read", org.apache.thrift.protocol.TProtocol.class);
        byte[] thriftParam = ((ThriftRequest) obj).getRequestParam();
        TMemoryBuffer t = new TMemoryBuffer(1024);
        t.write(thriftParam);
        TBinaryProtocol proto = new TBinaryProtocol(t);
        parameterObj = parameterType.newInstance();
        readObjectMethod.invoke(parameterObj, proto);
    }*/

}
