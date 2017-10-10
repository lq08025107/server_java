/**
 * 
 */
package com.sdt.logic;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import com.sdt.queue.LogicQueue;
import com.sdt.queue.MessageQueue;
import com.sdt.queue.Rabbitmq;

/**
 * @author LQ
 *
 */
public class LogicModule implements Runnable{
	//private BlockingQueue<String> processQueue = null;
	private MessageQueue messageQueue = null;
	public LogicModule() throws IOException{
//		LogicQueue logicQueue = new LogicQueue();
//		processQueue = LogicQueue.processQueue;
		messageQueue = new Rabbitmq("queue");
	}
	

	public void run() {
		System.out.println("logic start");
		while(true){
			try {
				String message = messageQueue.getMessage();
				System.out.println("============");
				System.out.println(message);
				System.out.println("============");
				
			} catch (Exception e) {
				// TODO: handle exception
			}
				
			
		}
		
		
	}
	
}
