package com.jm.demo.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class OperationsControllerTest {

    @MockBean
    private OperationsService operationsService;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setup() {
        Mockito.when(operationsService.factorial(ArgumentMatchers.anyInt()))
                .thenReturn(0);
    }

    @Test
    public void factorialTest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/factorial")
                        .queryParam("num", "3")
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.result")
                .isEqualTo(0);
    }

    @Test
    public void factorialArithmeticExceptionTest() {
        Mockito.when(operationsService.factorial(ArgumentMatchers.anyInt()))
                .thenThrow(ArithmeticException.class);
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/factorial")
                        .queryParam("num", "-1")
                        .build())
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("Invalid operation");
    }

    @Test
    public void factorialIllegalArgumentExceptionTest() {
        Mockito.when(operationsService.factorial(ArgumentMatchers.anyInt()))
                .thenThrow(IllegalArgumentException.class);
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/factorial")
                        .queryParam("num", "24")
                        .build())
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("Invalid operation");
    }
}
