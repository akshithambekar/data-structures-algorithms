import java.util.*;
import java.io.*;
 
public class LunchRoom
{
   public static final int TIME = 1080;  //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static PrintWriter outfile = null; //file to show the priority queue
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(int min, Queue<Customer> q)
   { 
      outfile.println(min + ": " + q);	//note: printing a priority queue will 
                                       //      show it in "heap order", not in 
                                       //      strictly highest priority first	
   }  
          
   public static void main(String[] args)
   {   
      try
      {
         outfile = new PrintWriter(new FileWriter("LunchRoom Seniors first.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      lunchRoom(TIME, outfile);
      outfile.close();
   }
   
   public static void lunchRoom(int TIME, PrintWriter outfile)
   {
      /***********************
      
        Enter your code for the simulation here.
         
      *************************/
      PriorityQueue<Customer> queue = new PriorityQueue<Customer>();
      int min = 0; 
      int totalMinutes = 0;
      double totalFreshMins = 0;
      double totalSophMins = 0;
      double totalJunMins = 0;
      double totalSenMins = 0;
      int longestWaitTime = 0;
      double numFresh = 0;
      double numSoph = 0;
      double numJun = 0;
      double numSen = 0;
      int longSenTime = 0;
      int longJunTime = 0;
      int longSophTime = 0;
      int longFreshTime = 0;
      double avgFreshTime = 0;
      double avgSophTime = 0;
      double avgJunTime = 0;
      double avgSenTime = 0;
      while (min < TIME || !queue.isEmpty()) {
         
         if (Math.random() < CHANCE_OF_CUSTOMER) {
            int gradYear = (int)(Math.random() * 4);
            Customer c = new Customer(min, gradYear + 2022);
            switch (c.getGradYear()) {
               case 2022: numSen++;
                  break;
               case 2023: numJun++;
                  break;
               case 2024: numSoph++;
                  break;
               case 2025: numFresh++;
                  break;
            }
            queue.add(c);
         }
         if (!queue.isEmpty() && queue.peek().getOrderTime() != 0) { // if first customer is still being served
            queue.peek().subtractService(); // decrement the service time
         } 
         if (!queue.isEmpty() && queue.peek().getOrderTime() == 0) { // if service time reaches 0
            int arrivalTime = queue.peek().getArrivalTime();
            queue.peek().setWaitTime(min - arrivalTime);
            totalMinutes += queue.peek().getWaitTime();
            if (longestWaitTime < queue.peek().getWaitTime()) {
               longestWaitTime = queue.peek().getWaitTime();
            }
            switch (queue.peek().getGradYear()) {
               case 2022: 
                  if (longSenTime < queue.peek().getWaitTime()) {
                     longSenTime = queue.peek().getWaitTime();
                  }
                  totalSenMins += queue.peek().getWaitTime();
                  avgSenTime = totalSenMins / numSen;
                  break;
               case 2023:
                  if (longJunTime < queue.peek().getWaitTime()) {
                     longJunTime = queue.peek().getWaitTime();
                  }
                  totalJunMins += queue.peek().getWaitTime();
                  avgJunTime = totalJunMins / numJun;
                  break;
               case 2024: 
                  if (longSophTime < queue.peek().getWaitTime()) {
                     longSophTime = queue.peek().getWaitTime();
                  }
                  totalSophMins += queue.peek().getWaitTime();
                  avgSophTime = totalSophMins / numSoph;
                  break;
               case 2025: 
                  if (longFreshTime < queue.peek().getWaitTime()) {
                     longFreshTime = queue.peek().getWaitTime();
                  }
                  totalFreshMins += queue.peek().getWaitTime();
                  avgFreshTime = totalFreshMins / numFresh;
                  break;
            }
            queue.remove();
         }      
         displayTimeAndQueue(min, queue);
         min++;
      }
      
      /*  report the results to the screen in table form, like this:
         Customer		Total		Longest		Average Wait
         Senior			47			10			3.2127659574468086
         Junior			59			19			5.796610169491525
         Sophomor			59			84			24.1864406779661
         Freshman			59			549		387.5762711864407 
         */
      System.out.println("Customer\t\tTotal\t\tLongest\t\tAverage Wait");
      System.out.println("Senior\t\t\t" + numSen + " \t\t" + longSenTime + " \t\t" + avgSenTime);
      System.out.println("Junior\t\t\t" + numJun + " \t\t" + longJunTime + " \t\t" + avgJunTime);
      System.out.println("Sophomore\t\t" + numSoph + " \t\t" + longSophTime + " \t\t" + avgSophTime);
      System.out.println("Freshman \t\t" + numFresh + " \t\t" + longFreshTime + "\t\t" + avgFreshTime);
 
   }
   
   
   /*
       Write the Customer class.  
       Include fields, constructor, accessor methods, compareTo, toString. 
      
       toString should return the arrival time and the abbreviation for 
       the high school class.  Four examples are:
             45:Se
             33:Ju 
             39:So
             25:Fr
    */ 
   public static class Customer implements Comparable<Customer> {
      private int arrivalTime;
      private int gradYear;
      private int orderAndBeServed;
      private int waitTime;
      
      public Customer(int arrival, int year) {
         arrivalTime = arrival;
         gradYear = year;
         orderAndBeServed = (int)(Math.random() * 6 + 2);
      }
      
      public int compareTo(Customer other) {
         return gradYear - other.gradYear;
      }
      
      public int getArrivalTime() {
         return arrivalTime;
      }
 
      public int getGradYear() {
         return gradYear;
      }
 
      public int getOrderTime() {
         return orderAndBeServed;
      }
 
      public int getWaitTime() {
         return waitTime;
      }
 
      public void setWaitTime(int x) {
         waitTime = x;
      }
 
      public void subtractService() {
         orderAndBeServed--;
      }
      
      public String toString() {
         String gradName = "Se";
         switch (gradYear) {
            case 2023: gradName = "Ju";
               break;
            case 2024: gradName = "So";
               break;
            case 2025: gradName = "Fe";
               break;
         }
         return arrivalTime + ":" + gradName; 
      }     
   }
}
/*************************
 
//note: printing a priority queue will 
//      show it in "heap order", not in 
//      strictly highest priority first.
//e.g.  45: [45:Se, 25:Fr, 33:Ju, 31:Fr, 34:Fr, 39:So]
*/