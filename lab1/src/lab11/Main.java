package lab11;
        import java.io.*;
        import java.math.*;
        import java.text.*;
        import java.util.*;


public class Main {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try{
            System.out.println("Enter x: ");
            String line = br.readLine();
            double x = Double.parseDouble(line);
            System.out.println("Enter eps^: ");
            line = br.readLine();
            int epsParam =  Integer.parseInt(line);
            double resultNormal = Teilor.calculateNormal(x, epsParam);
            float resultBig = Teilor.calculateBig(x, epsParam);
            Formatter fmt = new Formatter();
            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(epsParam+1);
            fmt.format("Normal: %+."+(epsParam+1)+"f", resultNormal);
            fmt.format(" Big: %."+(epsParam+1)+"f", resultBig);
            System.out.println(fmt);
            System.out.println("std function:" + formatter.format(Math.sin(x) / x));
        }
        catch (NumberFormatException e) {
            System.out.println("wrong number");
        }
        catch (IOException e)
        {
            System.out.println("error");
        }

    }
}
