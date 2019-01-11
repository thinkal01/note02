package com.note.common.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

@Controller
@RequestMapping("apiStudy")
@SessionAttributes(value = {"user"}, types = {String.class})
public class ApiStudyController {

    private static final String SUCCESS = "apistudy";
    private static final String RESULT_KEY = "result";

    /**
     * @SessionAttributes 将数据存储到session中，达到多个请求数据共享的目的。
     * 只能修饰在类上的注解
     * 通过属性名value，指定需要放到会话中的属性
     * 通过模型属性的对象类型types，指定哪些模型属性需要放到会话中
     */

    /**
     * 常用方法：ModelAndView 方法的返回值设置为 ModelAndView 类型。
     * ModelAndView 顾名思义，是包含视图和模型信息的类型。
     * 其数据存放在 request 域对象中.
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        String viewName = SUCCESS; // 需要返回的视图名
        String apiDocs = "ModelAndView(常用方法) : 之前学习的方法返回值是字符串，数据是通过Map返回到前端。现在可以通过ModelAndView类型直接完成。";
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject(RESULT_KEY, apiDocs); // 添加数据到model中
        return modelAndView;
    }

    /**
     * SpringMVC 确定目标方法 POJO 类型入参的过程
     * 第一步: 确定一个 key
     * 若方法形如 "testModelAttribute(User user)" ， 则key为 user(POJO 类名第一个字母小写)
     * 若方法形如"testModelAttribute(@ModelAttribute("userObj") User user)" ，则key为 userObj
     * 第二步: 在 implicitModel 中查找 key 对应的对象
     * 若 implicitModel 存在, 则作为入参传入
     * 若 implicitModel 中不存在, 则检查当前Handler 是否使用 @SessionAttributes 注解
     * 若使用了该注解, 且 @SessionAttributes 注解的 value 属性值中包含了 key, 则会从 HttpSession 中来获取 key 所对应的 value 值, 若存在则直接传入到目标方法的入参中. 若不存在则将抛出异常.
     * 若 Handler 没有标识 @SessionAttributes 注解或 @SessionAttributes 注解的 value 值中不包含 key, 则通过反射来创建 POJO 类型的参数, 传入为目标方法的参数
     * implicitModel？  SpringMVC 会把 key 和 POJO 类型的对象保存到 implicitModel 中, 进而会保存到 request 中.
     */
    @RequestMapping("/testModelAttribute")
    public ModelAndView testModelAttribute(User user) {
        ModelAndView modelAndView = new ModelAndView(SUCCESS);
        modelAndView.addObject(RESULT_KEY, "update : " + user); // 添加数据到model中
        return modelAndView;
    }

