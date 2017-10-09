/**
 * 
 */
package com.sdt.logic;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import com.sdt.queue.LogicQueue;

/**
 * @author LQ
 *
 */
public class LogicModule implements Runnable{
	private BlockingQueue<String> processQueue = null;
	
	public LogicModule(){
		LogicQueue logicQueue = new LogicQueue();
		processQueue = LogicQueue.processQueue;
	}
	
	public Queue<String> getQueue(){
		return processQueue;
	}
	public void run() {
		System.out.println("logic start");
		while(true){
			try {
				String message = processQueue.take();
				System.out.println("============");
				System.out.println(message);
				System.out.println("============");
				
			} catch (Exception e) {
				// TODO: handle exception
			}
				
			
		}
		
		
	}
	
}
