package com.marveldc.herosapi.mapper;

import com.marveldc.herosapi.document.Heroes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import reactor.core.publisher.Mono;

@Mapper
public interface HeroesMapper {
    HeroesMapper INSTANCE = Mappers.getMapper(HeroesMapper.class);

    Heroes toModel(Mono<Heroes> heroesMno);

}
