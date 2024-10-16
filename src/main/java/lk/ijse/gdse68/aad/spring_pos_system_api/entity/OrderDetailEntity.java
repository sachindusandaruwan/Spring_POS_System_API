package lk.ijse.gdse68.aad.spring_pos_system_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orderDetails")
@Entity
public class OrderDetailEntity implements SuperEntity {
    @Id
    private String orderDetailsId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "itemCode", referencedColumnName = "itemCode")
    private ItemEntity item;
    private int qty;
    private double unitPrice;
    private String description;
}
