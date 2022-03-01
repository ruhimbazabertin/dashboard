package com.uscboard.dashboard.util;

import javax.servlet.http.HttpServletRequest;

public class ApplicationUrl {
    public static String getSiteURL(HttpServletRequest request){
        String siteURL = request.getRequestURI().toString();
        return siteURL.replace(request.getServletPath(),"");

    }
}
