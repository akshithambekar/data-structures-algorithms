// Name: Akshith Ambekar
// Date: 1/11/2022

// double newDouble = Double.parseDouble("ez");

import java.util.*;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add("3 4 5 * +"); // 23
      postfixExp.add("3 4 * 5 +"); // 17
      postfixExp.add("10 20 + -6 6 * +"); // -6
      postfixExp.add("3 4 + 5 6 + *"); // 77
      postfixExp.add("3 4 5 + * 2 - 5 /"); // 5
      postfixExp.add("8 1 2 * + 9 3 / -"); // 7
      postfixExp.add("2 3 ^"); // 8
      postfixExp.add("20 3 %"); // 2
      postfixExp.add("21 3 %"); // 0
      postfixExp.add("22 3 %"); // 1
      postfixExp.add("23 3 %"); // 2
      postfixExp.add("5 !"); // 120
      postfixExp.add("1 1 1 1 1 + + + + !"); // 120
      
      for (String pf : postfixExp)
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      Stack<Double> newStack = new Stack();
   }
   
   public static double eval(double a, double b, String ch)
   {
      if (ch.equals("+")) {
         return a + b;
      }

      if (ch.equals("-")) {
         return a - b;
      }

      if (ch.equals("*")) {
         return a * b;
      }

      if (ch.equals("/")) {
         return a / b;
      }

      if (ch.equals("%")) {
         return a % b;
      }

      if (ch.equals("^")) {
         return Math.pow(a, b);
      }

      if (ch.equals("!")) {
         double result = 1.0;
         for (int i = 1; i <= a; i++) {
            result *= i;
         }
         return result;
      }
      return 0.0;
   }
   
   public static boolean isOperator(String op)
   {
      if (operators.contains(op)) {
         return true;
      }
      return false;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/