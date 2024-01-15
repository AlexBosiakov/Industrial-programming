package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Text text = new Text();
        text.read("test.txt");

        text.writeBigNum("rezultnumb.txt");
        text.writeMap("rezultmap.txt");
        text.sort();
        text.writeSorted("rezultsort.txt");
        text.writeMathExp("rezultmath.txt");
        text.serializeToJSON("rezultJSON.txt");
    }
}