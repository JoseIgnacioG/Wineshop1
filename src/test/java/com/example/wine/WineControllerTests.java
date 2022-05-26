package com.example.wine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class WineControllerTests {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void one() {
        webTestClient.get()
                .uri("/wine/1")
                .exchange()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Santa Rosa");
    }

    @Test
    void all() {
        webTestClient.get()
                .uri("/wine")
                .exchange()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(9);
    }
}
