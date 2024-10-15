package lk.ijse.gdse68.aad.spring_pos_system_api.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.spring_pos_system_api.custom.CustomerErrorResponse;
import lk.ijse.gdse68.aad.spring_pos_system_api.custom.CustomerResponse;
import lk.ijse.gdse68.aad.spring_pos_system_api.dao.CustomerDao;
import lk.ijse.gdse68.aad.spring_pos_system_api.dto.CustomerDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.entity.CustomerEntity;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.CustomerNotFound;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.DataPersistFailException;
import lk.ijse.gdse68.aad.spring_pos_system_api.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceimpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        if (customerDao.existsById(customerDto.getCustomerId())) {
            throw new DataPersistFailException("Customer already exists");
        }else {
            CustomerEntity savedCustomer = customerDao.save(mapping.convertToEntity(customerDto));
            if (savedCustomer == null && savedCustomer.getCustomerId() == null) {
                throw new DataPersistFailException("Customer not found");
            }
        }
    }

    @Override
    public CustomerResponse getSelectCustomer(String customerId) {
        if (customerDao.existsById(customerId)){
            return mapping.convertToDTO(customerDao.getById(customerId));
        }else {
            return new CustomerErrorResponse(0,"Customer not saved!!");
        }
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return mapping.convertToDtos(customerDao.findAll());
    }

    @Override
    public void updateCustomer(String customerId, CustomerDto customerDto) {
        Optional<CustomerEntity> tempCustomerById = customerDao.findById(customerId);
        if (!tempCustomerById.isPresent()) {
            throw new CustomerNotFound("customer not found");
        }else {
            tempCustomerById.get().setCustomerName(customerDto.getCustomerName());
            tempCustomerById.get().setCustomerAddress(customerDto.getCustomerAddress());
            tempCustomerById.get().setCustomerSalary(customerDto.getCustomerSalary());
        }
    }

    @Override
    public void deleteCustomer(String customerId) {

        Optional<CustomerEntity> findId=customerDao.findById(customerId);

        if(!findId.isPresent()){
            throw new CustomerNotFound("Customer is not found!!");

        }else {
            customerDao.deleteById(customerId);

        }
    }
}