    /**
     * 常用方法：@ModelAttribute 修饰方法。 被该注解修饰的方法, 会在每个目标方法执行之前被 SpringMVC 调用
     * 运行流程:
     * 第一步: 在执行 testModelAttribute(User user) 方法前，会先执行被@ModelAttribute 注解修饰的方法 getUser()
     * 第二步: 执行getUser() 后，将执行放入到Map中，其中的key 必须和目标方法User对象的首字母小写user
     * 第三步: SpringMVC 从 Map 中取出 User 对象,然后把对象传入目标方法的参数.
     * <p>
     * 未使用 @ModelAttribute testModelAttribute方法 打印的信息 :
     * update : User [id=1, account=itdragon, password=null, position=null]
     * 使用@ModelAttribute testModelAttribute方法 打印的信息 :
     * update : User [id=1, account=itdragon, password=zhangdeshuai, position=null]
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id,
                        Map<String, Object> map) {
        if (id != null) {
            //模拟从数据库中获取对象
            User user = new User(1, "itdragon", "zhangdeshuai");
            map.put("user", user); // 这里的key 一定是该对象User的首字母小写user
        }
    }

    /**
     * 常用方法：可以使用 Serlvet 原生的 API 作为目标方法的参数 具体支持以下类型
     * <p>
     * HttpServletRequest
     * HttpServletResponse
     * HttpSession
     * InputStream
     * OutputStream
     * Reader
     * Writer
     */
    @RequestMapping("/testServletAPI")
    public void testServletAPI(HttpServletRequest request,
                               HttpServletResponse response, Writer out) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        out.write("Hello Servlet API，和用Servlet一样(0——0) ; <br/>request : " + request + " ;<br/>response : " + response);
    }
    /**
     * 常用知识点：在类或者方法上使用 @RequestMapping 注解
     * 若没有修饰类，则访问路径是: http://ip:port/项目名/方法的@RequestMapping值
     * 若类有修饰类，则访问路径是: http://ip:port/项目名/类的@RequestMapping值/方法的@RequestMapping值
     */

    /**
     * 方法中用map作为参数，可以将数据存储到request作用域中，放回到页面上。
     * 同样用法的有  Model 类型 和  ModelMap 类型
     */
    @RequestMapping("/testMapResult")
    public String testMapResult(Map<String, Object> map, Model model, ModelMap modelMap) {
        String apiDocs = "Map,Model,ModelMap (常用方法) : 在方法中添加Map的参数，可以将数据放到request 作用域中！";
        map.put(RESULT_KEY, apiDocs);
        model.addAttribute("model", "Model");
        modelMap.addAttribute("modelMap", "ModelMap");
        return SUCCESS;
    }

    /**
     * 常用知识点：使用 method 属性来指定请求方式
     * 若用GET方式请求，会提示：HTTP Status 405 - Request method 'GET' not supported 错误信息
     */
    @RequestMapping(value = "/testRequestMethod", method = RequestMethod.POST)
    public String testRequestMethod(Map<String, Object> map) {
        String apiDocs = "RequestMethod (常用方法) : 若设置只有POST请求才能进入，则用GET方式请求，会报405的错误。反之亦然！";
        map.put(RESULT_KEY, apiDocs);
        return SUCCESS;
    }

    /**
     * 常用知识点：使用注解 @PathVariable 映射 URL绑定占位,属于REST风格。
     * 注意两点：
     * 1. 严格用法: @PathVariable("arg") String arg; 前一个arg参数，必须要和占位参数{arg}保持一致。后面一个arg参数可以自定义。
     * 2. 偷懒用法: @PathVariable String arg; 这里的参数名 arg 必须要和占位参数{arg}保持一致，不然会提示400的错误
     */
    @RequestMapping("/testPathVariable/{arg}")
    public String testPathVariable(@PathVariable("arg") String arg, Map<String, Object> map) {
        String apiDocs = "PathVariable (常用方法) : 通过映射 URL绑定占位获取的值是 " + arg;
        map.put(RESULT_KEY, apiDocs);
        return SUCCESS;
    }

    /**
     * 常用知识点：使用注解 @RequestParam 来映射请求参数
     * 该注解有三个参数，
     * value 请求的参数名,
     * required 请求的参数是否必填 ,默认是true,
     * defaultValue 请求的参数默认值.
     * 参数的类型建议是封装数据类型，因为float默认值是0.0 ,若该参数是非必填，则会报错 HTTP Status 500
     */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam("account") String account,
                                   @RequestParam(value = "password", required = true) String password,
                                   @RequestParam(value = "price", required = false, defaultValue = "0.0") float price,
                                   Map<String, Object> map) {
        String apiDocs = "RequestParam (常用方法) : 获取映射请求参数的值有 account : " + account + " password : " + password + " price : " + price;
        map.put(RESULT_KEY, apiDocs);
        return SUCCESS;
    }

    /**
     * 常用知识点：方法参数是POJO对象
     * 前端的请求参数名一定要和POJO对象属性一致。支持级联
     */
    @RequestMapping(value = "/testPojo", method = RequestMethod.POST)
    public String testPojo(User user, Map<String, Object> map) {
        map.put(RESULT_KEY, user);
        return SUCCESS;
    }

    /**
     * 不常用方法：params 和 headers
     *
     * @RequestMapping 注解中，除了常用的value和method外，还有两个较为常用的params和headers
     * 他们可以是请求跟精确，制定那些参数的请求不接受，同时也可以指定那些参数的请求接收。
     * params={param1,param2}
     * param1 表示 请求必须包含名为param1的请求参数
     * !param1 表示 请求不能包含名为param1的请求参数
     * param1!=value1 表示请求包含param1的请求参数，但是其值不能是value1
     */
    @RequestMapping(value = "/testParamsAndHeaders", params = {"itdragon"},
            headers = {"Accept-Language=zh-CN,zh;q=0.8"})
    public String testParamsAndHeaders(Map<String, Object> map) {
        String apiDocs = "params,headers (了解用法) : 这里表示当请求参数中包含了itdragon的时候才能进来";
        map.put(RESULT_KEY, apiDocs);
        return SUCCESS;
    }

    /**
     * 不常用方法：ant风格
     * ?匹文件名中一个字
     * *匹文件名中任意字
     * ** 匹多 层径
     */
    @RequestMapping("/*/testAntUrl")
    public String testAntUrl(Map<String, Object> map) {
        String apiDocs = "Ant风格 (了解用法) : ?匹文件名中一个字 ; *匹文件名中任意字 ; ** 匹多 层径 ";
        map.put(RESULT_KEY, apiDocs);
        return SUCCESS;
    }

    /**
     * 不常用方法：@RequestHeader 注解获取请求头数据。
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept-Language") String al,
                                    Map<String, Object> map) {
        String apiDocs = "@RequestHeader (了解用法) : 获取请求头数据的注解, 如Accept-Language 的值是 : " + al;
        map.put(RESULT_KEY, apiDocs);
        return SUCCESS;
    }

    /**
     * 不常用方法：@CookieValue: 映射一个 Cookie值
     */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId,
                                  Map<String, Object> map) {
        String apiDocs = "@CookieValue(了解用法) : 映射一个 Cookie值的注解, 如JSESSIONID 的Cookie值是 : " + sessionId;
        map.put(RESULT_KEY, apiDocs);
        return SUCCESS;
    }
}
