package com.PedroA10.GeoAPI.dto.pointsdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointsRequestDTO {

  private String nameLocale;
  private String description;
  private String address;
  private String city;
  private String state;
  private String country;
  private String category;
  private double rating;
  private boolean active;

  private GeoJsonPoint point;
  private GeoJsonPolygon area;

  private List<String> tags;
}
