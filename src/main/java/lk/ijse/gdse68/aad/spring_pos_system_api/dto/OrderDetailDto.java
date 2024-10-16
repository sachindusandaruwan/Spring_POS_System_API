package lk.ijse.gdse68.aad.spring_pos_system_api.dto;

import lk.ijse.gdse68.aad.spring_pos_system_api.custom.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDto implements SuperDto, OrderResponse {
    private String orderId;
    private String itemCode;
    private int qty;
    private double unitPrice;
    private double total;
}
