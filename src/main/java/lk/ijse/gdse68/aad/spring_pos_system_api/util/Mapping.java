package lk.ijse.gdse68.aad.spring_pos_system_api.util;

import lk.ijse.gdse68.aad.spring_pos_system_api.dto.CustomerDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.dto.ItemDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.entity.CustomerEntity;
import lk.ijse.gdse68.aad.spring_pos_system_api.entity.ItemEntity;
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


    //Items

    public ItemEntity convertToEntity(ItemDto itemDto){
        return modelMapper.map(itemDto, ItemEntity.class);
    }

    public ItemDto convertToDto(ItemEntity itemEntity){
        return modelMapper.map(itemEntity, ItemDto.class);
    }

    public List<ItemDto> convertToItemDtos(List<ItemEntity> itemList){
        return modelMapper.map(itemList, new TypeToken<List<ItemDto>>(){}.getType());
    }
}
