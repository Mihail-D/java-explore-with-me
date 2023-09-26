package ru.practicum.main_service.request.dto;

import org.junit.Test;
import ru.practicum.main_service.request.model.ParticipationRequestStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

public class ParticipationRequestDtoTest {

    @Test
    public void shouldTestCreateParticipationRequestDtoWithAllFieldsSet() {
        ParticipationRequestDto participationRequestDto = ParticipationRequestDto.builder()
                .id(1L)
                .created(LocalDateTime.now())
                .status(ParticipationRequestStatus.CONFIRMED)
                .requester(2L)
                .event(3L)
                .build();

        assertNotNull(participationRequestDto);
        assertEquals(Long.valueOf(1L), participationRequestDto.getId());
        assertNotNull(participationRequestDto.getCreated());
        assertEquals(ParticipationRequestStatus.CONFIRMED, participationRequestDto.getStatus());
        assertEquals(Long.valueOf(2L), participationRequestDto.getRequester());
        assertEquals(Long.valueOf(3L), participationRequestDto.getEvent());
    }


    @Test
    public void shouldTestCreateParticipationRequestDtoWithOnlyRequiredFieldsSet() {
        ParticipationRequestDto participationRequestDto = ParticipationRequestDto.builder()
                .id(1L)
                .status(ParticipationRequestStatus.CONFIRMED)
                .build();

        assertNotNull(participationRequestDto);
        assertEquals(Long.valueOf(1L), participationRequestDto.getId());
        assertNull(participationRequestDto.getCreated());
        assertEquals(ParticipationRequestStatus.CONFIRMED, participationRequestDto.getStatus());
        assertNull(participationRequestDto.getRequester());
        assertNull(participationRequestDto.getEvent());
    }


    @Test
    public void shouldTestGetId() {
        ParticipationRequestDto participationRequestDto = ParticipationRequestDto.builder()
                .id(1L)
                .build();

        assertEquals(Long.valueOf(1L), participationRequestDto.getId());
    }


    @Test
    public void shouldTestCreateParticipationRequestDtoWithNullFields() {
        ParticipationRequestDto participationRequestDto = new ParticipationRequestDto();

        assertNull(participationRequestDto.getId());
        assertNull(participationRequestDto.getCreated());
        assertNull(participationRequestDto.getStatus());
        assertNull(participationRequestDto.getRequester());
        assertNull(participationRequestDto.getEvent());
    }

    @Test
    public void shouldTestCreateParticipationRequestDtoWithInvalidDateFormat() {
        assertThrows(DateTimeParseException.class, () -> {
            ParticipationRequestDto.builder()
                    .created(LocalDateTime.parse("2021-13-01 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build();
        });
    }

    @Test
    public void shouldTestCreateParticipationRequestDtoWithInvalidStatusValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            ParticipationRequestDto.builder()
                    .status(ParticipationRequestStatus.valueOf("INVALID_STATUS"))
                    .build();
        });
    }
}