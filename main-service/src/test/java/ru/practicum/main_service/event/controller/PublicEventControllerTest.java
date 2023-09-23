package ru.practicum.main_service.event.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.practicum.main_service.event.dto.EventShortDto;
import ru.practicum.main_service.event.model.SortEvents;
import ru.practicum.main_service.event.service.EventService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(PublicEventController.class)
public class PublicEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    private List<EventShortDto> eventShortDtoList;

    @BeforeEach
    public void setup() {
        eventShortDtoList = Arrays.asList(new EventShortDto(), new EventShortDto());
    }

    @Test
    public void shouldTestGetEventsWithValidParameters() throws Exception {
        String expectedResponse = "[{\"id\":null,\"annotation\":null,\"category\":null,\"confirmedRequests\":null,\"eventDate\":null,\"initiator\":null,\"paid\":null,\"title\":null,\"views\":null},{\"id\":null,\"annotation\":null,\"category\":null,\"confirmedRequests\":null,\"eventDate\":null,\"initiator\":null,\"paid\":null,\"title\":null,\"views\":null}]";

        Mockito.when(eventService.getEvents(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.anyInt(), Mockito.anyInt(), Mockito.any())).thenReturn(eventShortDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/events"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse));

        Mockito.verify(eventService, Mockito.times(1)).getEvents(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.anyInt(), Mockito.anyInt(), Mockito.any());
    }

    @Test
    public void shouldTestGetEventsWithInvalidDateRange() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/events")
                        .param("rangeStart", "2022-12-31 23:59:59")
                        .param("rangeEnd", "2022-01-01 00:00:00"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(eventService, Mockito.never()).getEvents(Mockito.anyString(), Mockito.anyList(), Mockito.anyBoolean(), Mockito.any(LocalDateTime.class), Mockito.any(LocalDateTime.class), Mockito.anyBoolean(), Mockito.any(SortEvents.class), Mockito.anyInt(), Mockito.anyInt(), Mockito.any());
    }
}