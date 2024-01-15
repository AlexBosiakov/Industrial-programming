package org.example;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        try {
            FurnitureFactory factory = new FurnitureFactory();
            System.out.println("Chose input file extension(TXT, JSON, XML): ");
            String inputString = br.readLine();
            FileReader jsonReader;
            if (inputString.equals("TXT")) {
                jsonReader = new FileReader("input.txt");
                factory.txtInput(jsonReader);
                jsonReader.close();
            } else if (inputString.equals("JSON")) {
                jsonReader = new FileReader("input.json");
                factory.jsonInput(jsonReader);
                jsonReader.close();
            } else if (inputString.equals("XML")) {
                factory.xmlInput("input.xml");
            }

            FileWriter txtWriter = new FileWriter("output.txt");
            FileWriter jsonWriter = new FileWriter("output.json");
            factory.sort();
            factory.txtOutput(txtWriter);
            factory.jsonOutput(jsonWriter);
            factory.xmlOutput("output.xml");
            txtWriter.close();
            jsonWriter.close();
        } catch (IOException var7) {
            System.out.println("error");
        }

    }
}
