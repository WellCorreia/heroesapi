package com.marveldc.herosapi.repository;

import com.marveldc.herosapi.document.Heroes;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

public interface HeroesRepository extends CrudRepository<Heroes, String> {
}
