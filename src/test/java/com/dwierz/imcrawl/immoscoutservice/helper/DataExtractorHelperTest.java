package com.dwierz.imcrawl.immoscoutservice.helper;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataExtractorHelperTest {

    private String html;

    @Before
    public void loadExampleHtmlFile() throws Exception {
        this.html = IOUtils.toString(this.getClass().getResourceAsStream("exampleRawHtml.html"),
                "UTF-8"
        );
    }

    @Test
    public void testThatTitleExtractionWorks() {
        // given
        String title;
        DataExtractorHelper helper = new DataExtractorHelper();

        // when
        title = helper.getTitleFromHtml(html);

        // then
        assertNotNull(title);
        assertTrue("Wohntraum auf großem Eckgrundstück im noblen Junkersdorf".equals(title));
    }

    @Test
    public void testThatAddressExtractionWorks() {
        // given
        String address;
        DataExtractorHelper helper = new DataExtractorHelper();

        // when
        address = helper.getAddressFromHtml(html);

        // then
        assertNotNull(address);
        assertTrue("Amselstraße 19, 50858 Köln, Junkersdorf".equals(address));
    }

    @Test
    public void testThatPriceExtractionWorks() {
        // given
        double price;
        DataExtractorHelper helper = new DataExtractorHelper();

        // when
        price = helper.getPriceFromHtml(html);

        // then
        assertEquals(1275000.00d, price, 0.001d);
    }

    @Test
    public void testThatRoomsExtractionWorks() {
        // given
        double rooms;
        DataExtractorHelper helper = new DataExtractorHelper();

        // when
        rooms = helper.getRoomsFromHtml(html);

        // then
        assertEquals(5.0d, rooms, 0.01d);
    }

    @Test
    public void testThatBedroomsExtractionWorks() {
        // given
        double bedrooms;
        DataExtractorHelper helper = new DataExtractorHelper();

        // when
        bedrooms = helper.getBedroomsFromHtml(html);

        // then
        assertEquals(4.0d, bedrooms, 0.01d);
    }

    @Test
    public void testThatBathroomsExtractionWorks() {
        // given
        double bathrooms;
        DataExtractorHelper helper = new DataExtractorHelper();

        // when
        bathrooms = helper.getBathroomsFromHtml(html);

        // then
        assertEquals(1.0d, bathrooms, 0.01d);
    }

    @Test
    public void testThatSquareMetersLivingExtractionWorks() {
        // given
        double squareMetersLiving;
        DataExtractorHelper helper = new DataExtractorHelper();

        // when
        squareMetersLiving = helper.getSquareMetersLivingFromHtml(html);

        // then
        assertEquals(172.0d, squareMetersLiving, 0.001d);
    }


    @Test
    public void testThatSquareMetersLandExtractionWorks() {
        // given
        double squareMetersLand;
        DataExtractorHelper helper = new DataExtractorHelper();

        // when
        squareMetersLand = helper.getSquareMetersLandFromHtml(html);

        // then
        assertEquals(539.0d, squareMetersLand, 0.001d);
    }

    @Test
    public void testThatCommissionNoteExtractionWorks() {
        // given
        String commissionNote;
        DataExtractorHelper helper = new DataExtractorHelper();

        // when
        commissionNote = helper.getCommissionNoteFromHtml(html);

        // then
        assertTrue("Nein".equals(commissionNote));
    }
}