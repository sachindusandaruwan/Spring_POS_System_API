package lk.ijse.gdse68.aad.spring_pos_system_api.controller;

import lk.ijse.gdse68.aad.spring_pos_system_api.custom.CustomerResponse;
import lk.ijse.gdse68.aad.spring_pos_system_api.custom.ItemResponse;
import lk.ijse.gdse68.aad.spring_pos_system_api.dto.CustomerDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.dto.ItemDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.CustomerNotFound;
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

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
@CrossOrigin
public class ItemController {

    Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
   private final ItemService itemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveItem(@RequestBody ItemDto itemDto) {

        if (itemDto.getItemCode() == null || !itemDto.getItemCode().matches("^I\\d{4}$")) {
            return new ResponseEntity<>("Item code is empty or invalid! ", HttpStatus.BAD_REQUEST);
        }

        if (itemDto.getItemName() == null || !itemDto.getItemName().matches("^([A-Z][a-z]+)(\\s[A-Z][a-z]+)*$")) {
            return new ResponseEntity<>("Item name is empty or invalid! ", HttpStatus.BAD_REQUEST);
        }
        if (itemDto.getItemQty() <= 0) {
            return new ResponseEntity<>("Item quantity is empty or invalid! It must be greater than 0.", HttpStatus.BAD_REQUEST);
        }

        if (itemDto.getItemPrice() <= 0) {
            return new ResponseEntity<>("Item price is empty or invalid!", HttpStatus.BAD_REQUEST);
        }

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

    @GetMapping("/{itemCode}")
    public ItemResponse getItem(@PathVariable("itemCode")String itemCode){
        return itemService.getSelectItem(itemCode);
    }

    @GetMapping(value = "allItems",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getAllItems() {
        List<ItemDto> ItemDtos = itemService.getAllItems();
        logger.info("Fetched {} Items from the database", ItemDtos.size());

        for (ItemDto itemDto : ItemDtos) {
            logger.info("Item DTO: {}", itemDto);
        }
        return ItemDtos;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{itemCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateItem(@PathVariable ("itemCode") String itemCode, @RequestBody ItemDto itemDto) {


        if (itemDto.getItemCode() == null || !itemDto.getItemCode().matches("^I\\d{4}$")) {
            return new ResponseEntity<>("Item code is empty or invalid! ", HttpStatus.BAD_REQUEST);
        }

        if (itemDto.getItemName() == null || !itemDto.getItemName().matches("^([A-Z][a-z]+)(\\s[A-Z][a-z]+)*$")) {
            return new ResponseEntity<>("Item name is empty or invalid! ", HttpStatus.BAD_REQUEST);
        }
        if (itemDto.getItemQty() <= 0) {
            return new ResponseEntity<>("Item quantity is empty or invalid! It must be greater than 0.", HttpStatus.BAD_REQUEST);
        }

        if (itemDto.getItemPrice() <= 0) {
            return new ResponseEntity<>("Item price is empty or invalid!", HttpStatus.BAD_REQUEST);
        }


        try {
            if(itemDto == null && (itemCode != null||itemCode.isEmpty())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.updateItem(itemCode, itemDto);
            logger.info("Item updated successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CustomerNotFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value ="/{itemCode}" )
    public ResponseEntity<Void> deleteItem(@PathVariable ("itemCode") String itemCode) {
        try {
            itemService.deleteItem(itemCode);
            logger.info(itemCode+"item deleted!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);


        } catch (CustomerNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
