package com.notificationmail.mail.utils;

public class EmailUtils {

    public static String getEmail(String name, String host, String token) {
        return "Hello " + name + """
                , \n Your new account has been created.Please click the link below to verify your account \n
                """ + getVerificationUrl(host, token) + "\n\n The Support Team";
    }

    public static String getVerificationUrl(String host, String token) {
        return host + "/api/user?token=" + token;
    }
}
