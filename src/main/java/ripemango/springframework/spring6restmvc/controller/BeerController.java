package ripemango.springframework.spring6restmvc.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import ripemango.springframework.spring6restmvc.model.Beer;
import ripemango.springframework.spring6restmvc.service.BeerService;

import java.util.UUID;


@Slf4j
@AllArgsConstructor
@Controller
public class BeerController {

    final private BeerService beerService;

    public Beer getBeerById(UUID id){

        log.debug("test for controller");

        return beerService.getBeerById(id);

    }
}
