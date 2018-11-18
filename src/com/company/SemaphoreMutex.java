package com.company;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class SemaphoreMutex {
    static Object lock = new Object();
    static LinkedList<String> list = new LinkedList<>();

    static Semaphore semaphore = new Semaphore(0);
    static Semaphore mutex = new Semaphore(1);

    static class Producer extends Thread {

        public void run() {
            int counter = 1;

            try{
                while(true){
                    String threadName = Thread.currentThread().getName() + counter++;

                    mutex.acquire();
                    list.add(threadName);
                    System.out.println("Producer producing new value : " + threadName);
                    mutex.release();

                    semaphore.release();
                    Thread.sleep(700);
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    static class Consumer extends Thread {
        String name;

        public Consumer(String name){
            this.name = name;
        }

        public void run() {
            try {
                while(true){
                    semaphore.acquire();
                    mutex.acquire();
                    String result = "";

                    for(String value : list){
                        result = value + ", ";
                    }
                    System.out.println(name + " consumes value : " + result + " list size : " + list.size());
                    mutex.release();
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args){
        new Producer().start();
        new Consumer("Google").start();
        new Consumer("Amazon").start();
        new Consumer("IKEA").start();
    }
}
