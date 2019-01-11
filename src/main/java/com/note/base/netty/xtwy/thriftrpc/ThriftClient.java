package com.note.base.netty.xtwy.thriftrpc;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.hzins.thrift.demo.HelloWorldService;

public class ThriftClient {
	
	public static void startClient(int port) throws Exception{
		TTransport transport = new TSocket("localhost", port);
	    TProtocol protocol = new TBinaryProtocol(transport);
	    HelloWorldService.Client client = new HelloWorldService.Client(protocol);
	    transport.open();
	    String result = client.sayHello("zhangsan");
	    System.out.println(result);
		
	}

	public static void main(String[] args) throws Exception {
		startClient(8080);
	}
}
