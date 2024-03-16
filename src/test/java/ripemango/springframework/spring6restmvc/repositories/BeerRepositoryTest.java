package ripemango.springframework.spring6restmvc.repositories;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ripemango.springframework.spring6restmvc.entities.Beer;
import ripemango.springframework.spring6restmvc.model.BeerStyle;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerNameTooLong() {

        assertThrows(ConstraintViolationException.class,() -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("My Beer 0123456731313212315132156132561326515321651326542315613256413265153216513265423156132564")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("213456877")
                    .price(new BigDecimal("12.2"))
                    .build());
            beerRepository.flush();
        });
//        assertThat(savedBeer).isNotNull();
//        assertThat(savedBeer.getId()).isNotNull();
    }

    @Test
    void testSaveBeer(){
        Beer savedBeer = beerRepository.save(Beer.builder()
        .beerName("My Beer")
        .beerStyle(BeerStyle.PALE_ALE)
        .upc("21345613165451")
        .price(new BigDecimal("11.69"))
        .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }


}
