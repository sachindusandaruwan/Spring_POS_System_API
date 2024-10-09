package lk.ijse.gdse68.aad.spring_pos_system_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;
}
