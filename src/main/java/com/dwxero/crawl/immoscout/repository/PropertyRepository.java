package com.dwxero.crawl.immoscout.repository;

import com.dwxero.crawl.immoscout.entity.Property;
import com.dwxero.crawl.immoscout.entity.PropertyState;
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
