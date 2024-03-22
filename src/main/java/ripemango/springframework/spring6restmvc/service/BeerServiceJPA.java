package ripemango.springframework.spring6restmvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ripemango.springframework.spring6restmvc.entities.Beer;
import ripemango.springframework.spring6restmvc.mappers.BeerMapper;
import ripemango.springframework.spring6restmvc.model.BeerDTO;
import ripemango.springframework.spring6restmvc.model.BeerStyle;
import ripemango.springframework.spring6restmvc.repositories.BeerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
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

    private final static int DEFAULT_PAGE = 0;
    private final static int DEFAULT_PAGE_SIZE = 25;

    @Override
    public List<BeerDTO> listBeers(String BeerName, BeerStyle beerStyle, Boolean showInventory
            , Integer PageNumber, Integer PageSize) {

        PageRequest pageRequest = bulidPageRequest(PageNumber,PageSize);

        List<Beer> beerList;

        if(StringUtils.hasText(BeerName)&& beerStyle == null){
            // do impl
            beerList = listBeersByName(BeerName);
        } else if (!StringUtils.hasText(BeerName) && beerStyle != null) {
            beerList = listBeersByBeerStyle(beerStyle);
        }
        else if (StringUtils.hasText(BeerName) && beerStyle != null){
            beerList = listBeersByNameAndStyle(BeerName,beerStyle);
        }
        else{
            beerList = beerRepository.findAll();
        }

        if(showInventory != null && !showInventory){
            beerList.forEach(beer -> beer.setQuantityOnHand(null));
        }

        return beerList.stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList());
    }

    public PageRequest bulidPageRequest(Integer pageNumber, Integer pageSize){
        int queryPageNumber;
        int queryPageSize;

        if(pageNumber != null && pageNumber > 0){
            queryPageNumber = pageNumber - 1;
        }else{
            queryPageNumber = DEFAULT_PAGE;
        }

        if(pageSize == null){
            queryPageSize = DEFAULT_PAGE_SIZE;
        }else{
            if(pageSize > 1000){
                queryPageSize = 1000;
            }else{
                queryPageSize = pageSize;
            }
        }

        return  PageRequest.of(queryPageNumber,queryPageSize);
    }

    public List<Beer> listBeersByNameAndStyle(String beerName, BeerStyle beerStyle) {
        return beerRepository.findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle("%" + beerName + "%",beerStyle);
    }

    public List<Beer> listBeersByName(String beerName){
        return beerRepository.findAllByBeerNameIsLikeIgnoreCase("%" + beerName + "%");
    }
    public List<Beer> listBeersByBeerStyle(BeerStyle beerStyle){
        return beerRepository.findAllByBeerStyle(beerStyle);
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
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer) {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(foundBeer -> {
            foundBeer.setBeerName(beer.getBeerName());
            foundBeer.setBeerStyle(beer.getBeerStyle());
            foundBeer.setUpc(beer.getUpc());
            foundBeer.setPrice(beer.getPrice());
            atomicReference.set(Optional.of(beerMapper
            .beerToBeerDto(beerRepository.save(foundBeer))));
        },() ->{
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();

    }

    @Override
    public Boolean deleteById(UUID beerId) {
        if(beerRepository.existsById(beerId)){
            beerRepository.deleteById(beerId);
            return true;
        }
        return false;


    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer) {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

       beerRepository.findById(beerId).ifPresentOrElse(foundBeer ->{
           if(StringUtils.hasText(beer.getBeerName())){
               foundBeer.setBeerName(beer.getBeerName());
       }
           if(beer.getBeerStyle() != null){
               foundBeer.setBeerStyle(beer.getBeerStyle());
           }
           if(StringUtils.hasText(beer.getUpc())){
               foundBeer.setUpc(beer.getUpc());
           }
           if(beer.getPrice() != null){
               foundBeer.setPrice(beer.getPrice());
           }
           if(beer.getQuantityOnHand() != null){
               foundBeer.setQuantityOnHand(beer.getQuantityOnHand());
           }
           atomicReference.set(Optional.of(beerMapper
                   .beerToBeerDto(beerRepository.save(foundBeer))));
       },() ->{
           atomicReference.set(Optional.empty());
       });

        return atomicReference.get();
}
}
