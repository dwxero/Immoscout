package com.dwierz.imcrawl.immoscoutservice;

import com.dwierz.imcrawl.immoscoutservice.entity.Property;
import com.dwierz.imcrawl.immoscoutservice.entity.PropertyState;
import com.dwierz.imcrawl.immoscoutservice.helper.DataExtractorHelper;
import com.dwierz.imcrawl.immoscoutservice.service.PropertyService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * this crawler extracts the required property detail information from the raw html code which is stored in the database.
 */
@Component
public class DetailsDataExtractor implements Crawler {


    private static final Logger LOG = LoggerFactory.getLogger(ListCreationCrawler.class);

    private PropertyService propertyService;

    @Autowired
    public DetailsDataExtractor(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    @Scheduled(fixedDelay = 10 * 1000)
    public void crawl() {
        LOG.info("------------------------------------");
        LOG.info("Start of DetailsDataExtractor");
        LOG.info("------------------------------------");

        List<Property> listOfProperties = propertyService.getDownloadedProperties();
        for (Property property : listOfProperties) {
            property = getInformationFromRawHtml(property);
            property.setState(PropertyState.EXTRACTED);
            propertyService.updateProperty(property);
        }

        LOG.info("------------------------------------");
        LOG.info("Finish of DetailsDataExtractor");
        LOG.info("------------------------------------");
    }

    private Property getInformationFromRawHtml(Property property) {
        LOG.info("Extracting data for property:" + property);

        String rawHtml = property.getRawHtml();
        DataExtractorHelper helper = new DataExtractorHelper();

        property.setTitle(helper.getTitleFromHtml(rawHtml));
        property.setAddress(helper.getAddressFromHtml(rawHtml));
        property.setPrice(helper.getPriceFromHtml(rawHtml));
        property.setRooms(helper.getRoomsFromHtml(rawHtml));
        property.setBedrooms(helper.getBedroomsFromHtml(rawHtml));
        property.setBathrooms(helper.getBathroomsFromHtml(rawHtml));
        property.setSquareMetersLiving(helper.getSquareMetersLivingFromHtml(rawHtml));
        property.setSquareMetersLand(helper.getSquareMetersLandFromHtml(rawHtml));
        property.setCommissionNote(helper.getCommissionNoteFromHtml(rawHtml));
        return property;
    }
}
