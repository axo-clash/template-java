package fr.axopen.template-java.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheService {

    private final CacheManager cacheManager;

    @PersistenceContext
    private EntityManager entityManager;

    public void clearCacheByName(String name) {
        org.springframework.cache.Cache cache = cacheManager.getCache(name);

        if (cache != null) {
            cache.clear();
            log.info("Le cache {} a été clear", name);
        } else {
            log.info("Le cache {} n'existe pas", name);
        }
    }

    public void clearEntityCache(Class<?> clazz) {
        entityManager.getEntityManagerFactory().getCache().evict(clazz);
    }

    public void clearCache() {
        org.springframework.cache.Cache springCache;
        for (String name : cacheManager.getCacheNames()) {
            springCache = cacheManager.getCache(name);

            if (springCache != null) {
                springCache.clear();
            }
        }

        entityManager.getEntityManagerFactory().getCache().evictAll();
        Session session = entityManager.unwrap(Session.class);

        if (session != null) {
            session.clear();
        }

        try (SessionFactory sessionFactory = entityManager.getEntityManagerFactory().unwrap(SessionFactory.class)) {

            if (sessionFactory != null) {
                Cache hibernateCache = sessionFactory.getCache();

                if (hibernateCache != null) {
                    hibernateCache.evictAllRegions(); // Evict data from all query regions.
                }
            }
        }
    }

}
