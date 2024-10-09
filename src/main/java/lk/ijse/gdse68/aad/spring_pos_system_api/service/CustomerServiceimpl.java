package lk.ijse.gdse68.aad.spring_pos_system_api.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.spring_pos_system_api.dao.CustomerDao;
import lk.ijse.gdse68.aad.spring_pos_system_api.dto.CustomerDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.entity.CustomerEntity;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.DataPersistFailException;
import lk.ijse.gdse68.aad.spring_pos_system_api.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
