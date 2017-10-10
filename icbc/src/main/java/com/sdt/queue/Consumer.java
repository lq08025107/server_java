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
public class Consumer extends Endpoint implements  com.rabbitmq.client.Consumer{

	public Consumer(String endpointName) throws IOException {
		super(endpointName);
	}
	
	public void getMessage(){
		try {
			channel.basicConsume(endPointName, true, this);
		} catch (IOException e) {
			System.out.println(e);
		}
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
		String message = new String(body, "UTF-8");
		System.out.println(message);
	}

	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
		// TODO Auto-generated method stub
		
	}

	public void handleRecoverOk(String consumerTag) {
		// TODO Auto-generated method stub
		
	}

}
