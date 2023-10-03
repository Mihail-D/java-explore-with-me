package ru.practicum.main_service.locations.dto;

import org.junit.Test;
import ru.practicum.main_service.locations.model.Location;
import ru.practicum.main_service.locations.model.LocationStatus;

import static org.junit.jupiter.api.Assertions.*;

public class LocationDtoTest {

    @Test
    public void shouldCreateLocationDtoWithValidFloatValues() {
        LocationDto locationDto = LocationDto.builder()
                .lat(1.23f)
                .lon(4.56f)
                .build();

        assertEquals(1.23f, locationDto.getLat(), 0.01);
        assertEquals(4.56f, locationDto.getLon(), 0.01);
    }

    @Test
    public void shouldRetrieveLatAndLonValues() {
        LocationDto locationDto = LocationDto.builder()
                .lat(1.23f)
                .lon(4.56f)
                .build();

        assertEquals(1.23f, locationDto.getLat(), 0.01);
        assertEquals(4.56f, locationDto.getLon(), 0.01);
    }

    @Test
    public void shouldCreateLocationDtoWithInvalidFloatValues() {
        LocationDto locationDto = LocationDto.builder()
                .lat(Float.NaN)
                .lon(Float.POSITIVE_INFINITY)
                .build();

        assertTrue(Float.isNaN(locationDto.getLat()));
        assertTrue(Float.isInfinite(locationDto.getLon()));
    }

    @Test
    public void shouldCreateLocationDtoWithMaxAndMinFloatValues() {
        LocationDto locationDto = LocationDto.builder()
                .lat(Float.MAX_VALUE)
                .lon(Float.MIN_VALUE)
                .build();

        assertEquals(Float.MAX_VALUE, locationDto.getLat(), 0.01);
        assertEquals(Float.MIN_VALUE, locationDto.getLon(), 0.01);
    }

    @Test
    public void shouldCreateLocationDtoWithNonFloatValues() {
        assertThrows(NumberFormatException.class, () -> {
            LocationDto locationDto = LocationDto.builder()
                    .lat(Float.parseFloat("abc"))
                    .lon(Float.parseFloat("def"))
                    .build();
        });
    }

    @Test
    public void shouldCreateLocationWithLatAndLon() {
        Location location = new Location(1.0f, 2.0f);
        assertEquals(1.0f, location.getLat(), 0.0);
        assertEquals(2.0f, location.getLon(), 0.0);
    }

    @Test
    public void shouldSetAndGetAttributes() {
        Location location = new Location();
        location.setId(1);
        location.setLat(1.0f);
        location.setLon(2.0f);
        location.setName("Test Location");
        location.setRadius(10.0f);
        location.setStatus(LocationStatus.PENDING);

        assertEquals(1, location.getId());
        assertEquals(1.0f, location.getLat(), 0.0);
        assertEquals(2.0f, location.getLon(), 0.0);
        assertEquals("Test Location", location.getName());
        assertEquals(10.0f, location.getRadius(), 0.0);
        assertEquals(LocationStatus.PENDING, location.getStatus());
    }

    @Test
    public void shouldCreateLocationWithAllParameters() {
        Location location = new Location(1, 1.0f, 2.0f, "Test Location", 10.0f, LocationStatus.PENDING);

        assertEquals(1, location.getId());
        assertEquals(1.0f, location.getLat(), 0.0);
        assertEquals(2.0f, location.getLon(), 0.0);
        assertEquals("Test Location", location.getName());
        assertEquals(10.0f, location.getRadius(), 0.0);
        assertEquals(LocationStatus.PENDING, location.getStatus());
    }

    @Test
    public void shouldCreateLocationWithNullLatAndLon() {
        Location location = new Location(null, null);

        assertNull(location.getLat());
        assertNull(location.getLon());
    }

    @Test
    public void shouldCreateLocationWithNullName() {
        Location location = new Location(1.0f, 2.0f);
        location.setName(null);

        assertNull(location.getName());
    }

    @Test
    public void shouldCreateLocationWithNullRadius() {
        Location location = new Location(1.0f, 2.0f);
        location.setRadius(null);

        assertNull(location.getRadius());
    }

}