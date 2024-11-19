public class blankRemove {
   
   public static void main (String[] args) {
      String x = removeBlanks("Hello           I am a menace");
      System.out.println(x);
   }
   
   private static String removeBlanks(String str) {
      String e = str.replace(" ", "");
      return e;
   }
}