package ru.practicum.main_service.locations.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.main_service.locations.dto.LocationResponseDto;
import ru.practicum.main_service.locations.dto.NewLocationDto;
import ru.practicum.main_service.locations.dto.UpdateLocationDto;

import java.util.List;

public interface LocationService {

    LocationResponseDto createLocation(NewLocationDto newLocationDto, boolean isAdmin);

    void deleteLocation(long id);

    LocationResponseDto updateLocation(long id, UpdateLocationDto updateLocationDto);

    LocationResponseDto getLocation(long id);

    List<LocationResponseDto> getLocations(Pageable pageable);

    LocationResponseDto confirmLocation(long id, boolean approved);
}