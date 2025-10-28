package com.PedroA10.GeoAPI.controller;

import com.PedroA10.GeoAPI.model.Points;
import com.PedroA10.GeoAPI.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locates")
public class PointsController {

  @Autowired
  PointsService pointsService;

  @GetMapping
  public ResponseEntity<List<Points>> listPoints() {
    return ResponseEntity.ok(pointsService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Points> listById(@PathVariable String id) {
    Optional<Points> point = pointsService.findById(id);
    return point.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/by-name")
  public ResponseEntity<List<Points>> listByName(@RequestParam String name) {
    return ResponseEntity.ok(pointsService.findByName(name));
  }

  @GetMapping("/by-city")
  public ResponseEntity<List<Points>> listByCity(@RequestParam String city) {
    return ResponseEntity.ok(pointsService.getPointsByCity(city));
  }

  @GetMapping("/by-state")
  public ResponseEntity<List<Points>> listByState(@RequestParam String state) {
    return ResponseEntity.ok(pointsService.getPointsByState(state));
  }

  @GetMapping("/by-country")
  public ResponseEntity<List<Points>> listByCountry(@RequestParam String country) {
    return ResponseEntity.ok(pointsService.getPointsByCountry(country));
  }

  @GetMapping("/by-category")
  public ResponseEntity<List<Points>> listByCategory(@RequestParam String category) {
    return ResponseEntity.ok(pointsService.getPointsByCategory(category));
  }

  @GetMapping("/by-tag")
  public ResponseEntity<List<Points>> listByTags (@RequestParam String tags) {
    return ResponseEntity.ok(pointsService.getPointsByTag(tags));
  }

  @GetMapping("/active")
  public ResponseEntity<List<Points>> listActivePoints(){
    List<Points> activePoints =pointsService.getActivePoints();
    return ResponseEntity.ok(activePoints);
  }

  @GetMapping("/rating")
  public ResponseEntity<List<Points>> listByRating(@RequestParam double rating) {
    return ResponseEntity.ok(pointsService.getPointsByRating(rating));
  }

  @PostMapping("/within-polygon")
  public ResponseEntity<List<Points>> getPointsWithinPolygon(@RequestBody Object geoJsonPolygon) {
    return ResponseEntity.ok(pointsService.getPointsWithinPolygon(geoJsonPolygon));
  }

  @GetMapping("/nearby")
  public ResponseEntity<List<Points>> listNearbyPoints(
                                       @RequestParam double longitude,
                                       @RequestParam double latitude,
                                       @RequestParam double radiusKm) {
    return ResponseEntity.ok(pointsService.getPointsNearby(longitude, latitude, radiusKm));
  }

  @PostMapping
  public ResponseEntity<Points> createPoints(@RequestBody Points points) {

    try {
      Points newPoint = pointsService.createPoint(points);
      return new ResponseEntity<>(newPoint, HttpStatus.CREATED);
    }catch (IllegalArgumentException e){
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Points> updatePoints(@PathVariable String id, @RequestBody Points updatePoint) {
    try {
      Points result = pointsService.updatePoint(id, updatePoint);
      return ResponseEntity.ok(result);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePoints(@PathVariable String id) {
    try {
      pointsService.deletePoint(id);
      return ResponseEntity.noContent().build();
    }catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
