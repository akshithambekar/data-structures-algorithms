// Name: Akshith Ambekar
// Date: 1/16/2022

//uses PostfixEval

import java.util.*;

public class InfixPostfixEval {
   public static final String LEFT = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";

   public static void main(String[] args) {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /* build your list of Infix expressions here */
      List<String> infixExp = new ArrayList<>();
      infixExp.add("5 - 1 - 1");
      infixExp.add("5 - 1 + 1");
      infixExp.add("12 / 6 / 2");
      infixExp.add("3 + 4 * 5");
      infixExp.add("3 * 4 + 5");
      infixExp.add("1.3 + 2.7 + -6 * 6");
      infixExp.add("( 33 + -43 ) * ( -55 + 65 )");
      infixExp.add("8 + 1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");
      infixExp.add("3 + ( 4 - 5 - 6 * 2 )");
      infixExp.add("2 + 7 % 3");
      infixExp.add("( 2 + 7 ) % 3");

      for (String infix : infixExp) {
         String pf = infixToPostfix(infix); // get the conversion to work first
         System.out.println(infix + "\t\t\t" + pf);
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf)); // PostfixEval must work!
      }
   }

   public static String infixToPostfix(String infix) {
      List<String> infixes = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      Stack<String> s = new Stack<String>();
      String toRet = "";

      for (String str : infixes) {
         switch(str) {
            case "(":
               s.push(str);
               break;
            
            case ")":
               while (!s.peek().equals("(")) {
                  toRet += s.pop() + " ";
               }
               s.pop();
               break;
            
            case "+" : case "-" : case "*" : case "/":
               while (!s.isEmpty() && isLowerOrEqual(str.charAt(0), s.peek().charAt(0))) {
                  toRet += s.pop() + " ";
               }
               s.push(str);
               break;
            
            default:
               toRet += str + " ";
               break;
         }
      }

      while (!s.isEmpty()) {
         toRet += s.pop() + " ";
      }

      return toRet.trim();

   }

   // enter your precedence method below

   public static boolean isLowerOrEqual(char c, char d) {
      String str1 = Character.toString(c);
      String str2 = Character.toString(d);
      if (("*/".contains(str2) && "+-".contains(str1)) || ("+-".contains(str2) && "+-".contains(str1)) || ("*/".contains(str2) && "*/".contains(str1))) {
         return true;
      } else {
         return false;
      }
   }

}

/********************************************
 * 
 * Infix --> Postfix --> Evaluate
 * 5 - 1 - 1 5 1 - 1 - 3.0
 * 5 - 1 + 1 5 1 - 1 + 5.0
 * 12 / 6 / 2 12 6 / 2 / 1.0
 * 3 + 4 * 5 3 4 5 * + 23.0
 * 3 * 4 + 5 3 4 * 5 + 17.0
 * 1.3 + 2.7 + -6 * 6 1.3 2.7 + -6 6 * + -32.0
 * ( 33 + -43 ) * ( -55 + 65 ) 33 -43 + -55 65 + * -100.0
 * 8 + 1 * 2 - 9 / 3 8 1 2 * + 9 3 / - 7.0
 * 3 * ( 4 * 5 + 6 ) 3 4 5 * 6 + * 78.0
 * 3 + ( 4 - 5 - 6 * 2 ) 3 4 5 - 6 2 * - + -10.0
 * 2 + 7 % 3 2 7 3 % + 3.0
 * ( 2 + 7 ) % 3 2 7 + 3 % 0.0
 * 
 ***********************************************/
