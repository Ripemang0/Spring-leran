package ripemango.springframework.spring6restmvc.service;

import ripemango.springframework.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;



public interface BeerService {

    List<Beer> listBeers();

    Beer getBeerById(UUID id);

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);
}
