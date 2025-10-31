package com.PedroA10.GeoAPI.controller;

import com.PedroA10.GeoAPI.dto.pointsdto.PointsRequestDTO;
import com.PedroA10.GeoAPI.dto.pointsdto.PointsResponseDTO;
import com.PedroA10.GeoAPI.service.PointsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locates")
public class PointsController {

  @Autowired
  PointsService pointsService;

  @GetMapping
  public ResponseEntity<List<PointsResponseDTO>> listPoints() {
    return ResponseEntity.ok(pointsService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PointsResponseDTO> listById(@PathVariable String id) {
    return ResponseEntity.ok(pointsService.findById(id));
  }

  @GetMapping("/by-name")
  public ResponseEntity<List<PointsResponseDTO>> listByName(@RequestParam String name) {
    return ResponseEntity.ok(pointsService.findByName(name));
  }

  @GetMapping("/by-city")
  public ResponseEntity<List<PointsResponseDTO>> listByCity(@RequestParam String city) {
    return ResponseEntity.ok(pointsService.getPointsByCity(city));
  }

  @GetMapping("/by-state")
  public ResponseEntity<List<PointsResponseDTO>> listByState(@RequestParam String state) {
    return ResponseEntity.ok(pointsService.getPointsByState(state));
  }

  @GetMapping("/by-country")
  public ResponseEntity<List<PointsResponseDTO>> listByCountry(@RequestParam String country) {
    return ResponseEntity.ok(pointsService.getPointsByCountry(country));
  }

  @GetMapping("/by-category")
  public ResponseEntity<List<PointsResponseDTO>> listByCategory(@RequestParam String category) {
    return ResponseEntity.ok(pointsService.getPointsByCategory(category));
  }

  @GetMapping("/by-address")
  public ResponseEntity<List<PointsResponseDTO>> listByAddressName(@RequestParam String address) {
    return ResponseEntity.ok(pointsService.getPointsByAddress(address));
  }

  @GetMapping("/by-tag")
  public ResponseEntity<List<PointsResponseDTO>> listByTags (@RequestParam String tags) {
    return ResponseEntity.ok(pointsService.getPointsByTag(tags));
  }

  @GetMapping("/active")
  public ResponseEntity<List<PointsResponseDTO>> listActivePoints(){
    List<PointsResponseDTO> activePoints =pointsService.getActivePoints();
    return ResponseEntity.ok(activePoints);
  }

  @GetMapping("/rating")
  public ResponseEntity<List<PointsResponseDTO>> listByRating(@RequestParam double rating) {
    return ResponseEntity.ok(pointsService.getPointsByRating(rating));
  }

  @PostMapping("/within-polygon")
  public ResponseEntity<List<PointsResponseDTO>> getPointsWithinPolygon(@RequestBody Object geoJsonPolygon) {
    return ResponseEntity.ok(pointsService.getPointsWithinPolygon(geoJsonPolygon));
  }

  @GetMapping("/nearby")
  public ResponseEntity<List<PointsResponseDTO>> listNearbyPoints(
                                       @RequestParam double longitude,
                                       @RequestParam double latitude,
                                       @RequestParam double radiusKm) {
    return ResponseEntity.ok(pointsService.getPointsNearby(longitude, latitude, radiusKm));
  }

  @PostMapping
  public ResponseEntity<PointsResponseDTO> createPoints(@RequestBody @Valid PointsRequestDTO points) {
    PointsResponseDTO newPoint = pointsService.createPoint(points);
    return new ResponseEntity<>(newPoint, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PointsResponseDTO> updatePoints(@PathVariable String id, @RequestBody @Valid PointsRequestDTO updatePoint) {
    PointsResponseDTO result = pointsService.updatePoint(id, updatePoint);
    return ResponseEntity.ok(result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePoints(@PathVariable String id) {
    pointsService.deletePoint(id);
    return ResponseEntity.noContent().build();
  }
}
