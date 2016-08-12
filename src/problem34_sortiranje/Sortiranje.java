package problem34_sortiranje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by petar on 8/12/16.
 */

class Word implements Comparable<Word> {
    int idx;
    String word;
    double val;

    public Word(int idx, String word) {
        this.idx = idx;
        this.word = word;
        this.val = 0;
        for(char c : word.toCharArray()) {
            val += c - 'A' + 1;
        }
        val /= word.length();
    }

    @Override
    public int compareTo(Word o) {
        if(Double.compare(val, o.val) == 0) {
            return Integer.compare(idx, o.idx);
        } else {
            return Double.compare(val, o.val);
        }
    }

    @Override
    public String toString() {
        return this.word;
    }
}

public class Sortiranje {

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
            List<Word> words = new ArrayList<>();
            for(int i = 0; i < N; ++i) {
                String w = br.readLine();
                words.add(new Word(i, w));
            }
            Collections.sort(words);
            for(Word w : words) {
                System.out.println(w);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
