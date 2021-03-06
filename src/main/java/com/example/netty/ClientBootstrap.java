package com.example.netty;

import java.util.concurrent.ExecutionException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.netty.config.AppConfig;
import com.example.netty.core.endpoint.TcpClient;
import com.example.netty.core.j8583.MessageFactory;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.IsoValue;

public class ClientBootstrap {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
		
		TcpClient tcpClient = appContext.getBean(TcpClient.class);
		tcpClient.start();
		
		MessageFactory msgFacotry = appContext.getBean(MessageFactory.class);
		
		int msgNum = 3;
		
		for (int i = 0; i < msgNum; i++) {
			IsoMessage message = msgFacotry.newMessage(0x200);
			
			IsoValue<String> processingCode = new IsoValue<String>(IsoType.ALPHA, "010000", 6);
			message.setField(3, processingCode);
			
			tcpClient.send(message);
			
			Thread.sleep(2000);
		}
	}
}
