package lk.ijse.gdse68.aad.spring_pos_system_api.controller;

import lk.ijse.gdse68.aad.spring_pos_system_api.dto.ItemDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.DataPersistFailException;
import lk.ijse.gdse68.aad.spring_pos_system_api.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
@CrossOrigin
public class ItemController {

    Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
   private final ItemService itemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDto itemDto) {
        if (itemDto == null) {
            logger.error("ItemDto is null");
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try {
                itemService.saveItem(itemDto);
                logger.info("Item saved");
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }
}
