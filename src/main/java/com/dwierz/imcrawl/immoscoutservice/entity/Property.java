package com.dwierz.imcrawl.immoscoutservice.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Property {

    @Id
    private String id;

    private String url;
    private PropertyState state;
    private Date lastUpdate;
    private String exposeId;
    private String rawHtml;

    private String title;
    private String address;
    private double price;
    private double rooms;
    private double bathrooms;
    private double bedrooms;
    private double squareMetersLiving;
    private double squareMetersLand;
    private String commissionNote;


    public Property() {}

    public Property(String url, PropertyState state) {
        this.url = url;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PropertyState getState() {
        return state;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setState(PropertyState state) {
        this.state = state;
    }

    public String getExposeId() {
        return exposeId;
    }

    public void setExposeId(String exposeId) {
        this.exposeId = exposeId;
    }

    public String getRawHtml() {
        return rawHtml;
    }

    public void setRawHtml(String rawHtml) {
        this.rawHtml = rawHtml;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRooms() {
        return rooms;
    }

    public void setRooms(double rooms) {
        this.rooms = rooms;
    }

    public double getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(double bathrooms) {
        this.bathrooms = bathrooms;
    }

    public double getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(double bedrooms) {
        this.bedrooms = bedrooms;
    }

    public double getSquareMetersLiving() {
        return squareMetersLiving;
    }

    public void setSquareMetersLiving(double squareMetersLiving) {
        this.squareMetersLiving = squareMetersLiving;
    }

    public double getSquareMetersLand() {
        return squareMetersLand;
    }

    public void setSquareMetersLand(double squareMetersLand) {
        this.squareMetersLand = squareMetersLand;
    }

    public String getCommissionNote() {
        return commissionNote;
    }

    public void setCommissionNote(String commissionNote) {
        this.commissionNote = commissionNote;
    }

    @Override
    public String toString() {
        return String.format(
                "Property[id=%s, url='%s', state='%s']",
                id, url, state);
    }
}
