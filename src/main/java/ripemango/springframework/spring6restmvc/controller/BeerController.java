package ripemango.springframework.spring6restmvc.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ripemango.springframework.spring6restmvc.model.BeerDTO;
import ripemango.springframework.spring6restmvc.service.BeerService;

import java.util.List;
import java.util.UUID;


@Slf4j
//@AllArgsConstructor
@RequiredArgsConstructor
@RestController
@RequestMapping
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    final private BeerService beerService;


    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity updateBeerPatchById(@PathVariable("beerId")UUID beerId , @RequestBody BeerDTO beer){

        beerService.patchBeerById(beerId,beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId){

      if(!beerService.deleteById(beerId)){
          throw new NotFoundException();
      }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @PutMapping(BEER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("beerId")UUID beerId , @RequestBody BeerDTO beer){

        if(beerService.updateBeerById(beerId,beer).isEmpty()){
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(BEER_PATH)
//    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity handlepost(@Validated @RequestBody BeerDTO beer){

        BeerDTO saveBeer = beerService.saveNewBeer(beer);

        HttpHeaders header = new HttpHeaders();
        header.add("Location","/api/v1/beer/" + saveBeer.getId().toString());

        return new ResponseEntity(header,HttpStatus.CREATED);
    }

    @GetMapping(BEER_PATH)
    public List<BeerDTO> listBeers(){
        return beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    public BeerDTO getBeerById(@PathVariable("beerId") UUID beerId){

        log.debug("test for controller -12345");

        return beerService.getBeerById(beerId).orElseThrow(NotFoundException :: new);

    }
}
