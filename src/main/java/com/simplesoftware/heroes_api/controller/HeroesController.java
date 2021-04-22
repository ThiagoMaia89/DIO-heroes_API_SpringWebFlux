package com.simplesoftware.heroes_api.controller;

import com.simplesoftware.heroes_api.document.Heroes;
import com.simplesoftware.heroes_api.repository.HeroesRepository;
import com.simplesoftware.heroes_api.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.simplesoftware.heroes_api.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j
public class HeroesController {
    HeroesService heroesService;
    HeroesRepository heroesRepository;

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    public HeroesController (HeroesService heroesService, HeroesRepository heroesRepository){
        this.heroesRepository = heroesRepository;
        this.heroesService = heroesService;
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Heroes> getAllItems (){
        LOG.info("requesting the list off all heroes");
        return heroesService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL+"/{id}")
    public Mono <ResponseEntity<Heroes>> findByIdHero(@PathVariable String id){
        LOG.info("requesting the hero with id {}", id);
        return heroesService.findByIdHero(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code=HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes){
        LOG.info("a new hero was created");
        return heroesService.save(heroes);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL+"/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono<HttpStatus> deleteByIdHero(@PathVariable String id){
        heroesService.deleteByIdHero(id);
        LOG.info("deleting a hero with id {}", id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }

}
