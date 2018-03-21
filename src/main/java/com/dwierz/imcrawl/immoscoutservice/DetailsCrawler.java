package com.dwierz.imcrawl.immoscoutservice;

import com.dwierz.imcrawl.immoscoutservice.entity.Property;
import com.dwierz.imcrawl.immoscoutservice.entity.PropertyState;
import com.dwierz.imcrawl.immoscoutservice.service.PropertyService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * this class crawls the detail information pages for the given properties in the database.
 * the crawler only stores the raw html code in the property.
 */
@Component
public class DetailsCrawler implements Crawler {

    private static final Logger LOG = LoggerFactory.getLogger(ListCreationCrawler.class);

    private PropertyService propertyService;

    @Autowired
    public DetailsCrawler(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    @Scheduled(fixedDelay = 10 * 1000)
    public void crawl() {
        LOG.info("------------------------------------");
        LOG.info("Start of DetailsCrawler");
        LOG.info("------------------------------------");

        List<Property> listOfProperties = propertyService.getNewProperties();
        for (Property property : listOfProperties) {
            property = getInformationFromDetailsPage(property);
            propertyService.updateProperty(property);
        }

        LOG.info("------------------------------------");
        LOG.info("Finish of DetailsCrawler");
        LOG.info("------------------------------------");
    }

    private Property getInformationFromDetailsPage(Property property) {
        try {
            LOG.info("Fetching information for property:" + property);
            Connection.Response response = Jsoup.connect(property.getUrl()).method(Connection.Method.GET).execute();
            if (response.statusCode() == 200) {
                Document detailsPage = response.parse();
                property.setRawHtml(detailsPage.html());
                property.setState(PropertyState.DOWNLOADED);
                return property;
            } else {
                property.setState(PropertyState.OFFLINE);
                return property;
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return property;
        }
    }
}
