package ru.practicum.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class HttpRequestClientTest {

    @Mock
    private RestTemplate restTemplate;

    private RestApiClient httpRequestClient;

    private static final Logger LOGGER = Logger.getLogger(HttpRequestClientTest.class.getName());

    @BeforeEach
    void setUp() {
        try (AutoCloseable ac = MockitoAnnotations.openMocks(this)) {
            httpRequestClient = new RestApiClient(restTemplate);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception occur", e);
        }
    }

    @Test
    void shouldTestGet() {
        String path = "/test";
        Long userId = 1L;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("param1", "value1");

        ResponseEntity<Object> expectedResponse = ResponseEntity.ok().build();

        when(restTemplate.exchange(eq(path), eq(HttpMethod.GET), any(), eq(Object.class), eq(parameters)))
                .thenReturn(expectedResponse);

        ResponseEntity<Object> actualResponse = httpRequestClient.get(path, userId, parameters);

        assertEquals(expectedResponse, actualResponse);
    }
}