package de.telran.urlshortener.repository;

import de.telran.urlshortener.dto.statistics.TopBindingStatisticsResponse;
import de.telran.urlshortener.dto.statistics.TopRecord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository//Аннотация @Repository в Java используется для обозначения классов,
// реализующих функциональность DAO (Data Access Object). Она является частью
// Spring Framework и упрощает работу с хранилищами данных.
public class StatisticsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TopBindingStatisticsResponse getTopUrlBinding(int limit) {
        List<Tuple> results = entityManager.createQuery(
                        "SELECT ub.id as bindingId, u.id as userId, ub.count as count " +
                                "FROM UrlBinding ub JOIN ub.user u " +
                                "WHERE ub.count IS NOT NULL " +
                                "ORDER BY ub.count DESC ", Tuple.class)
                .setMaxResults(limit)
                .getResultList();

        List<TopRecord> topRecords = results.stream()
                .map(tuple -> new TopRecord(
                        tuple.get("bindingId", Long.class),
                        tuple.get("userId", Long.class),
                        tuple.get("count", Long.class)))
                .collect(Collectors.toList());

        return new TopBindingStatisticsResponse(topRecords);
    }
}
