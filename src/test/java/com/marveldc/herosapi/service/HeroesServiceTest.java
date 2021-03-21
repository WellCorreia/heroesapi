package com.marveldc.herosapi.service;

import com.marveldc.herosapi.builder.HeroesBuilder;
import com.marveldc.herosapi.document.Heroes;
import com.marveldc.herosapi.mapper.HeroesMapper;
import com.marveldc.herosapi.repository.HeroesRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class HeroesServiceTest {
    @Mock
    private HeroesRepository heroesRepository;
    private HeroesMapper heroesMapper;

    @InjectMocks
    private HeroesService heroesService;

    @Test
    void whenHeroInformedThenItShouldBeCreated() {
        // Given
        Heroes heroes = HeroesBuilder.builder().build().toHeroes();

        // When
        Mockito.when(heroesService.save(heroes)).thenReturn(Mono.justOrEmpty(heroes));

        System.out.println(heroes);
        // Then

        Mono heroesSaved = heroesService.save(heroes);

        System.out.println(heroesSaved);

//        // Matcher
//        MatcherAssert.assertThat(heroesSaved.getId(), Matchers.is(Matchers.equalTo(heroes.getId())));
//        MatcherAssert.assertThat(heroesSaved.getUniverse(), Matchers.is(Matchers.equalTo(heroes.getUniverse())));
//        MatcherAssert.assertThat(heroesSaved.getName(), Matchers.is(Matchers.equalTo(heroes.getName())));
//        MatcherAssert.assertThat(heroesSaved.getFilms(), Matchers.is(Matchers.equalTo(heroes.getFilms())));
    }
}
