package lk.ijse.gdse68.aad.spring_pos_system_api.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.spring_pos_system_api.custom.CustomerErrorResponse;
import lk.ijse.gdse68.aad.spring_pos_system_api.custom.ItemErrorResponse;
import lk.ijse.gdse68.aad.spring_pos_system_api.custom.ItemResponse;
import lk.ijse.gdse68.aad.spring_pos_system_api.dao.ItemDao;
import lk.ijse.gdse68.aad.spring_pos_system_api.dto.ItemDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.entity.CustomerEntity;
import lk.ijse.gdse68.aad.spring_pos_system_api.entity.ItemEntity;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.CustomerNotFound;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.DataPersistFailException;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.ItemNotFound;
import lk.ijse.gdse68.aad.spring_pos_system_api.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private Mapping mapping;


    @Override
    public void saveItem(ItemDto itemDto) {
        if (itemDao.existsById(itemDto.getItemCode())) {
            throw new DataPersistFailException("Item already exists");
        }else {
            ItemEntity savedItem = itemDao.save(mapping.convertToEntity(itemDto));
            if (savedItem == null && savedItem.getItemCode() == null) {
                throw new DataPersistFailException("Item not found");
            }
        }
    }

    @Override
    public ItemResponse getSelectItem(String itemCode) {
        if (itemDao.existsById(itemCode)){
            return mapping.convertToDto(itemDao.getById(itemCode));
        }else {
            return new ItemErrorResponse(0,"Item not saved!!");
        }
    }

    @Override
    public List<ItemDto> getAllItems() {
        return mapping.convertToItemDtos(itemDao.findAll());
    }

    @Override
    public void updateItem(String itemCode, ItemDto itemDto) {
        Optional<ItemEntity> tempItemByItemCode = itemDao.findById(itemCode);
        if (!tempItemByItemCode.isPresent()) {
            throw new ItemNotFound("Item not found");
        }else {
            tempItemByItemCode.get().setItemName(itemDto.getItemName());
            tempItemByItemCode.get().setItemQty(itemDto.getItemQty());
            tempItemByItemCode.get().setItemPrice(itemDto.getItemPrice());
        }
    }

    @Override
    public void deleteItem(String itemCode) {
        Optional<ItemEntity> findCode=itemDao.findById(itemCode);

        if(!findCode.isPresent()){
            throw new ItemNotFound("Item is not found!!");

        }else {
            itemDao.deleteById(itemCode);

        }
    }
}
