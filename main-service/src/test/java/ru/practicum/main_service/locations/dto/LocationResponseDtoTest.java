package ru.practicum.main_service.locations.dto;

import org.junit.Test;
import ru.practicum.main_service.locations.model.LocationStatus;

import static org.junit.jupiter.api.Assertions.*;

public class LocationResponseDtoTest {


    @Test
    public void shouldAllFieldsSet() {
        LocationResponseDto location = LocationResponseDto.builder()
                .id(1)
                .lat(37.7749f)
                .lon(-122.4194f)
                .name("San Francisco")
                .radius(10.0f)
                .status(LocationStatus.PENDING)
                .build();

        assertEquals(1, location.getId());
        assertEquals(37.7749f, location.getLat(), 0.001);
        assertEquals(-122.4194f, location.getLon(), 0.001);
        assertEquals("San Francisco", location.getName());
        assertEquals(10.0f, location.getRadius(), 0.001);
        assertEquals(LocationStatus.PENDING, location.getStatus());
    }

    @Test
    public void shouldMandatoryFieldsSet() {
        LocationResponseDto location = LocationResponseDto.builder()
                .id(1)
                .name("San Francisco")
                .status(LocationStatus.PENDING)
                .build();

        assertEquals(1, location.getId());
        assertNull(location.getLat());
        assertNull(location.getLon());
        assertEquals("San Francisco", location.getName());
        assertNull(location.getRadius());
        assertEquals(LocationStatus.PENDING, location.getStatus());
    }

    @Test
    public void shouldNullFields() {
        LocationResponseDto location = LocationResponseDto.builder().build();

        assertNull(location.getLat());
        assertNull(location.getLon());
        assertNull(location.getName());
        assertNull(location.getRadius());
        assertNull(location.getStatus());
    }


    @Test
    public void shouldEmptyStringFields() {
        LocationResponseDto location = LocationResponseDto.builder()
                .id(1)
                .lat(0.0f)
                .lon(0.0f)
                .name("")
                .radius(0.0f)
                .status(LocationStatus.PENDING)
                .build();

        assertEquals(1, location.getId());
        assertEquals(0.0f, location.getLat(), 0.001);
        assertEquals(0.0f, location.getLon(), 0.001);
        assertEquals("", location.getName());
        assertEquals(0.0f, location.getRadius(), 0.001);
        assertEquals(LocationStatus.PENDING, location.getStatus());
    }

    @Test
    public void shouldEqualObjects() {
        LocationResponseDto location1 = LocationResponseDto.builder()
                .id(1)
                .lat(37.7749f)
                .lon(-122.4194f)
                .name("San Francisco")
                .radius(10.0f)
                .status(LocationStatus.PENDING)
                .build();

        LocationResponseDto location2 = LocationResponseDto.builder()
                .id(1)
                .lat(37.7749f)
                .lon(-122.4194f)
                .name("San Francisco")
                .radius(10.0f)
                .status(LocationStatus.PENDING)
                .build();

        assertEquals(location1, location2);
    }

    @Test
    public void shouldNotEqualObjects() {
        LocationResponseDto location1 = LocationResponseDto.builder()
                .id(1)
                .lat(37.7749f)
                .lon(-122.4194f)
                .name("San Francisco")
                .radius(10.0f)
                .status(LocationStatus.PENDING)
                .build();

        LocationResponseDto location2 = LocationResponseDto.builder()
                .id(2)
                .lat(37.7749f)
                .lon(-122.4194f)
                .name("San Francisco")
                .radius(10.0f)
                .status(LocationStatus.PENDING)
                .build();

        assertNotEquals(location1, location2);
    }

}