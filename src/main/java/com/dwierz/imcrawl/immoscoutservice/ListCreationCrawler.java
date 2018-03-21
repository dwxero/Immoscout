package com.dwierz.imcrawl.immoscoutservice;

import com.dwierz.imcrawl.immoscoutservice.helper.UrlBuilder;
import com.dwierz.imcrawl.immoscoutservice.service.PropertyService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * this crawler creates a list of all properties under the given base url.
 * it stores empty property entities in the database which have only information regarding the url saved.
 */
@Component
public class ListCreationCrawler implements Crawler {

    private static final Logger LOG = LoggerFactory.getLogger(ListCreationCrawler.class);

    private PropertyService propertyService;

    @Autowired
    public ListCreationCrawler(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    @Scheduled(fixedDelay = 12 * 60 * 60 * 1000)
    public void crawl() {
        LOG.info("------------------------------------");
        LOG.info("Start of ListCreationCrawler");
        LOG.info("------------------------------------");

        int numberOfResultPages = getNumberOfResultPagesForUrl(UrlBuilder.getListUrlForPage(0));

        for (int i=1; i<= numberOfResultPages; i++) {
            List<String> listOfExposeIds = getExposeIdsOnPageForUrl(UrlBuilder.getListUrlForPage(i));
            propertyService.createPropertiesBasedOnExposeIds(listOfExposeIds);
        }

        LOG.info("------------------------------------");
        LOG.info("Finish of ListCreationCrawler");
        LOG.info("------------------------------------");
    }

    private int getNumberOfResultPagesForUrl(String searchPageUrl) {
        try {
            Document searchPage = Jsoup.connect(searchPageUrl).get();
            int numberOfResultPages = searchPage.select("div[id=\"pageSelection\"] > select > option").size();
            LOG.info("Number of result pages:" + numberOfResultPages + "(" + searchPageUrl + ")");
            return numberOfResultPages;
        } catch (IOException e) {
            return 0;
        }
    }

    private List<String> getExposeIdsOnPageForUrl(String searchPageUrl) {
        List<String> resultList = new ArrayList<>();
        try {
            Document searchPage = Jsoup.connect(searchPageUrl).get();
            Elements results = searchPage.select("article[data-item=\"result\"]");
            for (Element result : results) {
                String exposeId = result.attr("data-obid");
                resultList.add(exposeId);
            }
            return resultList;
        } catch (IOException e) {
            return resultList;
        }
    }
}
