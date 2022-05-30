package com.example.wine;


import com.example.wine.Winery.Winery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class WineryControllerTests {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void one() {
        webTestClient.get()
                .uri("/winery/26")
                .exchange()
                .expectBody()
                .jsonPath("$.name")
                .isEqualTo("Losada");
    }

    @Test
    void all() {
        webTestClient.get()
                .uri("/winery")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/hal+json");
    }

    @Test
    void createWinery() {

        Winery winery = new Winery();
        webTestClient.post()
                .uri("/winery")
                .bodyValue(winery)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void deleteWinery() {
        webTestClient.delete()
                .uri("/winery/40")
                .exchange()
                .expectStatus().isEqualTo(204);
    }

    @Test
    void wineryNotFound() {
        webTestClient.get()
                .uri("/winery/256")
                .exchange()
                .expectStatus().isNotFound();
    }
}
