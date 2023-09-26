package ru.practicum.main_service.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.practicum.main_service.users.dto.NewUserRequestDto;
import ru.practicum.main_service.users.dto.UserDto;
import ru.practicum.main_service.users.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminUserController.class)
public class AdminUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldGetUsersReturnListOfUserDto() {
        List<Long> ids = new ArrayList<>();
        Integer from = 0;
        Integer size = 10;

        UserService userServiceMock = Mockito.mock(UserService.class);
        List<UserDto> expectedUsers = new ArrayList<>();
        Mockito.when(userServiceMock.getUsers(ids, from, size)).thenReturn(expectedUsers);

        AdminUserController adminUserController = new AdminUserController(userServiceMock);

        List<UserDto> actualUsers = adminUserController.getUsers(ids, from, size);

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void shouldCreateUserReturnCreatedUserDto() {
        NewUserRequestDto userRequestDto = new NewUserRequestDto("John Doe", "john.doe@example.com");

        UserService userServiceMock = Mockito.mock(UserService.class);
        UserDto expectedUser = new UserDto(1L, "John Doe", "john.doe@example.com");
        Mockito.when(userServiceMock.createUser(userRequestDto)).thenReturn(expectedUser);

        AdminUserController adminUserController = new AdminUserController(userServiceMock);

        UserDto actualUser = adminUserController.createUser(userRequestDto);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void shouldDeleteUserReturnNoContent() {
        Long userId = 1L;

        UserService userServiceMock = Mockito.mock(UserService.class);

        AdminUserController adminUserController = new AdminUserController(userServiceMock);

        adminUserController.deleteUser(userId);

        Mockito.verify(userServiceMock).deleteUser(userId);
    }

    @Test
    public void shouldGetUsersWithEmptyIdsReturnAllUsers() {
        List<Long> ids = new ArrayList<>();
        Integer from = 0;
        Integer size = 10;

        UserService userServiceMock = Mockito.mock(UserService.class);
        List<UserDto> expectedUsers = new ArrayList<>();
        Mockito.when(userServiceMock.getUsers(ids, from, size)).thenReturn(expectedUsers);

        AdminUserController adminUserController = new AdminUserController(userServiceMock);

        List<UserDto> actualUsers = adminUserController.getUsers(ids, from, size);

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void shouldGetUsersWithInvalidFromAndSizeParametersReturnEmptyList() {
        List<Long> ids = new ArrayList<>();
        Integer from = -1;
        Integer size = -1;

        UserService userServiceMock = Mockito.mock(UserService.class);
        List<UserDto> expectedUsers = new ArrayList<>();
        Mockito.when(userServiceMock.getUsers(ids, from, size)).thenReturn(expectedUsers);

        AdminUserController adminUserController = new AdminUserController(userServiceMock);

        List<UserDto> actualUsers = adminUserController.getUsers(ids, from, size);

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void shouldCreateUserWithInvalidInputReturnBadRequest() throws Exception {
        NewUserRequestDto userRequestDto = new NewUserRequestDto("", "invalid_email");

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

