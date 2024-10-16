package lk.ijse.gdse68.aad.spring_pos_system_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "orders")
@Entity
public class OrderEntity implements SuperEntity{
    @Id
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    private CustomerEntity customer;
    private LocalDateTime orderTimeDate;
    private double total;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailEntity> orderDetails;
}
