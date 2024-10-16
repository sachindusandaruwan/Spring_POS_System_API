package lk.ijse.gdse68.aad.spring_pos_system_api.dao;

import lk.ijse.gdse68.aad.spring_pos_system_api.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDao extends JpaRepository<OrderEntity,String> {
}
