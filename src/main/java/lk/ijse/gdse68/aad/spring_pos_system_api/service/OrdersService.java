package lk.ijse.gdse68.aad.spring_pos_system_api.service;

import lk.ijse.gdse68.aad.spring_pos_system_api.dto.OrderDto;

public interface OrdersService {
    String saveOrder(OrderDto orderDto);
}
