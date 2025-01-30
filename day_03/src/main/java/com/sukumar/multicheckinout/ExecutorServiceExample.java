package com.sukumar.multicheckinout;
import java.util.concurrent.*;

public class ExecutorServiceExample {

    public static void main(String[] args) {
        
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 50; i++) {
            int taskId = i;
            executorService.submit(() -> {
                System.out.println("Task " + taskId + " is running by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskId + " is completed.");
            });
        }
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
    
}
