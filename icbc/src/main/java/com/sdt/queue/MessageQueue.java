/**
 * 
 */
package com.sdt.queue;

/**
 * @author liuqiang
 *
 */
public interface MessageQueue {
	public void sendMessage(String message);
	public String getMessage();
}
