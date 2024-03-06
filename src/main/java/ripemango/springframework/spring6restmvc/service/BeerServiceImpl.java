package ripemango.springframework.spring6restmvc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ripemango.springframework.spring6restmvc.model.Beer;
import ripemango.springframework.spring6restmvc.model.BeerStyle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {

        log.debug("Test for this");

        return Beer.builder()
                .id(id)
                .version(12)
                .beerName("Galaxy Cat")
                .upc("123456")
                .price(new BigDecimal("12.87"))
                .quantityOnHand(123)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
}
