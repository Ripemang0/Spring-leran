package ripemango.springframework.spring6restmvc.mappers;

import org.mapstruct.Mapper;
import ripemango.springframework.spring6restmvc.entities.Customer;
import ripemango.springframework.spring6restmvc.model.CustomerDTO;

/**
 * Author: Ripemango
 * Date: 2024/3/12
 */

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);

}
