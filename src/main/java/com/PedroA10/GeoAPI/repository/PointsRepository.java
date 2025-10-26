package com.PedroA10.GeoAPI.repository;


import com.PedroA10.GeoAPI.model.Points;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PointsRepository extends MongoRepository<Points, String> {

  List<Points> findByNameLocale(String nameLocale);

  List<Points> localeActive(boolean active);

  List<Points> findByRating(double ranting);

  List<Points> findByTagsContaining(String tags);

  List<Points> findByAddress(String address);

  List<Points> findByCategory(String category);

  @Query("{ 'point' : { $geoWithin : { $geometry : ?0 } } }")
  List<Points> findPointsWithinPolygon(Object geoJsonPolygon);
}
