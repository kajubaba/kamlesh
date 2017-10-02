package com.narendra.sams.core.util;

public class AmountInWords {
    private static String amount = "";
    private static String[] maxs = new String[]{"", "", " Hundred", " Thousand", " Lakh", " Crore"};
    private static String[] teen = new String[]{" Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen"};
    private static String[] tens = new String[]{" Twenty", " Thirty", " Fourty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety"};
    private static String[] units = new String[]{"", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine"};

    public static String convertToWords(long n) {
        amount = numToString(n);
        String converted = "";
        int pos = 1;
        boolean hun = false;
        while (amount.length() > 0) {
            String C;
            if (pos == 1) {
                if (amount.length() >= 2) {
                    C = amount.substring(amount.length() - 2, amount.length());
                    amount = amount.substring(0, amount.length() - 2);
                    converted = new StringBuilder(String.valueOf(converted)).append(digits(C)).toString();
                } else if (amount.length() == 1) {
                    converted = new StringBuilder(String.valueOf(converted)).append(digits(amount)).toString();
                    amount = "";
                }
                pos++;
            } else if (pos == 2) {
                C = amount.substring(amount.length() - 1, amount.length());
                amount = amount.substring(0, amount.length() - 1);
                if (converted.length() <= 0 || digits(C) == "") {
                    if (digits(C) != "") {
                        converted = digits(C) + maxs[pos] + converted;
                    }
                    hun = true;
                } else {
                    converted = digits(C) + maxs[pos] + " and" + converted;
                    hun = true;
                }
                pos++;
            } else if (pos > 2) {
                if (amount.length() >= 2) {
                    C = amount.substring(amount.length() - 2, amount.length());
                    amount = amount.substring(0, amount.length() - 2);
                    if (!hun && converted.length() > 0) {
                        converted = digits(C) + maxs[pos] + " and" + converted;
                    } else if (digits(C) != "") {
                        converted = digits(C) + maxs[pos] + converted;
                    }
                } else if (amount.length() == 1) {
                    if (hun || converted.length() <= 0) {
                        if (digits(amount) != "") {
                            converted = new StringBuilder(String.valueOf(digits(amount))).append(maxs[pos]).append(converted).toString();
                        }
                        amount = "";
                    } else {
                        converted = new StringBuilder(String.valueOf(digits(amount))).append(maxs[pos]).append(" and").append(converted).toString();
                    }
                }
                pos++;
            }
        }
        return converted;
    }

    private static String digits(String C) {
        String converted = "";
        for (int i = C.length() - 1; i >= 0; i--) {
            int ch = C.charAt(i) - 48;
            if (i == 0 && ch > 1 && C.length() > 1) {
                converted = new StringBuilder(String.valueOf(tens[ch - 2])).append(converted).toString();
            } else if (i == 0 && ch == 1 && C.length() == 2) {
                int sum = 0;
                for (int j = 0; j < 2; j++) {
                    sum = (sum * 10) + (C.charAt(j) - 48);
                }
                return teen[sum - 10];
            } else if (ch > 0) {
                converted = new StringBuilder(String.valueOf(units[ch])).append(converted).toString();
            }
        }
        return converted;
    }

    private static String numToString(long n) {
        String num = "";
        while (n != 0) {
            num = new StringBuilder(String.valueOf((char) ((int) ((n % 10) + 48)))).append(num).toString();
            n /= 10;
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println("Amount in Words : " + convertToWords(993456797));
    }
}
