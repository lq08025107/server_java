/**
 * 
 */
package com.sdt.queue;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * @author liuqiang
 *
 */
public class Rabbitmq extends Endpoint implements MessageQueue, com.rabbitmq.client.Consumer {
	private String message = null;
	public Rabbitmq(String endpointName) throws IOException {
		super(endpointName);
	}

	public void sendMessage(String message) {
		try {
			channel.basicPublish("", endPointName, null, message.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getMessage() {
		try {
			channel.basicConsume(endPointName, true, this);
		} catch (IOException e) {
			System.out.println(e);
		}
		return message;
	}

	public void handleConsumeOk(String consumerTag) {
		// TODO Auto-generated method stub
		
	}

	public void handleCancelOk(String consumerTag) {
		// TODO Auto-generated method stub
		
	}

	public void handleCancel(String consumerTag) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body) throws IOException {
		// TODO Auto-generated method stub
		message = new String(body, "UTF-8");
		System.out.println(message);
	}

	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
		// TODO Auto-generated method stub
		
	}

	public void handleRecoverOk(String consumerTag) {
		// TODO Auto-generated method stub
		
	}

}
