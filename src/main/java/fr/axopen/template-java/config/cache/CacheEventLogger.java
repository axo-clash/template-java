package fr.axopen.template-java.config.cache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CacheEventLogger implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        log.info("Cache event {} for item with key {}", cacheEvent.getType(), cacheEvent.getKey());
    }
}
