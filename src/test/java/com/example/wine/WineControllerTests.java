package com.example.wine;

import com.example.wine.Wine.Wine;
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
    void all() {
        webTestClient.get()
                .uri("/wine")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/hal+json");
    }

    @Test
    void one() {
        webTestClient.get()
                .uri("/wine/29")
                .exchange()
                .expectBody()
                .jsonPath("$.name")
                .isEqualTo("Santa Rosa");
    }

    @Test
    void createWine() {

        Wine wine = new Wine();
        webTestClient.post()
                .uri("/wine")
                .bodyValue(wine)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void deleteWine() {
        webTestClient.delete()
                .uri("/wine/29")
                .exchange()
                .expectStatus().isEqualTo(204);
    }

    @Test
    void wineNotFound() {
        webTestClient.get()
                .uri("/wine/256")
                .exchange()
                .expectStatus().isNotFound();
    }
}
