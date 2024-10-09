package lk.ijse.gdse68.aad.spring_pos_system_api.custom;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerErrorResponse implements CustomerResponse {
    private int errorCode;
    private String errorMessage;
}
