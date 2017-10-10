/**
 * 
 */
package com.sdt.queue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import sun.tools.jconsole.ConnectDialog;

/**
 * @author liuqiang
 *
 */
public abstract class Endpoint {
	protected Channel channel;
	protected Connection connection;
	protected String endPointName;
	
	public Endpoint(String endpointName) throws IOException{
		this.endPointName = endpointName;
		
		ConnectionFactory factory = new ConnectionFactory();
		
		factory.setHost("localhost");
		
		try {
			connection = factory.newConnection();
		} catch (TimeoutException e) {
			System.out.println(e);
			connection = null;
		}
		
		channel = connection.createChannel();
		channel.queueDeclare(endpointName,false,false,false,null);
	}
	public void close() throws IOException{
		try {
			this.channel.close();
		} catch (TimeoutException e) {
			System.out.println(e);
		}
		this.connection.close();
	}
}
