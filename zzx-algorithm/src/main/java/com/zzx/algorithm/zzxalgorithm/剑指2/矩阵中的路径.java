package com.zzx.algorithm.zzxalgorithm.剑指2;

/**
 * 剑指 Offer 12. 矩阵中的路径
 *
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例 1：
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 *
 */
public class 矩阵中的路径 {

    public boolean exist(char[][] board, String word) {
        char[] wordArr = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, wordArr, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, char[] wordArr, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >=  board[0].length || j < 0 || board[i][j] != wordArr[k]) {
            return false;
        } else if (k == wordArr.length - 1) {
            return true;
        }
        board[i][j] = '\0';
        boolean result = dfs(board, wordArr, i + 1, j, k+1) || dfs(board, wordArr, i - 1, j, k+1)
                || dfs(board, wordArr, i, j + 1, k + 1) || dfs(board, wordArr, i, j - 1, k + 1);
        board[i][j] = wordArr[k];
        return result;
    }

}
