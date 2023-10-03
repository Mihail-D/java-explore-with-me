package ru.practicum.main_service.locations.dto;

import org.junit.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class NewLocationDtoTest {


    @Test
    public void shouldValidInputValuesForAllFields() {
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(50f)
                .lon(100f)
                .name("Location")
                .radius(10f)
                .build();

        assertNotNull(newLocationDto);
        assertEquals(50f, newLocationDto.getLat());
        assertEquals(100f, newLocationDto.getLon());
        assertEquals("Location", newLocationDto.getName());
        assertEquals(10f, newLocationDto.getRadius());
    }

    @Test
    public void shouldMinMaxValuesForLatLonRadius() {
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(-90f)
                .lon(180f)
                .name("Location")
                .radius(Float.MAX_VALUE)
                .build();

        assertNotNull(newLocationDto);
        assertEquals(-90f, newLocationDto.getLat());
        assertEquals(180f, newLocationDto.getLon());
        assertEquals("Location", newLocationDto.getName());
        assertEquals(Float.MAX_VALUE, newLocationDto.getRadius());
    }

    @Test
    public void shouldNameLength120() {
        String name = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam auctor, nunc id aliquet ultrices, nisl nunc tincidunt nunc, nec lacinia nunc mi id justo. Sed auctor, mauris in lacinia tincidunt, nisl mi tincidunt nisi, nec aliquam metus metus ac nunc. Sed vitae semper mauris. Nulla facilisi. Sed euismod, mauris id lacinia tincidunt, nisl mi tincidunt nisi, nec aliquam metus metus ac nunc. Sed vitae semper mauris. Nulla facilisi.";
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(50f)
                .lon(100f)
                .name(name)
                .radius(10f)
                .build();

        assertNotNull(newLocationDto);
        assertEquals(name, newLocationDto.getName());
    }

    @Test
    public void shouldNullValuesForAllFields() {
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(null)
                .lon(null)
                .name(null)
                .radius(null)
                .build();

        assertNotNull(newLocationDto);
        assertNull(newLocationDto.getLat());
        assertNull(newLocationDto.getLon());
        assertNull(newLocationDto.getName());
        assertNull(newLocationDto.getRadius());
    }

    @Test
    public void shouldLatLonOutsideRange() {
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(-100f)
                .lon(200f)
                .name("Location")
                .radius(10f)
                .build();

        assertNotNull(newLocationDto);
        assertEquals(-100f, newLocationDto.getLat());
        assertEquals(200f, newLocationDto.getLon());
        assertEquals("Location", newLocationDto.getName());
        assertEquals(10f, newLocationDto.getRadius());
    }

    @Test
    public void shouldNameLength121() {
        String name = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam auctor, nunc id aliquet ultrices, nisl nunc tincidunt nunc, nec lacinia nunc mi id justo. Sed auctor, mauris in lacinia tincidunt, nisl mi tincidunt nisi, nec aliquam metus metus ac nunc. Sed vitae semper mauris. Nulla facilisi. Sed euismod, mauris id lacinia tincidunt, nisl mi tincidunt nisi, nec aliquam metus metus ac nunc. Sed vitae semper mauris. Nulla facilisi. ";
        NewLocationDto newLocationDto = NewLocationDto.builder()
                .lat(50f)
                .lon(100f)
                .name(name)
                .radius(10f)
                .build();

        assertNotNull(newLocationDto);
        assertEquals(name, newLocationDto.getName());
    }

}