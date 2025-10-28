package com.PedroA10.GeoAPI.mapper;

import com.PedroA10.GeoAPI.dto.PointsRequestDTO;
import com.PedroA10.GeoAPI.dto.PointsResponseDTO;
import com.PedroA10.GeoAPI.model.Points;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PointsMapper {

  PointsResponseDTO toResponseDTO(Points points);

  Points toEntity(PointsRequestDTO dto);

  List<PointsResponseDTO> toResponseDTOList(List<Points> points);

  void updateEntityFromDTO(PointsRequestDTO dto, @MappingTarget Points entity);
}
