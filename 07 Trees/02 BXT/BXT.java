// Name:
// Date: 2/6/2022
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;
 
public class BXT
{
   public static final String operators = "+ - * / % ^ !";
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Codepost
   {
      return root;
   }
    
   public void buildTree(String str)
   {  
      Stack<TreeNode> stack = new Stack<TreeNode>();
      List<String> postFix = new ArrayList<String>(Arrays.asList(str.split(" ")));
      for (String s : postFix) { // push all vals into stack
         if (isOperator(s)) {
            TreeNode mainRoot = new TreeNode(s, null, stack.pop());
            TreeNode a = stack.pop();
            mainRoot.setLeft(a);
            stack.push(mainRoot);
         } else { 
            stack.push(new TreeNode(s, null, null));
         }
      }
      root = stack.pop();
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  // recursive
   {
      if (t == null) {
         return 0.0;
      }
      if (!isOperator(t.getValue() + "")) {
         return Double.parseDouble(t.getValue() + "");
      } else {
         return computeTerm(t.getValue() + "", evaluateNode(t.getLeft()), evaluateNode(t.getRight()));
      }
   }
   
   private double computeTerm(String s, double a, double b)
   {
      switch (s) {
         case "+":
            return a + b;
         case "-":
            return a - b;
         case "*":
            return a * b;
         case "/":
            return a / b;
         case "%":
            return a % b;
         case "^":
            return Math.pow(a, b);
         case "!":
            double result = 1.0;
            for (int i = 1; i <= a; i++) {
               result *= 1;
            }
            return result;
         default:
            return -1.0;
      }
   }
   
   private boolean isOperator(String s)
   {
      if (operators.contains(s)) {
         return true;
      } else {
         return false;
      }
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); // recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); // recurse left
      return toRet;
   }
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private String inorderTraverse(TreeNode t)
   {
      String toRet = "";
      if (t == null) {
         return "";
      }
      toRet += inorderTraverse(t.getLeft());
      toRet += t.getValue() + " ";
      toRet += inorderTraverse(t.getRight());
      return toRet;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toRet = "";
      if (root == null) {
         return "";
      }
      toRet += root.getValue() + " ";
      toRet += preorderTraverse(root.getLeft());
      toRet += preorderTraverse(root.getRight());
      return toRet;
   }
   
  /* extension */
   // public String inorderTraverseWithParentheses()
   // {
      // return inorderTraverseWithParentheses(root);
   // }
//    
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
      // return "";
   // }
}