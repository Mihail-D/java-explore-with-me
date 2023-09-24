package ru.practicum.main_service.event.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.practicum.main_service.event.dto.EventFullDto;
import ru.practicum.main_service.event.service.EventService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PrivateEventControllerTest {

    @Mock(lenient = true)
    private EventService eventService;

    @InjectMocks
    private PrivateEventController privateEventController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(privateEventController).build();
    }

    @Test
    public void shouldTestGetAllEventsByUserIdWhenCalledThenReturnsResultFromService() throws Exception {
        List<EventFullDto> events = Collections.singletonList(new EventFullDto());
        when(eventService.getAllEventsByUserId(anyLong(), anyInt(), anyInt())).thenReturn(events);

        mockMvc.perform(get("/users/1/events"))
                .andExpect(status().isOk());
    }

}

