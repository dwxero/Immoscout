package com.dwxero.crawl.immoscout.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataExtractorHelper {

    private static final Logger LOG = LoggerFactory.getLogger(DataExtractorHelper.class);

    public String getTitleFromHtml(String html) {
        String title = "";
        Document document = Jsoup.parse(html);

        title = document.getElementById("expose-title").text();

        if ("".equals(title)) {
            LOG.warn("Couldn't find a proper title for property");
        }

        return title;
    }

    public String getAddressFromHtml(String html) {
        String address = "";
        Document document = Jsoup.parse(html);

        address = document.select("span[data-qa=\"is24-expose-address\"]").text();

        if ("".equals(address)) {
            LOG.warn("Couldn't find a proper address for property");
        }

        return address;
    }

    public double getPriceFromHtml(String html) {
        String price = "";
        Document document = Jsoup.parse(html);

        price = document.select("div.is24qa-kaufpreis").text();
        price = price.replace(".", "").replace(",", ".").replace(" ", "").replace("€", "");

        try {
            return Double.valueOf(price);
        } catch (NumberFormatException e) {
            LOG.warn("Couldn't find a proper price for property. Extracted value: " + price + " Message: " + e.getMessage());
            return 0.0d;
        }
    }

    public double getRoomsFromHtml(String html) {
        String rooms = "";
        Document document = Jsoup.parse(html);

        rooms = document.select("div.is24qa-zi").text();
        rooms = rooms.replace(",", ".").replace(" ", "");

        try {
            return Double.valueOf(rooms);
        } catch (NumberFormatException e) {
            LOG.warn("Couldn't find a proper number of rooms for property. Extracted value: " + rooms + " Message: " + e.getMessage());
            return 0.0d;
        }
    }

    public double getBedroomsFromHtml(String html) {
        String bedrooms = "";
        Document document = Jsoup.parse(html);

        bedrooms = document.select("dd.is24qa-schlafzimmer").text();
        bedrooms = bedrooms.replace(",", ".").replace(" ", "");

        try {
            return Double.valueOf(bedrooms);
        } catch (NumberFormatException e) {
            LOG.warn("Couldn't find a proper number of bedrooms for property. Extracted value: " + bedrooms + " Message: " + e.getMessage());
            return 0.0d;
        }
    }

    public double getBathroomsFromHtml(String html) {
        String bathrooms = "";
        Document document = Jsoup.parse(html);

        bathrooms = document.select("dd.is24qa-badezimmer").text();
        bathrooms = bathrooms.replace(",", ".").replace(" ", "");

        try {
            return Double.valueOf(bathrooms);
        } catch (NumberFormatException e) {
            LOG.warn("Couldn't find a proper number of bathrooms for property. Extracted value: " + bathrooms + " Message: " + e.getMessage());
            return 0.0d;
        }
    }

    public double getSquareMetersLivingFromHtml(String html) {
        String squareMetersLiving = "";
        Document document = Jsoup.parse(html);

        squareMetersLiving = document.select("div.is24qa-wohnflaeche").text();
        squareMetersLiving = squareMetersLiving.replace(".", "").replace(",", ".").replace("m²", "").replace(" ", "");

        try {
            return Double.valueOf(squareMetersLiving);
        } catch (NumberFormatException e) {
            LOG.warn("Couldn't find a proper value for square meters living for property. Extracted value: " + squareMetersLiving + " Message: " + e.getMessage());
            return 0.0d;
        }
    }

    public double getSquareMetersLandFromHtml(String html) {
        String squareMetersLand = "";
        Document document = Jsoup.parse(html);

        squareMetersLand = document.select("div.is24qa-grundstueck").text();
        squareMetersLand = squareMetersLand.replace(".", "").replace(",", ".").replace("m²", "").replace(" ", "");
        try {
            return Double.valueOf(squareMetersLand);
        } catch (NumberFormatException e) {
            LOG.warn("Couldn't find a proper value for square meters land for property. Extracted value: " + squareMetersLand + " Message: " + e.getMessage());
            return 0.0d;
        }
    }

    public String getCommissionNoteFromHtml(String html) {
        String commissionNote = "";
        Document document = Jsoup.parse(html);

        commissionNote = document.select("dd.is24qa-provision").text();

        if ("".equals(commissionNote)) {
            LOG.warn("Couldn't find a proper commission note for property");
        }

        return commissionNote;
    }
}
