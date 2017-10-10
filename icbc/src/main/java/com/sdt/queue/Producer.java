/**
 * 
 */
package com.sdt.queue;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

/**
 * @author liuqiang
 *
 */
public class Producer extends Endpoint{

	public Producer(String endpointName) throws IOException {
		super(endpointName);
	}
	
	public void sendMessage(String message) throws IOException{
		channel.basicPublish("", endPointName, null, message.getBytes());
	}
	
}
