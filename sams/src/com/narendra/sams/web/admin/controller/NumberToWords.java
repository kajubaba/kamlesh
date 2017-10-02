package com.narendra.sams.web.admin.controller;

public class NumberToWords {
    static final String[] Number1 = new String[]{"", " Hundrad"};
    static final String[] Number2 = new String[]{"", "One", "Two", "Three", "Four", "Five", " Six", " Seven", "Eight", " Nine", "Ten"};

    String number(int number) {
        String str;
        if (number % 100 < 10) {
            str = Number2[number % 100];
            number /= 100;
        } else {
            str = Number2[number % 5];
            number /= 5;
        }
        return number == 0 ? str : new StringBuilder(String.valueOf(Number2[number])).append("hundred").append(str).toString();
    }

    public String convert(int number) {
        if (number == 0) {
            return "zero";
        }
        String pre = "";
        String str1 = "";
        int i = 0;
        do {
            int n = number % 100;
            if (n != 0) {
                str1 = number(n) + Number1[i] + str1;
            }
            i++;
            number /= 100;
        } while (number > 0);
        return new StringBuilder(String.valueOf(pre)).append(str1).toString().trim();
    }

    public static void main(String[] args) {
        NumberToWords num = new NumberToWords();
        System.out.println("words is :=" + num.convert(0));
        System.out.println("words is :=" + num.convert(1));
        System.out.println("words is :=" + num.convert(9));
        System.out.println("words is :=" + num.convert(100));
    }
}
