package com.bp.service;

import javax.print.event.*;
import java.awt.print.*;
import java.io.*;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class GenerateInvoice {

public static boolean printInvoice(String invoice) {
      try {
          PrintService mPrinter = null;
          Boolean bFoundPrinter = false;

          PrintService[] printServices = PrinterJob.lookupPrintServices();

          for (PrintService printService : printServices) {
              String sPrinterName = printService.getName();
             // System.out.println(sPrinterName);
              if (sPrinterName.equals("Microsoft Print to PDF")) {
                  mPrinter = printService;
                  bFoundPrinter = true;
                  break;
              }
          }
          String testData = invoice+"\f";
          InputStream is = new ByteArrayInputStream(testData.getBytes());
          DocFlavor flavor =  DocFlavor.INPUT_STREAM.AUTOSENSE   ;

          PrintService service = PrintServiceLookup.lookupDefaultPrintService();
          System.out.println(service);

          DocPrintJob job = service.createPrintJob();
          Doc doc= new SimpleDoc(is, flavor, null);

          PrintJobWatcher pjDone = new PrintJobWatcher(job);

          job.print(doc, null);

          pjDone.waitForDone();

          is.close();
          return true;
      } catch (Exception e) {
          return false;
      }
  }
  
  static class PrintJobWatcher {
      boolean done = false;

      PrintJobWatcher(DocPrintJob job) {
          // Add a listener to the print job
          job.addPrintJobListener(new PrintJobAdapter() {
              public void printJobCanceled(PrintJobEvent pje) {
                  allDone();
              }
              public void printJobCompleted(PrintJobEvent pje) {
                  allDone();
              }
              public void printJobFailed(PrintJobEvent pje) {
                  allDone();
              }
              public void printJobNoMoreEvents(PrintJobEvent pje) {
                  allDone();
              }
              void allDone() {
                  synchronized (PrintJobWatcher.this) {
                      done = true;
                      PrintJobWatcher.this.notify();
                  }
              }
          });
      }
      public synchronized void waitForDone() {
          try {
              while (!done) {
                  wait();
              }
          } catch (InterruptedException e) {
          }
      }
  }

}