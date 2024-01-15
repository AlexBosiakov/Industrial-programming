package org.example;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.logging.ConsoleHandler;

public class Main {
    public static void main(String[] args) throws

    Exception {
        {

            try {
                FileReader fr = new FileReader("C:\\Users\\Admin\\IdeaProjects\\lab3\\src\\main\\java\\org\\example\\input.txt");
                Scanner scan = new Scanner(fr);
                FileWriter fw = new FileWriter("C:\\Users\\Admin\\IdeaProjects\\lab3\\src\\main\\java\\org\\example\\output.txt");
                String str = scan.nextLine();
                String razdel = scan.nextLine();
                fw.write("UnEditted string:\n" + str + "\nDelims:\n" + razdel);
                strt transformer = new strt(str, razdel);
                String time = transformer.gettimes();
                String nums = transformer.getNumbers();
                String lecsems = transformer.getLecsems();
                fw.write("\nTimes:\n" + time + "\nNumbers:\n" + nums + "\nLecsems:\n"
                        + lecsems + "\nTransformed string\n" + transformer.getBaseString());
                scan.close();
                fr.close();
                fw.close();
                System. out. println(time+lecsems);
            } catch (IOException e) {
                System.out.println("error");
            }
        }
    }
}
