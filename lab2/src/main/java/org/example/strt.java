package org.example;

import java.util.ArrayList;
import java.util.List;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class strt {
    private final String timeRegex = "(([0-1][0-9]|2[0-4]):[0-5][0-9]:[0-5][0-9])";
    private final String tenSystemNumRegex = "[0-9]+";
    private String baseStr;
    private String delimStr;
    private String[] lecsems;
    private List<String> tenSystemNumbers;
    private List<String> times;

    strt(String str, String delim){
        baseStr = str;
        delimStr = delim;
        times = new ArrayList<String>();
        tenSystemNumbers = new ArrayList<String>();
        findtimes();
        findLecsems();
        findNumbers();
        addNumberbeforenum();
        deleteSubStr();
    }
    public String getBaseString(){
        return baseStr;
    }
    public String getLecsems() {
        String result = "";
        for(int i = 0; i < lecsems.length; ++i) {
            result += ' ' + lecsems[i];
        }
        return result.trim();
    }

    public String gettimes() {
        String result = "";
        for(int i = 0; i < times.size(); ++i) {
            result += " " + times.get(i);
        }
        return result.trim();
    }

    public String getNumbers() {
        String result = "";
        for(int i = 0; i < tenSystemNumbers.size(); ++i) {
            result += " " + tenSystemNumbers.get(i);
        }
        return result.trim();
    }
    private void deleteSubStr(){
        int k = 0;
        int min = 999999;
        int fe=0,se=0;
        char firstChar = '(', lastChar =')';
        for(int i = 0; i < lecsems.length; ++i) {
            if (lecsems[i].charAt(0) == firstChar) {
                fe=i;
                break;
            }
        }
        for(int j =0; j < lecsems[fe].length(); ++j) {
            if (lecsems[fe].charAt(j) == lastChar) {
                se=j;
            }
        }
        if(fe!=0 && se!=0)
        {
            baseStr = baseStr.replace(lecsems[fe], "");
        }

    }

    private void findtimes() {
        Matcher timeMatcher = Pattern.compile(timeRegex).matcher(baseStr);
        while(timeMatcher.find()) {
            String dateString = timeMatcher.group();
            if(istime(dateString)) {
                times.add(dateString);
            }
        }
    }

    private boolean istime(String timeString) {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setLenient(false);
        try {
            timeFormat.parse(timeString);
        }
        catch(ParseException pe) {
            return false;
        }
        return true;
    }

    private void addNumberbeforenum() {
        double chislo= Math.random()*100;
        Integer randNum = (int)chislo;
        if(tenSystemNumbers.size() != 0) {
            baseStr = baseStr.replace(tenSystemNumbers.get(0),  randNum.toString()+tenSystemNumbers.get(0) );
        }
        else {
            baseStr = baseStr.replace(baseStr,  randNum.toString()+baseStr );
        }
    }

    private void findLecsems() {
        StringTokenizer tokenizer = new StringTokenizer(baseStr, delimStr);
        lecsems = new String[tokenizer.countTokens()];
        for(int i = 0; i < lecsems.length; ++i) {
            lecsems[i] = tokenizer.nextToken();
        }
    }

    private void findNumbers() {
        Matcher dateMatcher = Pattern.compile(tenSystemNumRegex).matcher(baseStr);
        while(dateMatcher.find()) {
            tenSystemNumbers.add(dateMatcher.group());
        }
    }
}
