package ru.practicum.main_service.event.service;

import org.junit.Test;
import org.mockito.Mockito;
import ru.practicum.main_service.StatisticClient;
import ru.practicum.main_service.categories.repository.CategoriesRepository;
import ru.practicum.main_service.event.repository.CustomBuiltEventRepository;
import ru.practicum.main_service.event.repository.EventRepository;
import ru.practicum.main_service.locations.LocationRepository;
import ru.practicum.main_service.request.RequestRepository;
import ru.practicum.main_service.users.repository.UserRepository;

import static org.junit.Assert.assertThrows;

public class EventServiceImplTest {

    @Test
    public void shouldTestGetAllEventsByUserIdInvalidUserId() {
        EventRepository eventRepository = Mockito.mock(EventRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        StatisticClient statsClient = Mockito.mock(StatisticClient.class);
        CategoriesRepository categoriesRepository = Mockito.mock(CategoriesRepository.class);
        RequestRepository requestRepository = Mockito.mock(RequestRepository.class);
        CustomBuiltEventRepository customBuiltEventRepository = Mockito.mock(CustomBuiltEventRepository.class);
        LocationRepository locationRepository = Mockito.mock(LocationRepository.class);

        EventServiceImpl eventService = new EventServiceImpl(eventRepository, userRepository, statsClient,
                categoriesRepository, requestRepository, customBuiltEventRepository, locationRepository
        );

        Long userId = 1L;
        Integer from = 0;
        Integer size = 10;

        Mockito.when(eventRepository.findByInitiatorId(Mockito.eq(userId), Mockito.any())).thenReturn(null);

        assertThrows(Exception.class, () -> eventService.getAllEventsByUserId(userId, from, size));
    }
}