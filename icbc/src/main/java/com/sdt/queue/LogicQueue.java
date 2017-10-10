/**
 * 
 */
package com.sdt.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author LQ
 *
 */
public class LogicQueue implements MessageQueue{

	public static BlockingQueue<String> processQueue = new LinkedBlockingQueue<String>();
	
	
	public void sendMessage(String message){
		try {
			processQueue.put(message);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getMessage(){
		String message = null;
		try {
			message = processQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}
