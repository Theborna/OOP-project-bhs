package com.electro.phase1.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary {
    private static Dictionary instance;
    private Set<Word> words = new HashSet<Word>();
    private int totalGoods, totalBads;
    private static int NORMALIZE_POWER;
    private static String[] notSuitedWords = { "I", "the", "then", "me", "hello", "hi" };

    private Dictionary() {
        for (List<String> sentence : ReadCSV.getInstance().getData())
            for (String word : sentence.get(0).split(" "))
                if (isSuited(word))
                    this.add(word, sentence.get(1).equals("1"));
        NORMALIZE_POWER = (totalBads + totalGoods) / ReadCSV.getInstance().getData().size();
    }

    public void print() {
        for (Word word : words)
            System.out.println(word);
    }

    public static Dictionary getInstance() {
        if (instance == null)
            instance = new Dictionary();
        return instance;
    }

    private void add(String word, boolean value) {
        if (value)
            totalGoods++;
        else
            totalBads++;
        get(word).add(value);
    }

    private Dictionary.Word get(String word) {
        for (Word w : words)
            if (w.word.equals(word))
                return w;
        Word newWord = new Word(word);
        words.add(newWord);
        return newWord;
    }

    public Double goodProbability(String sentence) {
        double probability = 100000;
        String[] allWords = sentence.split(" ");
        int i = 0;
        for (String word : allWords) {
            probability *= (wordGoodChance(get(word)) + 0.00000000000001);
            if (i++ < NORMALIZE_POWER)
                probability *= 2;
        }
        if (i < NORMALIZE_POWER)
            probability *= 1 << (NORMALIZE_POWER - i - 1);
        return probability * totalGoods / ((double) totalGoods + totalBads);
    }

    public Double badProbability(String sentence) {
        double probability = 100000;
        String[] allWords = sentence.split(" ");
        int i = 0;
        for (String word : allWords) {
            probability *= (wordBadChance(get(word)) + 0.00000000000001);
            if (i++ < NORMALIZE_POWER)
                probability *= 2;
        }
        if (i < NORMALIZE_POWER)
            probability *= 1 << (NORMALIZE_POWER - i - 1);
        return probability * totalBads / ((double) totalGoods + totalBads);
    }

    private Double wordGoodChance(Word word) {
        double ans = word.goodChance();
        if (ans == -1 || word.isIndifferent)
            return 1.0;
        return (totalGoods + totalBads) * ans / (double) totalGoods;
    }

    private Double wordBadChance(Word word) {
        double ans = word.badChance();
        if (ans == -1 || word.isIndifferent) {
            return 1.0;
        }
        return (totalGoods + totalBads) * ans / (double) totalBads;
    }

    public static boolean isSuited(String word) {
        for (String string : notSuitedWords)
            if (string.equals(word))
                return false;
        return true;
    }

    class Word {
        private static final double INDIFFERENCE_MEASURE = 0.2;
        private String word;
        private int goods, bads;
        private boolean isIndifferent;

        public Word(String word) {
            if (word.length() < 2)
                setIndifferent(true);
            this.word = word;
        }

        public void add(boolean value) {
            checkIndifference();
            if (isIndifferent)
                return;
            if (value)
                goods++;
            else
                bads++;
        }

        public double goodChance() {
            if (goods == 0 && bads == 0)
                return -1;
            if (Math.abs(goods - bads) / (double) goods < 0.1)
                return -1;
            return goods / ((double) getEnclosingInstance().totalGoods);
        }

        public double badChance() {
            if (bads == 0 && goods == 0)
                return -1;
            if (Math.abs(goods - bads) / (double) goods < 0.1)
                return -1;
            return bads / ((double) getEnclosingInstance().totalBads);
        }

        private void checkIndifference() {
            // if ((goods + bads) > getEnclosingInstance().words.size() *
            // INDIFFERENCE_MEASURE)
            // isIndifferent = true;
        }

        public void setIndifferent(boolean isIndifferent) {
            if (goods > bads)
                getEnclosingInstance().totalGoods--;
            else
                getEnclosingInstance().totalBads--;
            this.isIndifferent = isIndifferent;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + ((word == null) ? 0 : word.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Word other = (Word) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            if (word == null) {
                if (other.word != null)
                    return false;
            } else if (!word.equals(other.word))
                return false;
            return true;
        }

        private Dictionary getEnclosingInstance() {
            return Dictionary.this;
        }

    }
}
