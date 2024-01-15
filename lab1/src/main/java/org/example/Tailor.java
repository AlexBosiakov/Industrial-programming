package org.example;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;
import java.io.*;
public class Tailor {
    private double x;
    private double epsilon;
    private int k;

    public Tailor(double x, int k) {
        this.x = x;
        this.k = k;
        epsilon = Math.pow(10, -k);

    }
    public Tailor() {
        this.x = 0;
        this.k = 0;
        epsilon = 0;

    }
    public double getX() {
        return x;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public double calculateSystemSolution() {
        return Math.sin(x) / x;
    }
    public double calculateMySolution() {
        double element = 1, result = 1, count = 1;
        while(Math.abs(element) > epsilon)
        {
            count+=2;
            element = -1 * element*x*x/(count*(count-1));
            result += element;
        }
        return result;
    }

    public float calculateBigDecimal() {
        BigDecimal eps = new BigDecimal(1);
        for(int i = 0; i < k; ++i)
        {
            eps = eps.divide(new BigDecimal(10));
        }
        BigDecimal result = new BigDecimal(1);
        double count = 1;
        BigDecimal bigX = new BigDecimal(x);
        BigDecimal element = new BigDecimal(1);

        while(element.abs().doubleValue() > eps.abs().doubleValue())
        {
            count += 2;
            element = element.multiply(bigX);
            element = element.multiply(bigX);
            element = element.multiply(new BigDecimal(-1));
            element = element.divide(new BigDecimal(count - 1),k, RoundingMode.HALF_UP);
            element = element.divide(new BigDecimal(count), k, RoundingMode.HALF_UP);
            result = result.add(element);
        }
        return result.floatValue();
    }


    public void printResult(){
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(k + 1);

        System.out.println("System result:");
        System.out.println(formatter.format(calculateSystemSolution()));

        System.out.println("My result:");
        System.out.println(formatter.format(calculateMySolution()));

        System.out.println("big result:");
        System.out.println(formatter.format(calculateBigDecimal()));

    }
    public void scanInput(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try{
            System.out.println("Enter k:");
            k = Integer.parseInt(br.readLine());
            System.out.println("Enter x:");
            x = Double.parseDouble(br.readLine());
            epsilon = Math.pow(10, -k);
        }
        catch (NumberFormatException e) {
            System.out.println("Не целое число");
            System.exit(1);
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения с клавиатуры");
            System.exit(1);
        }
    }
}

