package lk.ijse.gdse68.aad.spring_pos_system_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "item")
@Entity
public class ItemEntity implements SuperEntity {
    @Id
    private String itemCode;
    private String itemName;
    private int itemQty;
    private double itemPrice;
}
