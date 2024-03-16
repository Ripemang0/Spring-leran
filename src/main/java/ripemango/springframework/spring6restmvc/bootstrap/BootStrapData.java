package ripemango.springframework.spring6restmvc.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ripemango.springframework.spring6restmvc.entities.Beer;
import ripemango.springframework.spring6restmvc.model.BeerStyle;
import ripemango.springframework.spring6restmvc.repositories.BeerRepository;
import ripemango.springframework.spring6restmvc.repositories.CustomerRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Author: Ripemango0
 * Date: 2024/3/13
 */

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

        private final BeerRepository beerRepository;
        private final CustomerRepository customerRepository;


    @Override
    public void run(String... args) throws Exception {

        Beer beer1 = beerRepository.save(Beer.builder()
                .beerName("Qingdao")
                .id(UUID.randomUUID())
                .beerStyle(BeerStyle.GOSE)
                .createDate(LocalDateTime.now())
                .price(new BigDecimal("3"))
                .quantityOnHand(3)
                .upc("10000")
                .updateDate(LocalDateTime.now())
                .version(1)
                .build()
        );
        beer1 = beerRepository.save(Beer.builder()
                .beerName("BaoJiBeer")
                .id(UUID.randomUUID())
                .beerStyle(BeerStyle.IPA)
                .createDate(LocalDateTime.now())
                .price(new BigDecimal("3.5"))
                .quantityOnHand(2)
                .upc("10001")
                .updateDate(LocalDateTime.now())
                .version(1)
                .build()
        );
        beer1 = beerRepository.save(Beer.builder()
                .beerName("Baiwei")
                .id(UUID.randomUUID())
                .beerStyle(BeerStyle.IPA)
                .createDate(LocalDateTime.now())
                .price(new BigDecimal("6.5"))
                .quantityOnHand(3)
                .upc("10003")
                .updateDate(LocalDateTime.now())
                .version(1)
                .build()
        );


    }
}
