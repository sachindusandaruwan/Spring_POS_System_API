package lk.ijse.gdse68.aad.spring_pos_system_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "customer")
@Entity
public class CustomerEntity implements SuperEntity{
    @Id
    private String customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;

}
