package com.dwierz.imcrawl.immoscoutservice.repository;

import com.dwierz.imcrawl.immoscoutservice.entity.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigurationRepository extends MongoRepository<Configuration, String> {

    Configuration findByServiceName(String serviceName);
}
