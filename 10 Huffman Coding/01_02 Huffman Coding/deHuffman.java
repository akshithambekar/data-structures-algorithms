
// Name: Akshith Ambekar     Date: 3/29/2022
import java.util.*;
import java.io.*;

public class deHuffman {
   public static void main(String[] args) throws IOException {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message." + middlePart + ".txt"));
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme." + middlePart + ".txt"));

      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);

      sc.close();
      huffLines.close();
   }

   public static TreeNode huffmanTree(Scanner huffLines) { // build tree based on huffLines file
      TreeNode root = new TreeNode("", null, null);
      while (huffLines.hasNext()) {
         String line = huffLines.nextLine();
         String val = line.substring(0, 1);
         TreeNode temp = root;
         for (char c : line.substring(1).toCharArray()) {
            if (c == '0') {
               if (temp.getLeft() == null) {
                  temp.setLeft(new TreeNode(""));
               }
               temp = temp.getLeft();
            } else {
               if (temp.getRight() == null) {
                  temp.setRight(new TreeNode(""));
               }
               temp = temp.getRight();
            }
         }
         temp.setValue(val);
      }
      return root;
   }

   public static String dehuff(String text, TreeNode root) { // decode text
      String result = "";
      TreeNode temp = root;
      for (char c : text.toCharArray()) {
         if (c == '0') {
            temp = temp.getLeft();
         } else { // c == '1'
            temp = temp.getRight();
         }
         if (temp.getLeft() == null && temp.getRight() == null) { // if temp is a leaf
            result += temp.getValue();
            temp = root;
         }
      }
      return result;
   }
}

/* TreeNode class for the AP Exams */
class TreeNode {
   private Object value;
   private TreeNode left, right;

   public TreeNode(Object initValue) {
      value = initValue;
      left = null;
      right = null;
   }

   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight) {
      value = initValue;
      left = initLeft;
      right = initRight;
   }

   public Object getValue() {
      return value;
   }

   public TreeNode getLeft() {
      return left;
   }

   public TreeNode getRight() {
      return right;
   }

   public void setValue(Object theNewValue) {
      value = theNewValue;
   }

   public void setLeft(TreeNode theNewLeft) {
      left = theNewLeft;
   }

   public void setRight(TreeNode theNewRight) {
      right = theNewRight;
   }
}
