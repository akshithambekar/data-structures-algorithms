// Name:   
// Date: 
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      
      
      //part_1_using_pig();
    
      part_2_using_piglatenizeFile();
      

      String pigLatin = pig("What!?");
      System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      pigLatin = pig("{(Hello!)}");
      System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      pigLatin = pig("\"McDonald???\"");
      System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   
   // making a method for locating the first vowel in a string
   public static int indexOfFirstVowel(String s) {
      String loweredWord = s.toLowerCase();
   
      for (int index = 0; index < loweredWord.length(); index++) {
      
         if (vowels.contains(String.valueOf(loweredWord.charAt(index)))) {
            return index;
         }
      }
      
      return -1; 
   }
   
   private static String convertFirstLetter(String s) {
      char x = s.charAt(0);
      
      String s1 = s.substring(1, s.length());
      String s2 = String.valueOf(x).toUpperCase() + s1;
      
      return s2;
   }
   
   private static String convertFirstLetterLower(String s) {
      char x = s.charAt(0);
      
      String s1 = s.substring(1, s.length());
      String s2 = String.valueOf(x).toLowerCase() + s1;
      
      return s2;
   }
   
   public static String pig(String s) {
      
      // punctuation check
      
      String begPunct = "";
      String endPunct = "";
      
      while (punct.indexOf(s.charAt(0)) > -1) {   
         begPunct = begPunct.concat(s.substring(0, 1));
         s = s.substring(1, s.length());
      }
      
      while (punct.indexOf(s.charAt(s.length() - 1)) > -1) {
         endPunct = endPunct.concat(s.substring(s.length() - 1, s.length()));
         s = s.substring(0, s.length() - 1);
      }
      
      int firstVowelIndex = indexOfFirstVowel(s);

      if(s.length() == 0)
         return "";
         
      // no vowel checks
      
      if (vowels.indexOf(String.valueOf(s.charAt(0))) >= 0) {
         String x = s.concat("way");
         return begPunct + x + endPunct;
      }   
      
      boolean vowelFound = false;
      
      for (int i = 0; i < vowels.length(); i++) {
         
         if (s.contains(String.valueOf(vowels.charAt(i)))) {
            vowelFound = true;
         }
      }
      
      if (!vowelFound) {
         return "**** NO VOWEL ****";
      } 
      
      boolean isFirstUpperCase = (letters.indexOf(String.valueOf(s.charAt(0)))) <= 25;
      
      // qu checks
     
      if (s.toLowerCase().contains("qu")) {
         int indexQ = s.toLowerCase().indexOf("qu");
         String newString = s.substring(indexQ + 2, s.length());
         String string2 = newString.concat(s.substring(0, indexQ + 2)).toLowerCase();
         String string3 = string2.concat("ay");
         
         if (isFirstUpperCase) {
            return begPunct + convertFirstLetter(string3) + endPunct;
         }
         
         return begPunct + string3 + endPunct;
      }
      
      // y checks
       
      int indexY = s.toLowerCase().indexOf("y");
      if (s.toLowerCase().contains("y") && s.toLowerCase().charAt(0) != 'y' && indexY < firstVowelIndex) {
         String newString = s.substring(indexY, s.length());
         String string2;
         if (isFirstUpperCase) {
            string2 = newString.concat(s.substring(0, indexY)).toLowerCase();
         } else {
            string2 = newString.concat(s.substring(0, indexY));
         }
         String string3 = string2.concat("ay");
         
         if (isFirstUpperCase) {
            return begPunct + convertFirstLetter(string3) + endPunct;
         }
         
         return begPunct + string3 + endPunct;
      }
      
      // first letter uppercase checks
      
      if (isFirstUpperCase) {
         String newString = s.substring(firstVowelIndex, s.length());
         String string2 = newString.concat(convertFirstLetterLower(s.substring(0, firstVowelIndex)));
         String string3 = string2.concat("ay");

         if (isFirstUpperCase) {
            return begPunct + convertFirstLetter(string3) + endPunct;
         }
         
         return begPunct + string3 + endPunct;
      }
      
   
      // main return statement
      
      String newString = s.substring(firstVowelIndex, s.length());
      String string2 = newString.concat(s.substring(0, firstVowelIndex));
      String string3 = string2.concat("ay");
      
      return begPunct + string3 + endPunct;
      }   
   

   
   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile(fileNameIn, fileNameOut);
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
  
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }

      while (infile.hasNextLine()) {
         String line = infile.nextLine();
         StringTokenizer st = new StringTokenizer(line);
         String pigLine = "";
         
         while (st.hasMoreTokens()) {
            pigLine = pigLine.concat(pig(st.nextToken())) + " ";
         }
         outfile.println(pigLine);
      } 
      
      outfile.close();
      infile.close();
   }
}
    
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   
   public static String pigReverse(String s)
   {
      if(s.length() == 0)
         return "";
      
      return null; // temporary to let code run 
         
   }
   */

