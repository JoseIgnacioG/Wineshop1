package com.example.wine;

import com.example.wine.Region.Region;
import com.example.wine.Type.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class RegionControllerTests {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void one() {
        webTestClient.get()
                .uri("/region/20")
                .exchange()
                .expectBody()
                .jsonPath("$.name")
                .isEqualTo("Alicante");
    }

    @Test
    void all() {
        webTestClient.get()
                .uri("/region")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/hal+json");
    }

    @Test
    void createRegion() {

        Region region = new Region();
        webTestClient.post()
                .uri("/region")
                .bodyValue(region)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void deleteRegion() {
        webTestClient.delete()
                .uri("/region/60")
                .exchange()
                .expectStatus().isEqualTo(204);
    }

    @Test
    void regionNotFound() {
        webTestClient.get()
                .uri("/region/256")
                .exchange()
                .expectStatus().isNotFound();
    }
}
