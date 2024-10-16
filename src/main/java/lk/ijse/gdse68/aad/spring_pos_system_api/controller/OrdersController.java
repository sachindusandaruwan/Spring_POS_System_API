package lk.ijse.gdse68.aad.spring_pos_system_api.controller;


import lk.ijse.gdse68.aad.spring_pos_system_api.dto.OrderDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.DataPersistFailException;
import lk.ijse.gdse68.aad.spring_pos_system_api.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    @Autowired
    private final OrdersService placeOrderService;

    Logger logger= LoggerFactory.getLogger(OrdersController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto) {
        if (orderDto == null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                placeOrderService.saveOrder(orderDto);
                logger.info("Order saved : " + orderDto);
                return ResponseEntity.created(null).build();
            } catch (DataPersistFailException e) {
                return ResponseEntity.badRequest().build();
            } catch (Exception e) {
                logger.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            }
        }
    }
}
