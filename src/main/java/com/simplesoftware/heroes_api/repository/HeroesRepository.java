package com.simplesoftware.heroes_api.repository;

import com.simplesoftware.heroes_api.document.Heroes;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes, String> {

}
