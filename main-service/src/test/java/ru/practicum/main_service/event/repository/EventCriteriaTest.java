package ru.practicum.main_service.event.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.practicum.main_service.event.dto.Criteria;
import ru.practicum.main_service.event.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class EventCriteriaTest {

    @Test
    public void shouldTestRetrieveEventsWithAllPossibleCriteria() {
        EntityManager entityManager = Mockito.mock(EntityManager.class);

        CriteriaBuilder builder = Mockito.mock(CriteriaBuilder.class);

        CriteriaQuery<Event> criteriaQuery = Mockito.mock(CriteriaQuery.class);

        Root<Event> root = Mockito.mock(Root.class);

        TypedQuery<Event> typedQuery = Mockito.mock(TypedQuery.class);

        Criteria criteria = Mockito.mock(Criteria.class);

        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(builder);

        Mockito.when(builder.createQuery(Event.class)).thenReturn(criteriaQuery);
    }
}