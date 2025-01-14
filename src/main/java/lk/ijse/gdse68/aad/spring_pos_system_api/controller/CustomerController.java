package lk.ijse.gdse68.aad.spring_pos_system_api.controller;

import lk.ijse.gdse68.aad.spring_pos_system_api.custom.CustomerResponse;
import lk.ijse.gdse68.aad.spring_pos_system_api.dto.CustomerDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.CustomerNotFound;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.DataPersistFailException;
import lk.ijse.gdse68.aad.spring_pos_system_api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private final CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDto customerDto ) {

        if (customerDto.getCustomerId() == null || !customerDto.getCustomerId().matches("^C\\d{4}$")) {
            return new ResponseEntity<>("Customer ID is empty or invalid! It should match 'CUS-000' format.", HttpStatus.BAD_REQUEST);
        }
        if (customerDto.getCustomerName() == null || !customerDto.getCustomerName().matches("^([A-Z][a-z]+)(\\s[A-Z][a-z]+)*$")) {
            return new ResponseEntity<>("Customer Name is empty or invalid! It should contain at least 4 alphabetic characters.", HttpStatus.BAD_REQUEST);
        }

        if (customerDto.getCustomerAddress() == null || !customerDto.getCustomerAddress().matches("^[A-Za-z0-9\\s,./-]+$")) {
            return new ResponseEntity<>("Customer Address is empty or invalid! It should contain at least 5 alphanumeric characters.", HttpStatus.BAD_REQUEST);
        }

        if (customerDto.getCustomerSalary() <= 0) {
            return new ResponseEntity<>("Customer Salary is empty or invalid! It must be greater than 0.", HttpStatus.BAD_REQUEST);
        }

      if (customerDto==null){
          logger.error("CustomerDto is null");
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      } else {
          try {
              customerService.saveCustomer(customerDto);
              logger.info("Customer saved successfully");
              return new ResponseEntity<>(HttpStatus.CREATED);
          }catch (DataPersistFailException e){
              return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
          }catch (Exception e){
              return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }
    }
    @GetMapping("/{customerId}")
    public CustomerResponse getCustomer(@PathVariable("customerId")String customerId){
        return customerService.getSelectCustomer(customerId);
    }
    @GetMapping(value = "allcustomers",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerDtos = customerService.getAllCustomers();
        logger.info("Fetched {} customers from the database", customerDtos.size());  // Log the number of customers fetched

        // Log each customer in the list
        for (CustomerDto customer : customerDtos) {
            logger.info("Customer DTO: {}", customer);  // Assuming CustomerDto has a proper toString() method
        }
        return customerDtos;  // Return the list of customers
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCustomer(@PathVariable ("customerId") String customerId, @RequestBody CustomerDto customerDto) {

        if (customerDto.getCustomerId() == null || !customerDto.getCustomerId().matches("^C\\d{4}$")) {
            return new ResponseEntity<>("Customer ID is empty or invalid!", HttpStatus.BAD_REQUEST);
        }
        if (customerDto.getCustomerName() == null || !customerDto.getCustomerName().matches("^([A-Z][a-z]+)(\\s[A-Z][a-z]+)*$")) {
            return new ResponseEntity<>("Customer Name is empty or invalid!", HttpStatus.BAD_REQUEST);
        }

        if (customerDto.getCustomerAddress() == null || !customerDto.getCustomerAddress().matches("^[A-Za-z0-9\\s,./-]+$")) {
            return new ResponseEntity<>("Customer Address is empty or invalid! ", HttpStatus.BAD_REQUEST);
        }

        if (customerDto.getCustomerSalary() <= 0) {
            return new ResponseEntity<>("Customer Salary is empty or invalid!", HttpStatus.BAD_REQUEST);
        }


        try {
            if(customerDto == null && (customerId != null||customerId.isEmpty())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            customerService.updateCustomer(customerId, customerDto);
            logger.info("customer updated successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CustomerNotFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value ="/{customerId}" )
    public ResponseEntity<Void> deleteCustomer(@PathVariable ("customerId") String customerId) {
        try {
            customerService.deleteCustomer(customerId);
            logger.info(customerId+"customer deleted!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);


        } catch (CustomerNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
