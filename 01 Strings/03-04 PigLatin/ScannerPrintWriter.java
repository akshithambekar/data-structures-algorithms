import java.util.Scanner;
import java.io.PrintWriter;

public class ScannerPrintWriter {
   public static void main (String[] args) {
      Scanner infile = new Scanner(new File("datafile.txt"));
      PrintWriter outfile = new PrintWriter(new FileWriter("process.txt"));
      outfile.println(infile.nextLine());
      int num1 = infile.nextInt();
      double num2 = infile.nextDouble();
      outfile.println("" + (num1 + num2));
      infile.close();
      outfile.close();
   }
}