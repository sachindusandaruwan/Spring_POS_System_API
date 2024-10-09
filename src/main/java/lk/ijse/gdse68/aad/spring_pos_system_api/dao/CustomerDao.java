package lk.ijse.gdse68.aad.spring_pos_system_api.dao;

import lk.ijse.gdse68.aad.spring_pos_system_api.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<CustomerEntity,String> {
}
