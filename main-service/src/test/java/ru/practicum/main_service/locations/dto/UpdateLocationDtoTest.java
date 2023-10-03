package ru.practicum.main_service.locations.dto;

import org.junit.Test;
import ru.practicum.main_service.locations.model.LocationStatus;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateLocationDtoTest {


    @Test
    public void shouldSetAndRetrieveFields() {
        UpdateLocationDto location = UpdateLocationDto.builder()
                .lat(10.0f)
                .lon(20.0f)
                .name("Location 1")
                .radius(50.0f)
                .status(LocationStatus.PENDING)
                .build();

        assertEquals(10.0f, location.getLat(), 0.0);
        assertEquals(20.0f, location.getLon(), 0.0);
        assertEquals("Location 1", location.getName());
        assertEquals(50.0f, location.getRadius(), 0.0);
        assertEquals(LocationStatus.PENDING, location.getStatus());
    }

    @Test
    public void shouldMinimumLatitudeLongitude() {
        UpdateLocationDto location = UpdateLocationDto.builder()
                .lat(-90.0f)
                .lon(-180.0f)
                .name("Location 2")
                .radius(100.0f)
                .status(LocationStatus.APPROVED)
                .build();

        assertEquals(-90.0f, location.getLat(), 0.0);
        assertEquals(-180.0f, location.getLon(), 0.0);
        assertEquals("Location 2", location.getName());
        assertEquals(100.0f, location.getRadius(), 0.0);
        assertEquals(LocationStatus.APPROVED, location.getStatus());
    }

    @Test
    public void shouldMaximumLatitudeLongitude() {
        UpdateLocationDto location = UpdateLocationDto.builder()
                .lat(90.0f)
                .lon(180.0f)
                .name("Location 3")
                .radius(150.0f)
                .status(LocationStatus.CANCELED)
                .build();

        assertEquals(90.0f, location.getLat(), 0.0);
        assertEquals(180.0f, location.getLon(), 0.0);
        assertEquals("Location 3", location.getName());
        assertEquals(150.0f, location.getRadius(), 0.0);
        assertEquals(LocationStatus.CANCELED, location.getStatus());
    }

    @Test
    public void shouldInvalidLatitudeLessThanMin() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        assertThrows(ConstraintViolationException.class, () -> {
            UpdateLocationDto location = UpdateLocationDto.builder()
                    .lat(-91.0f)
                    .lon(0.0f)
                    .name("Location 4")
                    .radius(200.0f)
                    .status(LocationStatus.PENDING)
                    .build();

            Set<ConstraintViolation<UpdateLocationDto>> violations = validator.validate(location);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }


    @Test
    public void shouldInvalidLatitudeGreaterThanMax() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        assertThrows(ConstraintViolationException.class, () -> {
            UpdateLocationDto location = UpdateLocationDto.builder()
                    .lat(91.0f)
                    .lon(0.0f)
                    .name("Location 5")
                    .radius(250.0f)
                    .status(LocationStatus.APPROVED)
                    .build();

            Set<ConstraintViolation<UpdateLocationDto>> violations = validator.validate(location);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }


    @Test
    public void shouldInvalidLongitudeLessThanMin() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        assertThrows(ConstraintViolationException.class, () -> {
            UpdateLocationDto location = UpdateLocationDto.builder()
                    .lat(0.0f)
                    .lon(-181.0f)
                    .name("Location 6")
                    .radius(300.0f)
                    .status(LocationStatus.CANCELED)
                    .build();

            Set<ConstraintViolation<UpdateLocationDto>> violations = validator.validate(location);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }


}