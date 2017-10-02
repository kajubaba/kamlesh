package com.narendra.sams.communication.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MSG91SMSSender {
    public static void sendFeeDepositSMS(String url, String authKey, String mobileNos, String message, String senderId, String routeType) {
        IOException e;
        String encoded_message = URLEncoder.encode(message);
        StringBuilder sbPostData = new StringBuilder(url);
        sbPostData.append("authkey=" + authKey);
        sbPostData.append("&mobiles=" + mobileNos);
        sbPostData.append("&message=" + encoded_message);
        sbPostData.append("&route=" + routeType);
        sbPostData.append("&sender=" + senderId);
        try {
            URL myURL = new URL(sbPostData.toString());
            URL url2;
            try {
                URLConnection myURLConnection = myURL.openConnection();
                myURLConnection.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
                while (true) {
                    BufferedReader bufferedReader;
                    try {
                        String response = reader.readLine();
                        if (response == null) {
                            reader.close();
                            bufferedReader = reader;
                            url2 = myURL;
                            return;
                        }
                        System.out.println(response);
                    } catch (IOException e2) {
                        e = e2;
                        bufferedReader = reader;
                        url2 = myURL;
                    }
                }
            } catch (IOException e3) {
                e = e3;
                url2 = myURL;
                e.printStackTrace();
            }
        } catch (IOException e4) {
            e = e4;
            e.printStackTrace();
        }
    }
}
