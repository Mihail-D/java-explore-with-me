package ru.practicum.main_service.locations.dto;

import org.junit.Test;
import ru.practicum.main_service.locations.model.Location;
import ru.practicum.main_service.locations.model.LocationStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LocationMapperTest {


    @Test
    public void shouldToLocationDto() {
        Location location = new Location(1, 12.34f, 56.78f, "Location 1", 10.0f, LocationStatus.PENDING);
        LocationDto locationDto = LocationMapper.toLocationDto(location);

        assertEquals(location.getLat(), locationDto.getLat(), 0.001);
        assertEquals(location.getLon(), locationDto.getLon(), 0.001);
    }

    @Test
    public void shouldToLocation() {
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(12.34f)
                .lon(56.78f)
                .name("New Location")
                .radius(10.0f)
                .build();

        Location location = LocationMapper.toLocation(newLocationDto);

        assertEquals(newLocationDto.getLat(), location.getLat(), 0.001);
        assertEquals(newLocationDto.getLon(), location.getLon(), 0.001);
        assertEquals(newLocationDto.getName(), location.getName());
        assertEquals(newLocationDto.getRadius(), location.getRadius(), 0.001);
    }

    @Test
    public void shouldToNewLocationDto() {
        Location location = new Location(1, 12.34f, 56.78f, "Location 1", 10.0f, LocationStatus.PENDING);
        LocationResponseDto locationResponseDto = LocationMapper.toNewLocationDto(location);

        assertEquals(location.getId(), locationResponseDto.getId());
        assertEquals(location.getLat(), locationResponseDto.getLat(), 0.001);
        assertEquals(location.getLon(), locationResponseDto.getLon(), 0.001);
        assertEquals(location.getName(), locationResponseDto.getName());
        assertEquals(location.getRadius(), locationResponseDto.getRadius(), 0.001);
        assertEquals(location.getStatus(), locationResponseDto.getStatus());
    }

    @Test
    public void shouldToLocationInvalidLatLon() {
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(100.0f)
                .lon(200.0f)
                .name("Invalid Location")
                .radius(10.0f)
                .build();

        Location location = LocationMapper.toLocation(newLocationDto);
        assertEquals(100.0f, location.getLat());
        assertEquals(200.0f, location.getLon());
    }


    @Test
    public void shouldToLocationNegativeRadius() {
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(12.34f)
                .lon(56.78f)
                .name("Negative Radius Location")
                .radius(-10.0f)
                .build();

        Location location = LocationMapper.toLocation(newLocationDto);
        assertEquals(-10.0f, location.getRadius());
    }


    @Test
    public void shouldToNewLocationDtoNullStatus() {
        Location location = new Location(1, 12.34f, 56.78f, "Location 1", 10.0f, null);
        LocationResponseDto locationResponseDto = LocationMapper.toNewLocationDto(location);

        assertNull(locationResponseDto.getStatus());
    }

}