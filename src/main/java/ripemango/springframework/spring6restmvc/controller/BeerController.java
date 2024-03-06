package ripemango.springframework.spring6restmvc.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripemango.springframework.spring6restmvc.model.Beer;
import ripemango.springframework.spring6restmvc.service.BeerService;

import java.util.List;
import java.util.UUID;


@Slf4j
@AllArgsConstructor
@RestController
public class BeerController {

    final private BeerService beerService;

    @RequestMapping("/api/v1/beer")
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }


    public Beer getBeerById(UUID id){

        log.debug("test for controller");

        return beerService.getBeerById(id);

    }
}
