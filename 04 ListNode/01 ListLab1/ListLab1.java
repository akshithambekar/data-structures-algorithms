// Name: Akshith Ambekar
// Date: 11/9/2021
import java.util.*;

import javax.swing.text.html.HTMLDocument.HTMLReader;
public class ListLab1
{
   public static void main(String[] args)
   {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         	new ListNode("science",
         		new ListNode("java",
         			new ListNode("coffee", head)
         		)
         	)
         );
      print(head);
      print(head);
      
      /**** uncomment the code below for ListLab1 Assignment  ****/
      
      ListNode a = copyNode(head);
      System.out.println("The head has a value \"" + head.getValue() + "\" at "+ head);
      System.out.println("The copy of head has a value of \"" + a.getValue() + "\" at "+ a);
    
      System.out.print("Copy the list: ");
      ListNode copy = copyList(head);
      print(copy);
    
      System.out.print("The rest of the list: ");
      ListNode theRest = rest(copy);
      print(theRest);
    
      System.out.println("First of the rest = " + first(theRest));
      System.out.println("Second of the rest = " + second(theRest));
      ListNode p = pointerToLast(theRest);
      System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      ListNode c = copyOfLast(theRest);
      System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
    
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      
      theRest = insertFirst(theRest, x);
      theRest = insertLast(theRest, x);
      print(theRest);
      
   }
   public static void print(ListNode head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
   
   /* enter your code here */
   
   public static ListNode copyNode(ListNode arg) {
      if (arg == null) {
         return null;
      }
      return new ListNode(arg.getValue(), arg.getNext());
   }
   
   public static ListNode copyList(ListNode arg) {
      if (arg == null) {
         return null;
      }
      return new ListNode(arg.getValue(), copyList(arg.getNext()));
   }
   
   public static ListNode rest(ListNode h) {
      if (h == null || h.getNext() == null) {
         return null;
      }
      return new ListNode(h.getNext().getValue(), rest(h.getNext()));
   }
   
   public static Object first(ListNode head) {
      if (head == null) {
         return null;
      }
      return head.getValue();
   }
   
   public static Object second(ListNode head) {
      if (head == null || head.getNext() == null) {
         return null;
      }
      return head.getNext().getValue();
   }
   
   public static ListNode pointerToLast(ListNode head) {
      if (head == null) {
         return null;
      }
      ListNode last = head;
      while (last.getNext() != null) {
         last = last.getNext();
      }
      return last;
   }
   
   public static ListNode copyOfLast(ListNode head) {
      while (head.getNext() != null) {
         head = head.getNext();
      }
      return copyNode(head);
   }
   
   public static ListNode insertFirst(ListNode head, Object arg) {
      if (head == null) {
         return null;
      }
      ListNode temp = new ListNode(arg, head);
      return temp;
   }
   
   public static ListNode insertLast(ListNode head, Object arg) {
      if (head == null) {
         return null;
      }

      ListNode temp = head;
      while (temp.getNext() != null) {
         temp = temp.getNext();
      }

      temp.setNext(new ListNode(arg, null));
      return head;
   }
}

/*****************************************
 
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 The head has a value "computer" at ListNode@15db9742
 The copy of head has a value of "computer" at ListNode@6d06d69c
 Copy the list: [computer, science, java, coffee, nonsense, boo, foo, hello]
 The rest of the list: [science, java, coffee, nonsense, boo, foo, hello]
 First of the rest = science
 Second of the rest = java
 Pointer to Last = hello at ListNode@7852e922
 Copy of Last =    hello at ListNode@4e25154f
 Insert what? p
 [p, science, java, coffee, nonsense, boo, foo, hello, p]
    
  **********************************************/
