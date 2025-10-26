package com.PedroA10.GeoAPI.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "points")
@CompoundIndexes({
  @CompoundIndex(name = "point_2dsphere", def = "{'point': '2dsphere'}")
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Points {

  @Id
  private String id;

  private String nameLocale;
  private String description;
  private String address;
  private String city;
  private String state;
  private String country;
  private String category;
  private double rating;
  private int reviewsCount;
  private boolean active;

  private GeoJsonPoint point;
  private GeoJsonPolygon area;

  private List<String> tags;
  private double popularity;
}
