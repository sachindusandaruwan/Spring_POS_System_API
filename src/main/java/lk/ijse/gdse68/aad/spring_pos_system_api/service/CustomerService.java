package lk.ijse.gdse68.aad.spring_pos_system_api.service;

import lk.ijse.gdse68.aad.spring_pos_system_api.custom.CustomerResponse;
import lk.ijse.gdse68.aad.spring_pos_system_api.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);

    CustomerResponse getSelectCustomer(String customerId);

    List<CustomerDto> getAllCustomers();

    void updateCustomer(String customerId, CustomerDto customerDto);

    void deleteCustomer(String customerId);
}
