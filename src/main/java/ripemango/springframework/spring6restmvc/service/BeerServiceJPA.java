package ripemango.springframework.spring6restmvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ripemango.springframework.spring6restmvc.mappers.BeerMapper;
import ripemango.springframework.spring6restmvc.model.BeerDTO;
import ripemango.springframework.spring6restmvc.repositories.BeerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Author:
 * Date: 2024/3/12
 */

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public List<BeerDTO> listBeers() {

        return beerRepository.findAll()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {

        return Optional.ofNullable(beerMapper.beerToBeerDto(beerRepository.findById(id)
        .orElse(null)));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {

        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beer)));
    }

    @Override
    public void updateBeerById(UUID beerId, BeerDTO beer) {
        beerRepository.findById(beerId).ifPresent(foundBeer -> {
            foundBeer.setBeerName(beer.getBeerName());
            foundBeer.setBeerStyle(beer.getBeerStyle());
            foundBeer.setUpc(beer.getUpc());
            foundBeer.setPrice(beer.getPrice());
            beerRepository.save(foundBeer);
        });

    }

    @Override
    public void deleteById(UUID beerId) {

    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beer) {

    }
}