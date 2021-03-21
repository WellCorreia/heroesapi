package com.marveldc.herosapi.controller;

import com.marveldc.herosapi.builder.HeroesBuilder;
import com.marveldc.herosapi.document.Heroes;
import com.marveldc.herosapi.service.HeroesService;
import com.marveldc.herosapi.utils.JsonConvertionUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import reactor.core.publisher.Mono;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class HeroesControllerTest {
    private static final String HEROES_API_URL_PATH = "/api/v1/heroes";

    private MockMvc mockMvc;

    @Mock
    private HeroesService heroesService;

    @InjectMocks
    private HeroesController heroesController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(heroesController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locate) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPostIsCalledThenTheStudentIsCreated() throws Exception {
        // Given
        Heroes heroes = HeroesBuilder.builder().build().toHeroes();

        // When
        Mockito.when(heroesService.save(heroes)).thenReturn(Mono.justOrEmpty(heroes));

        // Then
        mockMvc.perform(post(HEROES_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConvertionUtils.asJsonString(heroes)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.is(heroes.getName())))
                .andExpect(jsonPath("$.universe", Matchers.is(heroes.getUniverse())))
                .andExpect(jsonPath("$.films", Matchers.is(heroes.getFilms())));

    }
}