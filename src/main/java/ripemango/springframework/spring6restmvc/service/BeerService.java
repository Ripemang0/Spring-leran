package ripemango.springframework.spring6restmvc.service;

import ripemango.springframework.spring6restmvc.model.Beer;

import java.util.UUID;



public interface BeerService {

    Beer getBeerById(UUID id);

}
