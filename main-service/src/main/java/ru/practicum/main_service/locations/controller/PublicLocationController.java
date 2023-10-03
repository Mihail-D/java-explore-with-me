package ru.practicum.main_service.locations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_service.locations.dto.LocationResponseDto;
import ru.practicum.main_service.locations.dto.NewLocationDto;
import ru.practicum.main_service.locations.service.LocationService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class PublicLocationController {

    private final LocationService locationService;

    @GetMapping("/{id}")
    public LocationResponseDto getLocation(@PathVariable long id) {
        return locationService.getLocation(id);
    }

    @GetMapping
    public List<LocationResponseDto> getLocations(Pageable pageable) {
        return locationService.getLocations(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationResponseDto createLocationByUser(@RequestBody @Valid NewLocationDto newLocationDto) {
        return locationService.createLocation(newLocationDto, false);
    }
}