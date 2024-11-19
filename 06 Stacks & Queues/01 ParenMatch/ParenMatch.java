// Name: Akshith Ambekar
// Date: 1/10/2022

import java.util.*;

public class ParenMatch
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter test cases here */
      //parenExp.add("5 + 7");
      //parenExp.add("( 15 + -7 )");
      //parenExp.add(") 5 + 7 (");
      //parenExp.add("( ( 5.0 - 7.3 ) * 3.5 )");
      //parenExp.add("< { 5 + 7 } * 3 >");
      parenExp.add("[ ( 5 + 7 ) * ] 3");
      parenExp.add("( 5 + 7 ) * 3");
      parenExp.add("5 + ( 7 * 3 )");
      parenExp.add("( ( 5 + 7 ) * 3");
      parenExp.add("[ ( 5 + 7 ] * 3 )");
      parenExp.add("[ ( 5 + 7 ) * 3 ] )");
      parenExp.add("( [ ( 5 + 7 ) * 3 ]");
      parenExp.add("( ( ( ) $ ) )");
      parenExp.add("( ) [ ]");

      for (String s : parenExp)
      {
         boolean good = checkParen(s);
         if (good) {
            System.out.println(s + "\t good!");
         } else {
            System.out.println(s + "\t BAD");
         }
      }
   }
     
   //returns the index of the left parentheses or -1 if it is not there
   public static int isLeftParen(String p)
   {
      return LEFT.indexOf(p);
   }
  
   //returns the index of the right parentheses or -1 if it is not there
   public static int isRightParen(String p)
   {
      return RIGHT.indexOf(p);
   }
   
   public static boolean checkParen(String exp)
   {
      /*
         else if it is right paren?
            check if the stack is empty or not
            if stack is not empty, pop and compare indices
      if stack is not empty
         return false;
      */
      Stack<String> stack = new Stack<String>();
      String[] split = exp.split(" ");
      for (String x : split) {
         if (isLeftParen(x) >= 0) {
            stack.push(x);
         } else if (isRightParen(x) >= 0) {
            
            if (stack.isEmpty() && RIGHT.contains(x)) {
               return false;
            }
          
            if (LEFT.indexOf(stack.peek()) == isRightParen(x)) {
               stack.pop();
               //return true;
            }else
               return false;
            
            /*if (!stack.isEmpty()) {
               return false;
            }*/
         }
      }
      if (stack.isEmpty()) {
         return true;
      }
      return false;
   }
}

/*****************************************

Parentheses Match
5 + 7		 good!
( 15 + -7 )		 good!
) 5 + 7 (		 BAD
( ( 5.0 - 7.3 ) * 3.5 )		 good!
< { 5 + 7 } * 3 >		 good!
[ ( 5 + 7 ) * ] 3		 good!
( 5 + 7 ) * 3		 good!
5 + ( 7 * 3 )		 good!
( ( 5 + 7 ) * 3		 BAD
[ ( 5 + 7 ] * 3 )		 BAD
[ ( 5 + 7 ) * 3 ] )		 BAD
( [ ( 5 + 7 ) * 3 ]		 BAD
( ( ( ) $ ) )		 good!
( ) [ ]		 good!
 
 *******************************************/
