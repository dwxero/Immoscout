package com.dwxero.crawl.immoscout;

public interface Crawler {

    /**
     * this is the main method to crawl the specific data.
     * each implementation for this interface takes also care
     * about getting and storing the required data.
     */
    void crawl();
}
