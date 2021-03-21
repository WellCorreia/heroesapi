package com.marveldc.herosapi.controller;

import com.marveldc.herosapi.constans.HeroesConstant;
import com.marveldc.herosapi.exception.HeroesNotFoundException;
import com.marveldc.herosapi.repository.HeroesRepository;
import com.marveldc.herosapi.document.Heroes;
import com.marveldc.herosapi.service.HeroesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(HeroesConstant.HEROES_ENDPOINT_LOCAL)
public class HeroesController {

    HeroesService heroesService;

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Heroes> getAllItems() {
        log.info("requesting the list off all heroes");
        return heroesService.findAll();

    }


    @GetMapping("/{id}")
    public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id) throws HeroesNotFoundException {
        log.info("Requesting the hero with id {}", id);
        return heroesService.findByIdHero(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
        log.info("A new Hero was Created");
        return heroesService.save(heroes);

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Heroes>> updateHero(@RequestBody Heroes hero, @PathVariable String id) {
        log.info("A new Hero was update");
        return heroesService.update(hero, id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public void deleteByIdHero(@PathVariable String id) throws HeroesNotFoundException {
        heroesService.deleteByIdHero(id);
        log.info("Deleting the hero with id {}", id);
    }
}
