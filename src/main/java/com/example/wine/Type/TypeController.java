package com.example.wine.Type;

import com.example.wine.NotFoundException;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class TypeController {

    private final TypeRepository repository;
    private final TypeModelAssembler assembler;

    TypeController(TypeRepository repository, TypeModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @PostMapping("/type")
    ResponseEntity<?> newType(@RequestBody Type newType) {

        EntityModel<Type> entityModel = assembler.toModel(repository.save(newType));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/type")
    CollectionModel<EntityModel<Type>> all() {

        List<EntityModel<Type>> type = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(type, linkTo(methodOn(TypeController.class).all()).withSelfRel());
    }

    @GetMapping("/type/{id}")
    EntityModel<Type> one(@PathVariable Long id) {

        Type type = repository.findById(id) //
                .orElseThrow(() -> new NotFoundException(id));

        return assembler.toModel(type);
    }

    @PutMapping("/type/{id}")
    ResponseEntity<?> replaceType(@RequestBody Type newType, @PathVariable Long id) {

        Type updatedType = repository.findById(id) //
                .map(type -> {
                    type.setName(newType.getName());

                    return repository.save(type);
                }) //
                .orElseGet(() -> {
                    newType.setId(id);
                    return repository.save(newType);
                });

        EntityModel<Type> entityModel = assembler.toModel(updatedType);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/type/{id}")
    ResponseEntity<?> deleteType(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
