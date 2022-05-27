package com.example.wine;

import com.example.wine.Type.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class TypeControllerTests {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void one() {
        webTestClient.get()
                .uri("/type/23")
                .exchange()
                .expectBody()
                .jsonPath("$.name")
                .isEqualTo("Red");
    }

    @Test
    void all() {
        webTestClient.get()
                .uri("/type")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/hal+json");
    }

    @Test
    void createType() {

        Type type = new Type();
        webTestClient.post()
                .uri("/type")
                .bodyValue(type)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void deleteType() {
        webTestClient.delete()
                .uri("/type/48")
                .exchange()
                .expectStatus().isEqualTo(204);
    }

    @Test
    void typeNotFound() {
        webTestClient.get()
                .uri("/type/256")
                .exchange()
                .expectStatus().isNotFound();
    }
}
