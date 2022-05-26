package com.example.wine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadData {

 /*   private static final Logger log = LoggerFactory.getLogger(LoadData.class);
    @Bean
    CommandLineRunner initDatabase(WineRepository wineRepository, RegionRepository regionRepository,
                                   WineryRepository wineryRepository, TypeRepository typeRepository) {
        return args -> {
            wineRepository.save(new Wine(wineryRepository.save(new Winery("Alto turia")), 1999, 7,
                    regionRepository.save(new Region("Alto turia", "Valencia")), 25,
                    typeRepository.save(new Type("Verdejo")), 9, 3, 99, "Jose Pariente"));
            wineRepository.findAll().forEach(wine -> log.info("Preloaded " + wine));
        };
    }*/

}
