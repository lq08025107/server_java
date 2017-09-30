/**
 * 
 */
package com.sdt.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuqiang
 *
 */
public class ThreadTest {
	public static void main(String[] args){
		ExecutorService executor = Executors.newCachedThreadPool();
		for(int i = 0; i<15; i++){
			MyTask myTask = new MyTask(i);
			executor.execute(myTask);
//			System.out.println("线程池中线程的数目: " + executor. + ", 队列中等待的任务数目: " + 
//			executor.getQueue().size() + ", 已执行完的任务数目: " + executor.getCompletedTaskCount());
		}
		System.out.println("complete");
	}
}

class MyTask implements Runnable{
	private int taskNum;
	
	public MyTask(int num){
		this.taskNum = num;
	}

	public void run() {
		System.out.println("正在执行task " + taskNum);
		try {
			Thread.currentThread().sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("task " + taskNum + "执行完毕");
	}
}
