package org.zzx.nk.lambda.lambda_unaryoperator01;

import java.util.function.UnaryOperator;

public class TextEncryption {
    public static void main(String[] args) {
        UnaryOperator<String> encryptText = text -> {
            StringBuilder encrypted = new StringBuilder();
            for (char c : text.toCharArray()) {
                encrypted.append((char) (c + 3));
            }
            return encrypted.toString();
        };

        String plainText = "Hello World";
        String encryptedText = encryptText.apply(plainText);

        System.out.println("Plain Text: " + plainText);
        System.out.println("Encrypted Text: " + encryptedText);
    }

    public void test() {
        String plainText = "Hello World";
        String encryptedText = encrypted(plainText);
    }

    private String encrypted(String plainText) {
        UnaryOperator<String> unaryOperator = t -> {
          StringBuilder sb = new StringBuilder();
          for (char c : t.toCharArray()) {
              sb.append((char) (c + 3));
          }
          return sb.toString();
        };
        return unaryOperator.apply(plainText);
    }
}