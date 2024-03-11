package ripemango.springframework.spring6restmvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ripemango.springframework.spring6restmvc.model.Beer;
import ripemango.springframework.spring6restmvc.model.Customer;
import ripemango.springframework.spring6restmvc.service.CustomerService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CustomerController {
    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity pathCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer){

        customerService.patchCustomerById(customerId,customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteCustomerById(@PathVariable("customerId") UUID customerId){

        customerService.deleteCustomerById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("customerId")UUID customerId , @RequestBody Customer customer){

        customerService.updateCustomerById(customerId,customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity handlepost(@RequestBody Customer customer){

        Customer saveCustomer= customerService.saveNewCustomer(customer);

        HttpHeaders header = new HttpHeaders();
        header.add("Location",CUSTOMER_PATH + saveCustomer.getId().toString());

        return new ResponseEntity(header,HttpStatus.CREATED);
    }

    @GetMapping(CUSTOMER_PATH)
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId){

        return customerService.getCustomerById(customerId).orElseThrow(NotFoundException :: new);

    }
}
