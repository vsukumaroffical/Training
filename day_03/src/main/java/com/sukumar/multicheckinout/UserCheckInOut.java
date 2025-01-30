package com.sukumar.multicheckinout;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserCheckInOut {
  private static final List<String[]> userData = new ArrayList<>();

  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);

      System.out.print("Enter the number of users: ");
      int numberOfUsers = scanner.nextInt();
      scanner.nextLine(); 
       
      List<String> userIds = new ArrayList<>();        

      for (int i = 0; i < numberOfUsers; i++) {
          System.out.print("Enter user ID for user " + (i + 1) + ": ");
          userIds.add(scanner.nextLine());
      }

      ExecutorService executorService = Executors.newFixedThreadPool(5);

      
      for (String userId : userIds) {
          executorService.execute(() -> checkInUser(userId));
      }

      
      try {
          Thread.sleep(2000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

     
      for (String userId : userIds) {
          executorService.execute(() -> checkOutUser(userId));
      }

      executorService.shutdown();

      System.out.println("Running");
      while (!executorService.isTerminated()) {
      
      }

      generateExcelSheet();
      scanner.close();
  }

  private static synchronized void checkInUser(String userId) {
      String checkInTime = java.time.LocalTime.now().toString();
      System.out.println("User " + userId + " checked in at " + checkInTime +" "+Thread.currentThread());
      userData.add(new String[]{userId, checkInTime, ""});
  }

  private static synchronized void checkOutUser(String userId) {
      String checkOutTime = java.time.LocalTime.now().toString();
      System.out.println("User " + userId + " checked out at " + checkOutTime);

      
      for (String[] record : userData) {
          if (record[0].equals(userId) && record[2].isEmpty()) {
              record[2] = checkOutTime;
              break;
          }
      }
  }

  private static void generateExcelSheet() {
  	
      XSSFWorkbook workbook = new XSSFWorkbook();
      Sheet sheet = workbook.createSheet("CheckInOut Data");

      
      Row headerRow = sheet.createRow(0);
      headerRow.createCell(0).setCellValue("User ID");
      headerRow.createCell(1).setCellValue("Check-In Time");
      headerRow.createCell(2).setCellValue("Check-Out Time");

      
      int rowNum = 1;
      for (String[] record : userData) {
          Row row = sheet.createRow(rowNum++);
          row.createCell(0).setCellValue(record[0]);
          row.createCell(1).setCellValue(record[1]);
          row.createCell(2).setCellValue(record[2]);
      }

      try (FileOutputStream fileOut = new FileOutputStream("CheckInOutdata.xlsx")) {
          workbook.write(fileOut);
          System.out.println("Check-in/out data written to CheckInOutData.xlsx");
      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          try {
              workbook.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }
}
