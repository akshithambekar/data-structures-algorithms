// Name: Akshith Ambekar
// Date: 11/18/2021

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size = 0;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   /* two accessor methods  */
   public int size()
   {
      return size;
   }
   public DLNode getHead()
   {
      return head;
   }
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   /* inserts obj at position index (the list is zero-indexed).  
      increments size. 
      no need for a special case when size == 0.
	   */
   public void add(int index, Object obj) throws IndexOutOfBoundsException
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      if (index == 0) {
         DLNode temp = new DLNode(obj, head, head.getNext());
         head.getNext().setPrev(temp);
         head.setNext(temp);
      } else {
         DLNode x = head;
         for (int i = 0; i < index; i++) {
            x = x.getNext();
         }
         DLNode temp = new DLNode(obj, x, x.getNext());
         x.getNext().setPrev(temp);
         x.setNext(temp);
      }
      size++;
   }
    /* return obj at position index (zero-indexed). 
    */
   public Object get(int index) throws IndexOutOfBoundsException
   { 
      if (index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      
      // head = head.getNext();
      for (int i = 0; i < index; i++) {
         head = head.getNext();
      }
      return head.getNext().getValue();
   }
   /* replaces obj at position index (zero-indexed). 
        returns the obj that was replaced.
        */
   public Object set(int index, Object obj) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode temp = head.getNext();
      for (int i = 0; i < index; i++) {
         temp = temp.getNext();
      }
      Object x = temp.getValue();
      temp.setValue(obj);
      return x;
   }
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object in the node that was removed. 
        */
   public Object remove(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode temp = head;
      for (int i = 0; i < index + 1; i++) {
         temp = temp.getNext();
      }
      Object x = temp.getValue();
      temp.getPrev().setNext(temp.getNext());
      temp.getNext().setPrev(temp.getPrev());
      size--;
      return x;
   }
  	/* inserts obj to front of list, increases size.
	    */ 
   public void addFirst(Object obj)
   {
      DLNode temp = new DLNode(obj, head, head.getNext());
      head.getNext().setPrev(temp);
      head.setNext(temp);
      size++;
   }
   /* appends obj to end of list, increases size.
       */
   public void addLast(Object obj)
   {
      DLNode temp = new DLNode(obj, head.getPrev(), head);
      head.getPrev().setNext(temp);
      head.setPrev(temp);
      size++;
   }
   /* returns the first element in this list  
      */
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   /* returns the last element in this list  
     */
   public Object getLast()
   {
      return head.getPrev().getValue();
   }
   /* returns and removes the first element in this list, or
      returns null if the list is empty  
      */
   public Object removeFirst()
   {
      DLNode temp = head.getNext();
      // head = head.getNext();
      temp.getNext().setPrev(head);
      head.setNext(temp.getNext());
      size--;
      return temp.getValue();
   }
   /* returns and removes the last element in this list, or
      returns null if the list is empty  
      */
   public Object removeLast()
   {
      Object temp = head.getPrev().getValue();
      /*
      while (head.getNext() != null) {
         head = head.getNext();
      } */
      head.getPrev().getPrev().setNext(head);
      head.setPrev(head.getPrev().getPrev());
      size--;
      return temp;
   }
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      String string = "[";
      DLNode temp = head;
      for (int i = 0; i < size - 1; i++) {
         temp = temp.getNext();
         string = string + temp.getValue() + ", ";
      }
      temp = temp.getNext();
      string = string + temp.getValue() + "]";
      return string;
   }
}