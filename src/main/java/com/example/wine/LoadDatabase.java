package com.example.wine;

import com.example.wine.Region.Region;
import com.example.wine.Region.RegionRepository;
import com.example.wine.Type.Type;
import com.example.wine.Type.TypeRepository;
import com.example.wine.Wine.Wine;
import com.example.wine.Wine.WineRepository;
import com.example.wine.Winery.Winery;
import com.example.wine.Winery.WineryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(WineRepository wineRepository, RegionRepository regionRepository,
                                   WineryRepository wineryRepository, TypeRepository typeRepository) {
        return args -> {

//            Region alicante = new Region("Alicante", "Espana");
////            Region bierzo = new Region("Bierzo", "Espana");
////            Region cadiz = new Region("Cadiz", "Espana");
////
//            Type red = new Type("Red");
////            Type mencia = new Type("Mencia");
////            Type syrah = new Type("Syrah");
////
////            Winery losada = new Winery("Losada");
////            Winery enrique_mendoza = new Winery("Enrique Mendoza");
//
//            Winery albala = new Winery("Huerta de Albala");
////
//            Wine wine1 = new Wine("Santa Rosa", 2017, 42f, 420, 189, 11, 3, alicante,red,albala);
//
//            Wine wine2 = new Wine("Altos de Losada", 2018, 42f, 415, 179, 4, 3, bierzo, mencia, losada);
//
//            Wine wine3 = new Wine("Tintilla de Rota", 2016, 46f, 92, 47, 4, 3,cadiz, syrah, enrique_mendoza);
//
//            regionRepository.save(alicante);
////            regionRepository.save(bierzo);
////            regionRepository.save(cadiz);
////
//            typeRepository.save(red);
//            typeRepository.save(mencia);
//            typeRepository.save(syrah);
//
//            wineryRepository.save(losada);
//            wineryRepository.save(enrique_mendoza);
//            wineryRepository.save(albala);
////
//            wineRepository.save(wine1);
//            wineRepository.save(wine2);
//            wineRepository.save(wine3);
//
//            regionRepository.findAll().forEach(r -> log.info("Preloaded: " + r));
//
//            typeRepository.findAll().forEach(t -> log.info("Preloaded: " + t));
//
//            wineryRepository.findAll().forEach(winery -> log.info("Preloaded: " + winery));
////
//            wineRepository.findAll().forEach(w -> log.info("Preloaded " + w));
        };
    }

}
