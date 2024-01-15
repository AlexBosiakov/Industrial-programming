package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Sentence {

    private List <String> leks;

    public Sentence(List<String> lexs) {
        this.leks = lexs;
    }

    public List<String> findBigNum() {
        List<String> res = new ArrayList<>();
        for (String lex : leks) {
            try
            {
                Double.parseDouble(lex);
                res.add(lex);
            } catch (NumberFormatException ignored) {}
        }
        return res;
    }
    public String toString()
    {
        return String.join(" ", leks);
    }
    public List<String> findMathExp() {
        List<String> res = new ArrayList<>();
        for (String lex : leks) {
            if (lex.matches("[a-zA-Z]=\\d+[+\\-*/]\\d+")) {
                res.add(lex);
            }
        }
        return res;
    }

}
