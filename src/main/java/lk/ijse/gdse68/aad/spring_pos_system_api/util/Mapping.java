package lk.ijse.gdse68.aad.spring_pos_system_api.util;

import lk.ijse.gdse68.aad.spring_pos_system_api.dto.CustomerDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerEntity convertToEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, CustomerEntity.class);
    }

//    public CustomerDto convertToDto(CustomerEntity customerEntity){
//        return modelMapper.map(customerEntity, CustomerDto.class);
//    }

    public CustomerDto convertToDTO(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDto.class);
    }

    public List<CustomerDto> convertToDtos(List<CustomerEntity> customerList){
        return modelMapper.map(customerList, new TypeToken<List<CustomerDto>>(){}.getType());
    }
}
