package com.dwierz.imcrawl.immoscoutservice.repository;

import com.dwierz.imcrawl.immoscoutservice.entity.Property;
import com.dwierz.imcrawl.immoscoutservice.entity.PropertyState;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PropertyRepository extends MongoRepository<Property, String> {

    Property findById(String id);
    Property findByUrl(String url);
    Property findByExposeId(String exposeId);

    List<Property> findByState(PropertyState state);

    List<Property> findByState(PropertyState state, Pageable pageable);
}
