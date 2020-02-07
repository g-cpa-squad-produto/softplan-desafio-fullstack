package com.example.demo.web.rest;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserResourceTest {

    @InjectMocks
    private UserResource userResource;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User u1 = new User();
        u1.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(u1));
    }

    @Test
    void createUsuario() {

    }

    @Test
    void updateUsuario() {
        User u1 = new User();
        u1.setId(1L);

        when(restTemplate.getForEntity("http://localhost:8080/api/usuarios/1", User.class))
                .thenReturn(new ResponseEntity(u1, HttpStatus.OK));

        User u2 = userResource.getUsuario(1L).getBody();
        Assertions.assertEquals(u1, u2);
    }

    @Test
    void getAllUsuarios() {
    }

    @Test
    void getUsuario() {
    }

    @Test
    void deleteUsuario() {
    }
}
