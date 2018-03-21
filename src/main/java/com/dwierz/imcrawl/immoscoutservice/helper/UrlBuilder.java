package com.dwierz.imcrawl.immoscoutservice.helper;

public class UrlBuilder {

    public static String getListUrlForPage(int pageNumber) {
        return "https://www.immobilienscout24.de/Suche/S-T/P-" + pageNumber + "/Haus-Kauf/Nordrhein-Westfalen/Koeln";
    }

    public static String getDetailUrlForExposeId(String exposeId) {
        return "https://www.immobilienscout24.de/expose/" + exposeId;
    }
}
