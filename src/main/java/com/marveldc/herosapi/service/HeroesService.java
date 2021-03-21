package com.marveldc.herosapi.service;

import com.marveldc.herosapi.document.Heroes;
import com.marveldc.herosapi.exception.HeroesNotFoundException;
import com.marveldc.herosapi.repository.HeroesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HeroesService {
    private final HeroesRepository heroesRepository;

    public Flux<Heroes> findAll(){

        return Flux.fromIterable(heroesRepository.findAll());
    }

    public Mono<Heroes> findByIdHero(String id) throws HeroesNotFoundException {
        Heroes heroes = heroesRepository.findById(id)
                .orElseThrow(() -> new HeroesNotFoundException(id));
        return Mono.just(heroes);
    }

    public Mono<Heroes> save(Heroes heroes) {
        return Mono.justOrEmpty(heroesRepository.save(heroes));
    }

    public Mono<Heroes> update(Heroes hero, String id){
        Optional<Heroes> findHero = heroesRepository.findById(id);
        if (findHero.isPresent()) {
            return Mono.justOrEmpty(heroesRepository.save(hero));
        }
        return Mono.justOrEmpty(findHero);
    }

    public Mono<Boolean> deleteByIdHero(String id) throws HeroesNotFoundException {
        findByIdHero(id);
        heroesRepository.deleteById(id);
        return Mono.just(true);
    }
}
