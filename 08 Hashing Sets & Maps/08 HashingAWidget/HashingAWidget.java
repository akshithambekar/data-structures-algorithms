// Name: 
// Date: 

import java.util.*;

public class HashingAWidget
{
   public static void main(String[] args)
   {
      Set<Widget> tSet = new TreeSet<Widget>();
      Set<Widget> hSet = new HashSet<Widget>();
      	
      Widget a = new Widget(2,3);   //same or different?
      Widget b = new Widget(2,3);
      Widget c = new Widget(2,3);
     // c = b;
     // Widget d = new Widget(21,3);  tSet.add(d); hSet.add(d);
     // Widget e = new Widget(2,13);  tSet.add(e); hSet.add(e);
   
      tSet.add(a); 
      tSet.add(b);
      tSet.add(c);
       
      hSet.add(a); 
      hSet.add(b);
      hSet.add(c); 
         
      System.out.println(a.hashCode()+ " "+b.hashCode() + " " + c.hashCode());
         
      System.out.println("TreeSet:  " + tSet);
      System.out.println("HashSet:  " + hSet);
   }
}

/** 
 *  Modify the Widget class so that it hashes on its
 *  values, not on its address.   Be sure that compareTo(),
 *  equals(Object), equals(Widget), and hashCode() agree with each other.
 */
class Widget implements Comparable<Widget>
{
   private int myCubits, myHands; // fields
   
   public Widget() // default constructor
   {
      myCubits = myHands = 0;
   }
   
   public Widget(int x) // 1-arg constructor, overloaded constructor
   {
      myCubits = x;
      myHands = 0;
   }
   
   public Widget(int x, int y) // 2-arg constructor, overloaded constructor
   {
      myCubits = x;
      myHands = y;
   }
   
   public Widget(Widget arg) // 1-arg constructor, overloaded constructor
   {
      myCubits = arg.getCubits();
      myHands = arg.getHands();
   }
   
   public int getCubits() // instance method, accessor method
   {
      return myCubits;
   }
   
   public int getHands() // instance method, accessor method
   {
      return myHands;
   }
  
   public void setCubits(int x) // modifier method
   {
      myCubits = x;
   }
   
   public void setHands(int x) // modifier method
   {
      myHands = x;
   }
   
   //other methods
   public int compareTo(Widget other) // inherited method from Comparable interface, implemented in subclass Widget
   {
      // Widget w = (Widget)other;       no need to cast
      if(myCubits < other.getCubits())
         return -1;
      if(myCubits > other.myCubits)
         return 1;
      if(myHands < other.myHands)    //"private" is at the class level
         return -1;
      if(myHands > other.getHands())
         return 1;
      return 0;
   }
   
   public boolean equals(Widget other) // inherited from Object, overridden method
   {
      return compareTo(other) == 0;
   }
   
   public String toString() // inherited from Object, overridden method
   {
      return myCubits + " cubits " + myHands + " hands";
   }  
   
  	/* 2 new methods for this lab 
      override equals(Object) and hashCode
    */
   public boolean equals(Object arg) {
      if (arg instanceof Widget) {
         return equals((Widget) arg);
      }
      return false;
   }
   public int hashCode() {
      return toString().hashCode();
   }  
   
}