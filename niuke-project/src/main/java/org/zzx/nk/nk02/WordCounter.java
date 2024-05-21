package org.zzx.nk.nk02;

/**
 * 6. 计算字符串中的单词数
 * 题目描述：编写一个函数来计算给定字符串中的单词数。
 */
public class WordCounter {
    public int countWords(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        String[] words = s.trim().split("\\s+");
        return words.length;
    }

    public static void main(String[] args) {
        WordCounter counter = new WordCounter();
        System.out.println(counter.countWords("Hello world!")); // 2
        System.out.println(counter.countWords("This is a test.")); // 4
    }
}