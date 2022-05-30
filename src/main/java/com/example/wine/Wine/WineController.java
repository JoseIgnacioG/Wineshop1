package com.example.wine.Wine;

import com.example.wine.NotFoundException;
import com.example.wine.ResponseHandler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class WineController {

    private final WineRepository repository;
    private final WineModelAssembler assembler;

    public WineController(WineRepository repository, WineModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @PostMapping("/wine")
    ResponseEntity<?> newWine(@Valid @RequestBody Wine newWine, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            throw new NotFoundException(newWine.getId());
        } else {
            EntityModel<Wine> entityModel = assembler.toModel(repository.save(newWine));
            return ResponseEntity //
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                    .body(entityModel);
        }


//        return ResponseHandler.generateResponse("Acidity cannot be out of 1-5 range!", HttpStatus.MULTI_STATUS, entityModel);
    }

    @GetMapping("/wine/{id}")
    EntityModel<Wine> one(@PathVariable Long id) {

        Wine wine = repository.findById(id) //
                .orElseThrow(() -> new NotFoundException(id));
        return assembler.toModel(wine);
    }

    @GetMapping("/wine")
    CollectionModel<EntityModel<Wine>> all() {

        List<EntityModel<Wine>> wines = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(wines, linkTo(methodOn(WineController.class).all()).withSelfRel());
    }

    @PutMapping("/wine/{id}")
    ResponseEntity<?> replaceWine(@RequestBody Wine newWine, @PathVariable Long id) {

        Wine updatedEmployee = repository.findById(id) //
                .map(wine -> {
                    wine.setName(newWine.getName());
                    wine.setWineYear(newWine.getWineYear());
                    wine.setRating(newWine.getRating());
                    wine.setNum_reviews(newWine.getNum_reviews());
                    wine.setPrice(newWine.getPrice());
                    wine.setBody(newWine.getBody());
                    wine.setAcidity(newWine.getAcidity());
                    wine.setWinery(newWine.getWinery());
                    wine.setRegion(newWine.getRegion());
                    wine.setType(newWine.getType());

                    return repository.save(wine);
                }) //
                .orElseGet(() -> {
                    newWine.setId(id);
                    return repository.save(newWine);
                });

        EntityModel<Wine> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/wine/{id}")
    ResponseEntity<?> deleteWine(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

