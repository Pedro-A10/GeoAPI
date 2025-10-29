package com.PedroA10.GeoAPI.service;

import com.PedroA10.GeoAPI.dto.PointsRequestDTO;
import com.PedroA10.GeoAPI.dto.PointsResponseDTO;
import com.PedroA10.GeoAPI.mapper.PointsMapper;
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

  @Autowired
  private PointsMapper pointsMapper;

  public PointsResponseDTO createPoint(PointsRequestDTO point) {
    Points points = pointsMapper.toEntity(point);
    Points savePoint = pointsRepository.save(points);
    return pointsMapper.toResponseDTO(savePoint);
  }

  public List<PointsResponseDTO> findAll() {
    List<Points> pointsList =  pointsRepository.findAll();
    return pointsMapper.toResponseDTOList(pointsList);
  }

  public Optional<PointsResponseDTO> findById(String id) {
    Optional<Points> pointsOPT = pointsRepository.findById(id);
    return pointsOPT.map(pointsMapper::toResponseDTO);
  }

  public List<PointsResponseDTO> findByName(String name) {
    List<Points> listNameLocale = pointsRepository.findByNameLocale(name);
    return pointsMapper.toResponseDTOList(listNameLocale);
  }

  public List<PointsResponseDTO> getPointsByCity(String city) {
    List<Points> listCityName = pointsRepository.findByCity(city);
    return pointsMapper.toResponseDTOList(listCityName);
  }

  public List<PointsResponseDTO> getPointsByState(String state) {
    List<Points> listStateName = pointsRepository.findByState(state);
    return pointsMapper.toResponseDTOList(listStateName);
  }

  public List<PointsResponseDTO> getPointsByCountry(String country) {
    List<Points> listCountryName = pointsRepository.findByCountry(country);
    return pointsMapper.toResponseDTOList(listCountryName);
  }

  public List<PointsResponseDTO> getPointsByAddress(String address) {
    List<Points> addressName = pointsRepository.findByAddress(address);
    return pointsMapper.toResponseDTOList(addressName);
  }

  public List<PointsResponseDTO> getPointsByCategory(String category) {
    List<Points> listByCategory = pointsRepository.findByCategory(category);
    return pointsMapper.toResponseDTOList(listByCategory);
  }

  public List<PointsResponseDTO> getPointsByTag(String tag) {
    List<Points> listTags = pointsRepository.findByTagsContaining(tag);
    return pointsMapper.toResponseDTOList(listTags);
  }

  public List<PointsResponseDTO> getActivePoints() {
    List<Points> activePoints = pointsRepository.findByActive(true);
    return pointsMapper.toResponseDTOList(activePoints);
  }

  public List<PointsResponseDTO> getPointsByRating(double rating) {
    List<Points> listByRating = pointsRepository.findByRating(rating);
    return pointsMapper.toResponseDTOList(listByRating);
  }

  public List<PointsResponseDTO> getPointsWithinPolygon(Object geoJsonPolygon) {
    List<Points> pointsWithinPolygon = pointsRepository.findPointsWithinPolygon(geoJsonPolygon);
    return pointsMapper.toResponseDTOList(pointsWithinPolygon);
  }

  public List<PointsResponseDTO> getPointsNearby(double longitude, double latitude, double radiusKm) {
    Point location = new Point(longitude, latitude);
    Distance distance = new Distance(radiusKm, org.springframework.data.geo.Metrics.KILOMETERS);
    List<Points> nearbyPoints = pointsRepository.findByPointNear(location, distance);
    return pointsMapper.toResponseDTOList(nearbyPoints);
  }

  public PointsResponseDTO updatePoint(String id, PointsRequestDTO requestDTO) {
    Optional<Points> pointsOPT = pointsRepository.findById(id);
    if (pointsOPT.isEmpty()) {
      throw new RuntimeException("Point not found");
    }

    Points existingPoint = pointsOPT.get();
    pointsMapper.updateEntityFromDTO(requestDTO, existingPoint);
    existingPoint.setId(id);
    Points updatedPoint = pointsRepository.save(existingPoint);
    return pointsMapper.toResponseDTO(updatedPoint);
  }

  public void deletePoint(String id) {
    pointsRepository.deleteById(id);
  }
}
