package lk.ijse.gdse68.aad.spring_pos_system_api.service;

import lk.ijse.gdse68.aad.spring_pos_system_api.custom.ItemResponse;
import lk.ijse.gdse68.aad.spring_pos_system_api.dto.ItemDto;

public interface ItemService {
     void saveItem(ItemDto itemDto);

     ItemResponse getSelectItem(String itemCode);
}
