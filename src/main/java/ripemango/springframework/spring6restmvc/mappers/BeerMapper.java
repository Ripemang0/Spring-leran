package ripemango.springframework.spring6restmvc.mappers;

import org.mapstruct.Mapper;
import ripemango.springframework.spring6restmvc.entities.Beer;
import ripemango.springframework.spring6restmvc.model.BeerDTO;

/**
 * Author: Ripemango0
 * Date: 2024/3/12
 */

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
