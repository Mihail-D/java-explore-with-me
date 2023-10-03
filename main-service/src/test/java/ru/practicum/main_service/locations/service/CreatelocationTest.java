package ru.practicum.main_service.locations.service;

import org.junit.Test;
import ru.practicum.main_service.exception.ConflictException;
import ru.practicum.main_service.locations.LocationRepository;
import ru.practicum.main_service.locations.dto.LocationResponseDto;
import ru.practicum.main_service.locations.dto.NewLocationDto;
import ru.practicum.main_service.locations.model.LocationStatus;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreatelocationTest {

    @Test
    public void shouldCreateLocationValidInputDataIsAdminTrue() {
        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationServiceImpl locationService = new LocationServiceImpl(locationRepository);
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(1.0f)
                .lon(2.0f)
                .name("Location 1")
                .radius(10.0f)
                .build();

        LocationResponseDto result = locationService.createLocation(newLocationDto, true);

        assertNotNull(result);
        assertEquals(newLocationDto.getLat(), result.getLat());
        assertEquals(newLocationDto.getLon(), result.getLon());
        assertEquals(newLocationDto.getName(), result.getName());
        assertEquals(newLocationDto.getRadius(), result.getRadius());
        assertEquals(LocationStatus.APPROVED, result.getStatus());
    }

    @Test
    public void shouldCreateLocationValidInputDataIsAdminFalse() {
        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationServiceImpl locationService = new LocationServiceImpl(locationRepository);
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(1.0f)
                .lon(2.0f)
                .name("Location 1")
                .radius(10.0f)
                .build();

        LocationResponseDto result = locationService.createLocation(newLocationDto, false);

        assertNotNull(result);
        assertEquals(newLocationDto.getLat(), result.getLat());
        assertEquals(newLocationDto.getLon(), result.getLon());
        assertEquals(newLocationDto.getName(), result.getName());
        assertEquals(newLocationDto.getRadius(), result.getRadius());
        assertEquals(LocationStatus.PENDING, result.getStatus());
    }

    @Test
    public void shouldCreateLocationLocationWithSameNameExists() {
        LocationRepository locationRepository = mock(LocationRepository.class);
        when(locationRepository.existsLocationByName(anyString())).thenReturn(true);
        LocationServiceImpl locationService = new LocationServiceImpl(locationRepository);
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(1.0f)
                .lon(2.0f)
                .name("Location 1")
                .radius(10.0f)
                .build();

        assertThrows(ConflictException.class, () -> locationService.createLocation(newLocationDto, true));
    }

    @Test
    public void shouldCreateLocationLocationWithSameLatAndLonExists() {
        LocationRepository locationRepository = mock(LocationRepository.class);
        when(locationRepository.existsLocationByLatAndLon(anyFloat(), anyFloat())).thenReturn(true);
        LocationServiceImpl locationService = new LocationServiceImpl(locationRepository);
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(1.0f)
                .lon(2.0f)
                .name("Location 1")
                .radius(10.0f)
                .build();

        assertThrows(ConflictException.class, () -> locationService.createLocation(newLocationDto, true));
    }

    @Test
    public void shouldCreateLocationNewLocationDtoIsNull() {
        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationServiceImpl locationService = new LocationServiceImpl(locationRepository);

        assertThrows(NullPointerException.class, () -> locationService.createLocation(null, true));
    }

    @Test
    public void shouldCreateLocationNewLocationDtoNameIsNull() {
        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationServiceImpl locationService = new LocationServiceImpl(locationRepository);
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(1.0f)
                .lon(2.0f)
                .radius(10.0f)
                .build();

        LocationResponseDto result = locationService.createLocation(newLocationDto, true);

        assertNotNull(result);
        assertEquals(newLocationDto.getName(), result.getName());
    }


}