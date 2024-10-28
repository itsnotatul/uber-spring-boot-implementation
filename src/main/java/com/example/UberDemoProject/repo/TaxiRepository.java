package com.example.UberDemoProject.repo;

import com.example.UberDemoProject.model.Taxi;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi,Long> {

    @Query(value = "SELECT t FROM Taxi t WHERE t.available = true " +
            "AND t.latitude BETWEEN :minLat AND :maxLat " +
            "AND t.longitude BETWEEN :minLng AND :maxLng", nativeQuery = true)
    List<Taxi> findAvailableTaxisInArea(@Param("minLat") Double minLat,
                                        @Param("maxLat") Double maxLat,
                                        @Param("minLng") Double minLng,
                                        @Param("maxLng") Double maxLng);

    Optional<Taxi> findById(Long id);

}
