package lk.ijse.gdse68.aad.spring_pos_system_api.controller;

import lk.ijse.gdse68.aad.spring_pos_system_api.dto.CustomerDto;
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

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private final CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDto customerDto ) {
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
}
