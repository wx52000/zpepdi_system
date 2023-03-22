package com.zpepdi.qj_heating.opcvTest;

import java.util.List;

public class JsonTxtBean {
    private List<B> words_result;

    public static class B{
        private String words;

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }

        @Override
        public String toString() {
            return "B{" +
                    "words='" + words + '\'' +
                    '}';
        }
    }

    public List<B> getWords_result() {
        return words_result;
    }

    public void setWords_result(List<B> words_result) {
        this.words_result = words_result;
    }

    @Override
    public String toString() {
        return "JsonTxtBean{" +
                "words_result=" + words_result +
                '}';
    }
}
