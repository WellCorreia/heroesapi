package com.marveldc.herosapi.service;

import com.marveldc.herosapi.document.Heroes;
import com.marveldc.herosapi.repository.HeroesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Service
public class HeroesService {
    private final HeroesRepository heroesRepository;

    public HeroesService(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }

    public Flux<Heroes> findAll(){

        return Flux.fromIterable(this.heroesRepository.findAll());
    }

    public Mono<Heroes> findByIdHero(String id){

        return Mono.justOrEmpty(this.heroesRepository.findById(id));
    }

    public Mono<Heroes> save(Heroes heroes){
        return  Mono.justOrEmpty(this.heroesRepository.save(heroes));
    }

    public Mono<Heroes> update(Heroes hero, String id){
        Optional<Heroes> findHero = this.heroesRepository.findById(id);
        if (findHero.isPresent()) {
            return Mono.justOrEmpty(this.heroesRepository.save(hero));
        }
        return Mono.justOrEmpty(findHero);
    }


    public Mono<Boolean> deletebyIDHero(String id) {
        heroesRepository.deleteById(id);
        return Mono.just(true);
    }
}
