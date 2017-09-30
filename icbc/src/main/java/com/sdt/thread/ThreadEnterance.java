/**
 * 
 */
package com.sdt.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.plaf.basic.BasicTabbedPaneUI.TabSelectionHandler;

import com.sdt.http.HttpServer;

import ch.qos.logback.core.joran.event.StartEvent;

/**
 * @author liuqiang
 *
 */
public class ThreadEnterance {
	
	public void start(){
		ExecutorService executor = Executors.newCachedThreadPool();
		HttpServer httpServer = new HttpServer(8080);
		Task task = new Task();
		executor.execute(httpServer);
		executor.execute(task);
	}
	public static void main(String[] args){
		new ThreadEnterance().start();
	}
}
class Task implements Runnable{

	public void run() {
		while(true){
			System.out.println("heiheihei");
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
		
	}
	
}