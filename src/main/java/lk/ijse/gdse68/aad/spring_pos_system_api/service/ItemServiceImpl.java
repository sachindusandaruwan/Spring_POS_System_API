package lk.ijse.gdse68.aad.spring_pos_system_api.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.spring_pos_system_api.dao.ItemDao;
import lk.ijse.gdse68.aad.spring_pos_system_api.dto.ItemDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.entity.CustomerEntity;
import lk.ijse.gdse68.aad.spring_pos_system_api.entity.ItemEntity;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.DataPersistFailException;
import lk.ijse.gdse68.aad.spring_pos_system_api.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
