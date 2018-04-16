import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;


public class WordCount {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;


    public static void main(String[] args) {
        WordCount wordCount = new WordCount();
        Map<String, Integer> wordMap = wordCount.readFile();
        wordCount.sortAndWrite(wordMap);
    }

    //read input file from a path
    public Map<String, Integer> readFile(){

        StringBuffer stringBuffer = new StringBuffer();
        //Read
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("/Users/suzhongbei/Desktop/codetest/input.txt")));
            String line = "";

            while((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //generate word frequency pair
        Pattern pattern = Pattern.compile("(\\.)? ");
        String[] words = pattern.split(stringBuffer.toString());
        Map<String, Integer> wordMap = new HashMap<>();
        for(String s : words){
            if(!wordMap.containsKey(s)){
                wordMap.put(s, 1);
            }
            else{
                int count = wordMap.get(s);
                wordMap.put(s, count+1);
            }
        }
        return wordMap;
    }

   //sort by frequency and write into output file
    public void sortAndWrite(Map<String, Integer> wordMap){
        //Sort
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1,
                               Entry<String, Integer> o2) {

                return o2.getValue().compareTo(o1.getValue());
            }
        });

        //Write
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File("/Users/suzhongbei/Desktop/codetest/output.txt")));
            bufferedWriter.write("Word and frequency：");
            bufferedWriter.flush();
            bufferedWriter.newLine();
            for(Map.Entry<String, Integer> mapping : list){
                bufferedWriter.write(mapping.getKey() + " : " + mapping.getValue());
                bufferedWriter.flush();
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
