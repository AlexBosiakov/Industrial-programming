package org.example;

import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class Text {
    private List<Sentence> sentences;
    private Map<String, Sentence> sentenceMap;
    private final String delimStr = "[ ;_]";

    public Text() {
        sentences = new ArrayList<>();
        sentenceMap = new TreeMap<>();
    }

    public void read(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String str;
            while ((str = br.readLine()) != null) {
                List<String> leks = Arrays.asList(str.split(delimStr));
                Sentence sentence = new Sentence(leks);
                sentences.add(sentence);
                sentenceMap.put(leks.get(0), sentence);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBigNum(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Sentence sentence : sentences) {
                List<String> bigNum = sentence.findBigNum();
                for (String num : bigNum) {
                    bw.write(num);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMap(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Sentence> entry : sentenceMap.entrySet()) {
                bw.write(entry.getKey() + ": " + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sort() {
        sentences.sort(Comparator.comparingInt(s -> s.toString().split("\\s+")[0].length()));
    }

    public void writeSorted(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Sentence sentence : sentences) {
                bw.write(sentence.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMathExp(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Sentence sentence : sentences) {
                List<String> mathExp = sentence.findMathExp();
                for (String exp : mathExp) {
                    bw.write(exp);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void serializeToJSON(String filename) {
        JSONArray jsonArray = new JSONArray();
        for (Sentence sentence : sentences) {
            JSONObject sentenceObject = new JSONObject();
            sentenceObject.put("lexemes", sentence.toString());
            jsonArray.add(sentenceObject);
        }
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

