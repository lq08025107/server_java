/**
 * 
 */
package com.sdt.thread;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



import com.sdt.http.HttpServer;
import com.sdt.logic.LogicModule;



/**
 * @author liuqiang
 *
 */
public class ThreadEnterance {
	
	public void start() throws IOException{
		ExecutorService executor = Executors.newCachedThreadPool();
		HttpServer httpServer = new HttpServer(8080);
		//Task task = new Task();
		LogicModule logicModule = new LogicModule();
		executor.execute(httpServer);
		executor.execute(logicModule);
	}
	public static void main(String[] args) throws IOException{
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