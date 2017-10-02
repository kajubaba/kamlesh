package com.narendra.sams.web.utils;

public class LogoNameUtil {
    public static String getImagePath() {
        String deployedOn = System.getenv("ENV");
        if ("KRISHNA".equals(deployedOn)) {
            return "/resources/logo/Logo_KRISHNA.jpg";
        }
        if ("SPIS".equals(deployedOn)) {
            return "/resources/logo/Logo_SPIS.jpg";
        }
        if ("SGRKL".equals(deployedOn)) {
            return "/resources/logo/Logo_SGRKL.jpg";
        }
        return null;
    }
}
