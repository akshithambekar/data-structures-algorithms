// Name: Akshith Ambekar
// Date: 
import java.util.*;

interface BSTinterface<E>
{
   public int size();
   public boolean contains(E element);
   public E add(E element);
   //public E addBalanced(E element); // AVL
   public E remove(E element);
   public E min();
   public E max();
   public String display();
   public String toString();
   public List<E> toList();  //returns an in-order list of E
}

public class BST_Generic<E extends Comparable<E>> implements BSTinterface<E> {

  private TreeNode<E> root;
  private int size;
  public BST_Generic()
  {
     root = null;
     size = 0;
  }
public int size()
{
   return countNodes(root);
}

public int countNodes(TreeNode<E> t) 
{
   if (t == null) {
      return 0;
   }
   return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());

}
  public E add(E element) 
  {
      root = add(root, element);
      return root.getValue();
  }
  private TreeNode<E> add(TreeNode<E> t, E element) //recursive helper method
  {      
      if (t == null) {
         return new TreeNode(s, null, null);
      } else if (element.compareTo(t.getValue()) <= 0) {
         t.setLeft(add(t.getLeft(), s));
      } else {
         t.setRight(add(t.getRight(), s));
      }
      return t;
  }

  public boolean contains(E element)
   {
      return contains(root, element);
   }
   private boolean contains(TreeNode<E> t, E x) //recursive helper method
   {
      if (t == null) {
         return false;
      }
      int c = x.compareTo(t.getValue());
      if (c == 0) {
         return true;
      } else if (c < 0) {
         return contains(t.getLeft(), x);
      } else {
         return contains(t.getRight(), x);
      }
   }

  public String display()
  {
      return display(root, 0);
  }
  private String display(TreeNode<E> t, int level) //recursive helper method
  {
      String toRet = "";
      if (t == null)
         return "";
      toRet += display(t.getRight(), level + 1); 
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); 
      return toRet;
  }

  public E min()
  {
      return min(root);
  }
  private E min(TreeNode<E> t)  //use iteration
  {
      if (t == null) {
         return null;
      }
      while (t.getLeft() != null) {
         t = t.getLeft();
      }
      return t.getValue();
  }

  public E max()
  {
      return max(root);
  }
  private E max(TreeNode<E> t)  //recursive helper method
  {
      if (t == null) {
         return null;
      }
      if (t.getRight() == null) {
         return t.getValue();
      }
      return max(t.getRight());
  }

  public E remove(E element)
  {
      root = remove(root, element);
      size--;
      return root.getValue()
  }
  private TreeNode<E> remove(TreeNode<E> current, E target)
  {
      if (current == null)
         return null;
      
      if (target.equals(current.getValue())) {
         if (current.getLeft() == null && current.getRight() == null)
            return null;
         else if (current.getLeft() == null)
            return current.getRight();
         else if (current.getRight() == null)
            return current.getLeft();
         else {
            String maxLeft = max(current.getLeft())
            current.setValue(maxLeft);
            current.setLeft(remove(current.getLeft(), maxLeft));
         }
      }

      else if (target.compareTo(current.getValue()) < 0)
         current.setLeft(remove(current.getLeft(), target));
      else
         current.setRight(remove(current.getRight(), target));
      return current;
  }
  public String toString()
  {
    return toString(root);
  }
  private String toString(TreeNode<E> t)  //an in-order traversal.  Use recursion.
  {
      String returnString = "";
      if (t != null) {
         returnString += toString(t.getLeft());
         returnString += t.getValue() + " ";
         returnString += toString(t.getRight());
      }
      return returnString;
  }
}
  
/*******************
  Copy your BST code.  Implement generics.
**********************/

/*******************
  Copy your TreeNode code.  Implement generics.
**********************/
class TreeNode<E>
{
  private E value; 
      private TreeNode<E> left, right;
   
       public TreeNode (E initValue)
      { 
         value = initValue; 
         left = null; 
         right = null; 
      }
   
       public TreeNode (E initValue, TreeNode<E> initLeft, TreeNode<E> initRight)
      { 
         value = initValue; 
         left = initLeft; 
         right = initRight; 
      }
   
       public E getValue()
      { 
         return value; 
      }
   
       public TreeNode<E> getLeft() 
      { 
         return left; 
      }
   
       public TreeNode<E> getRight() 
      { 
         return right; 
      }
   
       public void setValue(E theNewValue) 
      { 
         value = theNewValue; 
      }
   
       public void setLeft(TreeNode<E> theNewLeft) 
      { 
         left = theNewLeft;
      }
   
       public void setRight(TreeNode<E> theNewRight)
      { 
         right = theNewRight;
      }
}