//name: Akshith Ambekar   date:
import java.text.DecimalFormat;
public class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   /* enter the private fields */
   
   private double balance;
   private Station boardedAt;
   private boolean isBoarded;
   
   /* the one-arg constructor  */
   public SmartCard(double initBalance)
   {
      balance = initBalance;
      boardedAt= null;
      isBoarded = false;
   }

   //these three getter methods only return your private data
   //they do not make any changes to your data
   
   public boolean getIsBoarded() 
   { 
      return isBoarded;
   }
   
   public double getBalance()
   {
      return balance;
   }
         
   public Station getBoardedAt()
   {
      return boardedAt;
   }
   
   public String getFormattedBalance() {
      return df.format(balance);
   }
    
   /* write the instance methods  */
  
   public void board(Station s) {
      if (isBoarded == true) {
         System.out.println("Error: already boarded?!");
         return;
      }
      
      if (balance < 0.50) {
         System.out.println("Insufficient funds to board. Please add more money.");
         return;
      }
      
      boardedAt = s;
      isBoarded = true;
   }
   
   public double cost(Station s) {
      int difference = Math.abs(boardedAt.getZone() - s.getZone());
      return 0.5 + (difference * 0.75);
   }
   
   public void exit(Station s) {
   
      if (isBoarded == false) {
         System.out.println("Error: Did not board?!");
         return;
      }
      
      if (balance < cost(s)) {
         System.out.println("Insufficient funds to exit. Please add more money.");
         return;
      }
      
      isBoarded = false;
      balance = balance - cost(s);
      System.out.println("From " + boardedAt.getName() + " to " + s.getName() + " costs " + df.format(cost(s)) + ". SmartCard has " + getFormattedBalance());
      
      if (boardedAt == s) {
         boardedAt = null;
      }
      
   }
   
   public void addMoney(double d) {
      balance = balance + d;
   }
   
}
   
// ***********  start a new class.  The new class does NOT have public or private.  ***/

class Station
{
   private String name;
   private int zone;
   
   public Station() {
      name = "";
      zone = 0;
   }
   
   public Station(String x, int y) {
      name = x;
      zone = y;
   }
   
   public String getName() {
      return name;
   }
   
   public int getZone() {
      return zone;
   }
}