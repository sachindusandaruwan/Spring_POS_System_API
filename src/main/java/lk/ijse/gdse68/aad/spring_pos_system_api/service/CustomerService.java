package lk.ijse.gdse68.aad.spring_pos_system_api.service;

import lk.ijse.gdse68.aad.spring_pos_system_api.custom.CustomerResponse;
import lk.ijse.gdse68.aad.spring_pos_system_api.dto.CustomerDto;

public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);

    CustomerResponse getSelectCustomer(String customerId);
}
