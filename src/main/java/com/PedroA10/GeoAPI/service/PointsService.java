package com.PedroA10.GeoAPI.service;

import com.PedroA10.GeoAPI.model.Points;
import com.PedroA10.GeoAPI.repository.PointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PointsService {

  @Autowired
  PointsRepository pointsRepository;

  public Points createPoint(Points point) {
    return pointsRepository.save(point);
  }

  public List<Points> findAll() {
    return pointsRepository.findAll();
  }

  public Optional<Points> findById(String id) {
    return pointsRepository.findById(id);
  }

  public List<Points> findByName(String name) {
    return pointsRepository.findByNameLocale(name);
  }

  public List<Points> getPointsByCity(String city) {
    return pointsRepository.findByCity(city);
  }

  public List<Points> getPointsByState(String state) {
    return pointsRepository.findByState(state);
  }

  public List<Points> getPointsByCountry(String country) {
    return pointsRepository.findByCountry(country);
  }

  public List<Points> getPointsByCategory(String category) {
    return pointsRepository.findByCategory(category);
  }

  public List<Points> getPointsByTag(String tag) {
    return pointsRepository.findByTagsContaining(tag);
  }

  public List<Points> getActivePoints() {
    return pointsRepository.localeActive(true);
  }

  public List<Points> getPointsByRating(double rating) {
    return pointsRepository.findByRating(rating);
  }

  public List<Points> getPointsWithinPolygon(Object geoJsonPolygon) {
    return pointsRepository.findPointsWithinPolygon(geoJsonPolygon);
  }

  public List<Points> getPointsNearby(double longitude, double latitude, double radiusKm) {
    Point location = new Point(longitude, latitude);
    Distance distance = new Distance(radiusKm, org.springframework.data.geo.Metrics.KILOMETERS);
    return pointsRepository.findByPointNear(location, distance);
  }

  public Points updatePoint(String id, Points updatedPoint) {
    updatedPoint.setId(id);
    return pointsRepository.save(updatedPoint);
  }

  public void deletePoint(String id) {
    pointsRepository.deleteById(id);
  }
}
