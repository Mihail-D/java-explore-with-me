package ru.practicum.main_service.locations.controller;

import org.junit.Test;
import org.mockito.Mockito;
import ru.practicum.main_service.exception.ConflictException;
import ru.practicum.main_service.locations.dto.LocationResponseDto;
import ru.practicum.main_service.locations.dto.NewLocationDto;
import ru.practicum.main_service.locations.dto.UpdateLocationDto;
import ru.practicum.main_service.locations.model.LocationStatus;
import ru.practicum.main_service.locations.service.LocationService;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdminLocationControllerTest {


    @Test
    public void shouldCreateLocationWithValidInput() {
        LocationService locationService = Mockito.mock(LocationService.class);

        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(37.7749f)
                .lon(-122.4194f)
                .name("San Francisco")
                .radius(10.0f)
                .build();

        AdminLocationController adminLocationController = new AdminLocationController(locationService);

        LocationResponseDto locationResponseDto = LocationResponseDto.builder()
                .id(1)
                .lat(37.7749f)
                .lon(-122.4194f)
                .name("San Francisco")
                .radius(10.0f)
                .status(LocationStatus.PENDING)
                .build();
        Mockito.when(locationService.createLocation(newLocationDto, true)).thenReturn(locationResponseDto);

        LocationResponseDto result = adminLocationController.createLocation(newLocationDto);
        assertEquals(locationResponseDto, result);
    }

    @Test
    public void shouldUpdateLocationWithValidInput() {
        LocationService locationService = Mockito.mock(LocationService.class);

        UpdateLocationDto updateLocationDto = UpdateLocationDto.builder()
                .lat(37.7749f)
                .lon(-122.4194f)
                .name("San Francisco")
                .radius(10.0f)
                .build();

        AdminLocationController adminLocationController = new AdminLocationController(locationService);

        LocationResponseDto locationResponseDto = LocationResponseDto.builder()
                .id(1)
                .lat(37.7749f)
                .lon(-122.4194f)
                .name("San Francisco")
                .radius(10.0f)
                .status(LocationStatus.PENDING)
                .build();
        Mockito.when(locationService.updateLocation(1, updateLocationDto)).thenReturn(locationResponseDto);

        LocationResponseDto result = adminLocationController.updateLocation(1, updateLocationDto);
        assertEquals(locationResponseDto, result);
    }

    @Test
    public void shouldDeleteLocationWithValidInput() {
        LocationService locationService = Mockito.mock(LocationService.class);

        AdminLocationController adminLocationController = new AdminLocationController(locationService);

        adminLocationController.deleteLocation(1);

        Mockito.verify(locationService).deleteLocation(1);
    }

    @Test
    public void shouldCreateLocationWithInvalidInput() {
        LocationService locationService = Mockito.mock(LocationService.class);

        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(null)
                .lon(-122.4194f)
                .name("")
                .radius(10.0f)
                .build();

        Mockito.when(locationService.createLocation(newLocationDto, true)).thenThrow(ConflictException.class);

        AdminLocationController adminLocationController = new AdminLocationController(locationService);

        assertThrows(ConflictException.class, () -> {
            adminLocationController.createLocation(newLocationDto);
        });
    }


    @Test
    public void shouldUpdateLocationWithInvalidInput() {
        LocationService locationService = Mockito.mock(LocationService.class);

        UpdateLocationDto updateLocationDto = UpdateLocationDto.builder()
                .lat(100.0f)
                .lon(-200.0f)
                .name("San Francisco")
                .radius(-10.0f)
                .build();

        Mockito.when(locationService.updateLocation(1L, updateLocationDto)).thenThrow(ConflictException.class);

        AdminLocationController adminLocationController = new AdminLocationController(locationService);

        assertThrows(ConflictException.class, () -> {
            adminLocationController.updateLocation(1L, updateLocationDto);
        });
    }


    @Test
    public void shouldUpdateNonExistentLocation() {
        LocationService locationService = Mockito.mock(LocationService.class);

        UpdateLocationDto updateLocationDto = UpdateLocationDto.builder()
                .lat(37.7749f)
                .lon(-122.4194f)
                .name("San Francisco")
                .radius(10.0f)
                .build();

        Mockito.when(locationService.updateLocation(1L, updateLocationDto)).thenThrow(NoSuchElementException.class);

        AdminLocationController adminLocationController = new AdminLocationController(locationService);

        assertThrows(NoSuchElementException.class, () -> {
            adminLocationController.updateLocation(1L, updateLocationDto);
        });
    }


}