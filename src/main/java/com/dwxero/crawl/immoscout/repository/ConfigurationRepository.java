package com.dwxero.crawl.immoscout.repository;

import com.dwxero.crawl.immoscout.entity.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigurationRepository extends MongoRepository<Configuration, String> {

    Configuration findByServiceName(String serviceName);
}
