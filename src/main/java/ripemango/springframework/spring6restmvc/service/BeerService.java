package ripemango.springframework.spring6restmvc.service;

import ripemango.springframework.spring6restmvc.model.BeerDTO;
import ripemango.springframework.spring6restmvc.model.BeerStyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



public interface BeerService {

    List<BeerDTO> listBeers(String BeerName, BeerStyle beerStyle, Boolean showInventory, Integer PageNumber, Integer PageSize);

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    Boolean deleteById(UUID beerId);

    Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer);
}
