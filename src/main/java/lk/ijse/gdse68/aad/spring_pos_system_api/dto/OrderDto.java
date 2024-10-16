package lk.ijse.gdse68.aad.spring_pos_system_api.dto;

import lk.ijse.gdse68.aad.spring_pos_system_api.custom.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto implements SuperDto, OrderResponse {
    private String orderId;
    private String customerId;
    private List<OrderDetailDto> orderDetails;
    private LocalDateTime orderDateTime;
    private double total;
}
