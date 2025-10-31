package com.PedroA10.GeoAPI.service;

import com.PedroA10.GeoAPI.dto.pointsdto.PointsRequestDTO;
import com.PedroA10.GeoAPI.dto.pointsdto.PointsResponseDTO;
import com.PedroA10.GeoAPI.exception.PointNotFoundException;
import com.PedroA10.GeoAPI.mapper.PointsMapper;
import com.PedroA10.GeoAPI.model.Points;
import com.PedroA10.GeoAPI.repository.PointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;

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
    return pointsMapper.toResponseDTOList(pointsRepository.findAll());
  }

  public PointsResponseDTO findById(String id) {
    Points  pointsOPT = pointsRepository.findById(id)
      .orElseThrow(() -> new PointNotFoundException("Point not found with id " + id));
    return pointsMapper.toResponseDTO(pointsOPT);
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
    Points existingPoint = pointsRepository.findById(id)
      .orElseThrow(() -> new PointNotFoundException("Point not found with id " + id));

    pointsMapper.updateEntityFromDTO(requestDTO, existingPoint);
    Points updatePoint = pointsRepository.save(existingPoint);
    return pointsMapper.toResponseDTO(updatePoint);
  }

  public void deletePoint(String id) {
    if (!pointsRepository.existsById(id)) {
      throw new PointNotFoundException("Point not found with id " + id);
    }
    pointsRepository.deleteById(id);
  }
}
