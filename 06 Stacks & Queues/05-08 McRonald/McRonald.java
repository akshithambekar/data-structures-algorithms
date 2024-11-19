//Updated on 12.14.2020 v2

//Name: Akshith Ambekar  Date: 1/21/22
import java.util.*;
import java.io.*;
public class McRonald
{
   public static final int TIME = 1080;     
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;   // to serve the front of the queue
   //public static final int numberOfServiceWindows = 3;  //for McRonald 3
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> q, int min)
   { 
      //Billington's
      outfile.println(min + ": " + q);	
      //Jurj's
      //outfile.println("Customer#" + intServiceAreas[i] + 
      //                            " leaves and his total wait time is " + (intMinute - intServiceAreas[i]));                     	
      
   }
   
   public static int getCustomers()
   {
      return customers;
   }
   public static double calculateAverage()
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   public static int getLongestWaitTime()
   {
      return longestWaitTime;
   }
   public static int getLongestQueue()
   {
      return longestQueue;
   }
            
   public static void main(String[] args)
   {     
    //set up file      
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      mcRonald(TIME, outfile);   //run the simulation
      
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
      /***************************************
           Write your code for the simulation   
      **********************************/
      Queue<Customer> queue = new LinkedList<Customer>();
      serviceWindow = 1;
      for (int min = 0; min < TIME; min++) {
         // determine if a new customer has arrived, let them join the queue, increase totals, display the minute and current queue
         if (Math.random() < CHANCE_OF_CUSTOMER) { // adding customer, 20% chance
            queue.add(new Customer(min));
            customers++;
         }
         // serve first customer in queue
         //queue.remove(queue.peek());
         if (!queue.isEmpty() && queue.peek().getOrderTime() != 0)// if first customer is still being served
            queue.peek().subtractService();// decrement the service time
         if (!queue.isEmpty() && queue.peek().getOrderTime() == 0) { // if service time reaches 0
            int arrivalTime = queue.peek().getArrivedAt();
            queue.peek().setWaitTime(min - arrivalTime);
            totalMinutes += queue.peek().getWaitTime();
            if (longestWaitTime < queue.peek().getWaitTime()) {
               longestWaitTime = queue.peek().getWaitTime();
            }
            queue.remove();
         } 
         if(longestQueue < queue.size()) longestQueue = queue.size();      
         displayTimeAndQueue(queue, min);

            
         /* 
            dequeue the customer, save their arrival time :done:
            calculations: compute customer's time spent waiting in the restaurant
                          add that wait time to the total wait time
                          update the longest wait time
                          update longest queue
         */

      } // exit for loop
      // while more customers: empty the queue, update variables, increment minutes 
      // display all calculations: total served, avg wait time, longest wait time, and longest queue
      int min = TIME;
      while (!queue.isEmpty()) {
          if (!queue.isEmpty() && queue.peek().getOrderTime() != 0)// if first customer is still being served
            queue.peek().subtractService();// decrement the service time
         if (!queue.isEmpty() && queue.peek().getOrderTime() == 0) { // if service time reaches 0

            int arrivalTime = queue.peek().getArrivedAt();
            queue.peek().setWaitTime(min - arrivalTime);
            totalMinutes += queue.peek().getWaitTime();
            if (longestWaitTime < queue.peek().getWaitTime()) {
               longestWaitTime = queue.peek().getWaitTime();
            }
            queue.remove();
         }
         
         if(longestQueue < queue.size()) longestQueue = queue.size();
         displayTimeAndQueue(queue, min);
         min++;
      }
      /*   report the data to the screen    */  
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
   }
   
   static class Customer      
   {
      private int arrivedAt;
      private int orderAndBeServed;
      private int waitTime;
      
    /**********************************
       Complete the Customer class with  
       constructor, accessor methods, toString.
    ***********************************/
      
      public Customer (int arrival) {
         arrivedAt = arrival;
         orderAndBeServed = (int)(Math.random() * 5 + 2);
         waitTime = orderAndBeServed + arrival;
      }

      public int getOrderTime() {
         return orderAndBeServed;
      }
      public int getArrivedAt(){ return arrivedAt; }
      public void setWaitTime(int wt){ waitTime = wt; } 
      public int getWaitTime() {
         return waitTime;
      }

      public void subtractService() {
         orderAndBeServed--;
      }

      public String toString() {
         return "" + arrivedAt;
      }
   }
}