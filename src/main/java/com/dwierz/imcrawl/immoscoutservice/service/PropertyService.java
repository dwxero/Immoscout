package com.dwierz.imcrawl.immoscoutservice.service;

import com.dwierz.imcrawl.immoscoutservice.entity.Property;
import com.dwierz.imcrawl.immoscoutservice.entity.PropertyState;
import com.dwierz.imcrawl.immoscoutservice.helper.UrlBuilder;
import com.dwierz.imcrawl.immoscoutservice.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PropertyService {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyService.class);


    private static final int DEFAULT_MAX_NUMBER_OF_PROPERTIES = 10;

    private PropertyRepository repository;

    @Autowired
    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public void createPropertiesBasedOnExposeIds(List<String> exposeIds) {
        for (String exposeId : exposeIds) {
            // check if property is already existing
            Property propertyInDb = repository.findByExposeId(exposeId);
            if (propertyInDb == null) {
                Property property = new Property();
                property.setUrl(UrlBuilder.getDetailUrlForExposeId(exposeId));
                property.setState(PropertyState.NEW);
                property.setLastUpdate(new Date());
                property.setExposeId(exposeId);
                repository.save(property);
                LOG.info("Created new property in db: " + property);
            }
        }
    }

    public List<Property> getNewProperties() {
        return getPropertiesByState(PropertyState.NEW, DEFAULT_MAX_NUMBER_OF_PROPERTIES);
    }

    public List<Property> getDownloadedProperties() {
        return getPropertiesByState(PropertyState.DOWNLOADED, DEFAULT_MAX_NUMBER_OF_PROPERTIES);
    }

    public List<Property> getPropertiesByState(PropertyState state, int maxNumberOfProperties) {
        return repository.findByState(state, new PageRequest(0, maxNumberOfProperties));
    }

    public void updateProperty(Property property) {
        property.setLastUpdate(new Date());
        repository.save(property);
        LOG.info("Updated property in db: " + property);
    }
}
