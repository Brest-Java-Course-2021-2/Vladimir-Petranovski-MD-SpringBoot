package com.epam.brest.rest.config;

import com.epam.brest.model.ModelSpecification;
import com.epam.brest.service_api.ModelSpecificationService;
import com.google.common.cache.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheModelSpecificationWithGuavaConfig {

    public static final Logger LOG = LogManager.getLogger(
            CacheModelSpecificationWithGuavaConfig.class);

    /**
     * @Fiel modelSpecificationService ModelSpecificationService.
     */

    private final ModelSpecificationService modelSpecificationService;

    /**
     * @Constructor CacheModelSpecificationWithGuavaConfig.
     *
     * @param modelSpecificationService ModelSpecificationService.
     */

    public CacheModelSpecificationWithGuavaConfig(
            final ModelSpecificationService modelSpecificationService) {
        this.modelSpecificationService = modelSpecificationService;
    }

    private LoadingCache<String, ModelSpecification> cache = CacheBuilder.newBuilder()
            .maximumSize(50)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .removalListener(new RemovalListener<String, ModelSpecification> () {
                @Override
                public void onRemoval(RemovalNotification<String, ModelSpecification> removalNotification) {
                    LOG.warn("Method  onRemoval() started in class {}", getClass().getName());
                    LOG.warn("Removed entry: {} -> {}", removalNotification.getKey(), removalNotification.getValue());
                    LOG.warn("Caused: {}", removalNotification.getCause().name());
                }
            })
            .recordStats()
            .build(new CacheLoader<String, ModelSpecification>() {
                @Override
                public ModelSpecification load(String s) throws Exception {
                    LOG.info("Method  load() started in class {}", getClass().getName());
                    return modelSpecificationService.getModelSpecificationByCarModel(s);
                }
            });
    public void cacheRun(String key) {
        try {
            cache.get(key);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    public CacheStats getCacheStats() {
        return cache.stats();
    }

    public void printMap() {
        LOG.warn("{}", cache.asMap().toString());
    }
}
