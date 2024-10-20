package com.example.UberDemoProject.repo;

import com.example.UberDemoProject.model.Taxi;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaxiRepository extends JpaRepository<Taxi,Long> {

    @Query("SELECT t FROM Taxi t WHERE t.available = true " +
            "AND t.latitude BETWEEN :minLat AND :maxLat " +
            "AND t.longitude BETWEEN :minLng AND :maxLng")
    List<Taxi> findAvailableTaxisInArea(@Param("minLat") Double minLat,
                                        @Param("maxLat") Double maxLat,
                                        @Param("minLng") Double minLng,
                                        @Param("maxLng") Double maxLng);

}
