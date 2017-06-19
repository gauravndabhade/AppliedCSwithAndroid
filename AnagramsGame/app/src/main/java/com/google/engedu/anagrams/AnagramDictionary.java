/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static android.R.attr.tag;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 4;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    //private ArrayList<String> wordList;
    private HashSet<String> wordSet;
    private HashMap<String,ArrayList<String>> lettersToWord;
    private HashMap<Integer,ArrayList<String>> sizeToWords;

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);

        //wordList = new ArrayList<String>();
        wordSet = new HashSet<String>();
        lettersToWord = new HashMap<String, ArrayList<String>>();
        sizeToWords = new HashMap<Integer,ArrayList<String>>();
        String line;

        while((line = in.readLine()) != null) {

            String word = line.trim();
            String keyWord = sortLetters(word);

            //Mapping keys to HashMap lettersToWord and add word(values) to ArrayList
            if(lettersToWord.get(keyWord)==null)
                lettersToWord.put(keyWord,new ArrayList<String>());
            lettersToWord.get(keyWord).add(word);

            //Mapping keys to HashMap sizeToWord and adding words(values) to ArrayList
            if(sizeToWords.get(word.length())==null)
                sizeToWords.put(word.length(),new ArrayList<String>());
            sizeToWords.get(word.length()).add(word);

            wordSet.add(word);
            //wordList.add(word);
        }
    }

    public boolean isGoodWord(String word, String base) {
        return wordSet.contains(word) && !word.contains(base);
    }

    public String sortLetters(String word)
    {
        char[] char_arr = word.toCharArray();
        Arrays.sort(char_arr);
        return new String(char_arr);
    }
    public List<String> getAnagrams(String targetWord) {
        return lettersToWord.get(sortLetters(targetWord));
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        for(char alphabet = 'a'; alphabet <= 'z';alphabet++) {
            if(null!=getAnagrams(word+alphabet))
                result.addAll(getAnagrams(word+alphabet));
        }
        return result;
    }

    public String pickGoodStarterWord() {

        while (true) {
            //For selecting size of word using sizeToWord HashMap
            String word = sizeToWords.get(DEFAULT_WORD_LENGTH).get(random.nextInt(sizeToWords.get(DEFAULT_WORD_LENGTH).size()));
            if (lettersToWord.get(sortLetters(word)).size() >= MIN_NUM_ANAGRAMS && word.length() <= MAX_WORD_LENGTH)
                return word;
        }
    }
}
