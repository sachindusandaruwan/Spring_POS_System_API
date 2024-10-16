package lk.ijse.gdse68.aad.spring_pos_system_api.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderErrorResponse implements OrderResponse {
    private int errorCode;
    private String errorMessage;
}
