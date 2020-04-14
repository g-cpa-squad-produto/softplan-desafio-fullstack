package com.softplan.processmanagerapi.controller;

import com.softplan.processmanagerapi.controllers.UserController;
import com.softplan.processmanagerapi.dados.UserResponseDataTest;
import com.softplan.processmanagerapi.models.User;
import com.softplan.processmanagerapi.payload.ApiResponse;
import com.softplan.processmanagerapi.payload.SignUpRequest;
import com.softplan.processmanagerapi.payload.response.UserResponse;
import com.softplan.processmanagerapi.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.AssertTrue;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void getAllUsers() {
        Mockito.when(userService.getAllUser()).thenReturn(Arrays.asList(new UserResponse(1l, "João Gabriel Honorato de Assis"), new UserResponse(2l, "Usuario de teste")));
        ResponseEntity<List<UserResponse>> allUser = userController.getAllUser();
        Mockito.verify(userService).getAllUser();
        Assert.assertTrue(allUser.getBody().size() == 2);

    }
}
