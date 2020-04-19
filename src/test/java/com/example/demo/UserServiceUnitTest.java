package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserServiceUnitTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    BCryptPasswordEncoder passwordEncoder;
//    @MockBean
//    private UserService userService;
//    @MockBean
//    private UserRepository userRepository;
    @Test
    public void findUserByName(){
//        ResponseEntity<ArrayList> newss= this.restTemplate.getForEntity("http://localhost:"+port+"/news", Json);
//        assertThat(newss.getStatusCode(),equalTo(HttpStatus.UNAUTHORIZED));
    }
}
