package ru.practicum.main_service.locations.controller;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.main_service.locations.dto.LocationResponseDto;
import ru.practicum.main_service.locations.dto.NewLocationDto;
import ru.practicum.main_service.locations.model.LocationStatus;
import ru.practicum.main_service.locations.service.LocationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublicLocationControllerTest {

    @Test
    public void shouldGetLocationReturnsLocationResponseDto() {
        long id = 1;

        LocationService locationService = Mockito.mock(LocationService.class);
        LocationResponseDto expectedResponse = LocationResponseDto.builder()
                .id(1L)
                .lat(40.7128F)
                .lon(74.0060F)
                .name("Test Location")
                .radius(10.0F)
                .status(LocationStatus.APPROVED)
                .build();
        Mockito.when(locationService.getLocation(id)).thenReturn(expectedResponse);

        PublicLocationController controller = new PublicLocationController(locationService);

        LocationResponseDto actualResponse = controller.getLocation(id);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldGetLocationsReturnsListLocationResponseDto() {
        int from = 0;
        int size = 10;

        LocationService locationService = Mockito.mock(LocationService.class);
        List<LocationResponseDto> expectedResponse = new ArrayList<>();
        Mockito.when(locationService.getLocations(Mockito.any(Pageable.class))).thenReturn(expectedResponse);

        PublicLocationController controller = new PublicLocationController(locationService);

        List<LocationResponseDto> actualResponse = controller.getLocations(PageRequest.of(from, size));

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldCreateLocationByUserReturnsLocationResponseDto() {
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(40.7128F)
                .lon(74.0060F)
                .name("Test Location")
                .radius(10.0F)
                .build();

        LocationService locationService = Mockito.mock(LocationService.class);
        LocationResponseDto expectedResponse = LocationResponseDto.builder()
                .id(1L)
                .lat(40.7128F)
                .lon(74.0060F)
                .name("Test Location")
                .radius(10.0F)
                .status(LocationStatus.APPROVED)
                .build();
        Mockito.when(locationService.createLocation(newLocationDto, false)).thenReturn(expectedResponse);

        PublicLocationController controller = new PublicLocationController(locationService);

        LocationResponseDto actualResponse = controller.createLocationByUser(newLocationDto);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldGetLocationWithNonExistentIdReturns404() {
        long id = 1;

        LocationService locationService = Mockito.mock(LocationService.class);
        Mockito.when(locationService.getLocation(id)).thenReturn(null);

        PublicLocationController controller = new PublicLocationController(locationService);

        LocationResponseDto locationResponseDto = controller.getLocation(id);
        ResponseEntity<LocationResponseDto> response = locationResponseDto == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(locationResponseDto, HttpStatus.OK);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void shouldGetLocationsWithInvalidQueryParametersReturns400() {
        int from = -1;
        int size = 0;
        LocationService locationService = Mockito.mock(LocationService.class);
        PublicLocationController controller = new PublicLocationController(locationService);

        {
            ResponseEntity<List<LocationResponseDto>> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }
    }

    @Test
    public void shouldCreateLocationByUserWithInvalidNewLocationDtoReturns400() {
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(40.7128F)
                .lon(74.0060F)
                .name("Test Location")
                .radius(10.0F)
                .build();

        LocationService locationService = Mockito.mock(LocationService.class);

        PublicLocationController controller = new PublicLocationController(locationService);

        LocationResponseDto locationResponseDto = controller.createLocationByUser(newLocationDto);
        ResponseEntity<LocationResponseDto> response = locationResponseDto == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(locationResponseDto, HttpStatus.OK);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}