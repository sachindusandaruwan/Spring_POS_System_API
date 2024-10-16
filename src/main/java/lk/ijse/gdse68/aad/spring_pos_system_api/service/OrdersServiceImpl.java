package lk.ijse.gdse68.aad.spring_pos_system_api.service;

import lk.ijse.gdse68.aad.spring_pos_system_api.dao.ItemDao;
import lk.ijse.gdse68.aad.spring_pos_system_api.dao.OrdersDao;
import lk.ijse.gdse68.aad.spring_pos_system_api.dto.OrderDetailDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.dto.OrderDto;
import lk.ijse.gdse68.aad.spring_pos_system_api.entity.ItemEntity;
import lk.ijse.gdse68.aad.spring_pos_system_api.entity.OrderDetailEntity;
import lk.ijse.gdse68.aad.spring_pos_system_api.entity.OrderEntity;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.DataPersistFailException;
import lk.ijse.gdse68.aad.spring_pos_system_api.exception.ItemNotFound;
import lk.ijse.gdse68.aad.spring_pos_system_api.util.AppUtil;
import lk.ijse.gdse68.aad.spring_pos_system_api.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private final OrdersDao ordersDao;

    @Autowired
    private Mapping mapping;

    @Autowired
    private ItemDao itemDao;

    @Override
    public String saveOrder(OrderDto orderDto) {
        orderDto.setOrderId(AppUtil.createOrderId());
        orderDto.setOrderDateTime(AppUtil.getCurrentDateTime());
        orderDto.setTotal(orderDto.getOrderDetails().stream().mapToDouble(detail -> detail.getQty() * detail.getUnitPrice()).sum());

        OrderEntity orderEntity = mapping.convertToOrderEntity(orderDto);

        List<OrderDetailEntity> orderDetailEntities = orderDto.getOrderDetails().stream().map(detail -> {

                    OrderDetailEntity orderDetailEntity = mapping.convertToOrderDetailEntity(detail);
                    orderDetailEntity.setOrderDetailsId(AppUtil.createOrderDetailsId());
                    orderDetailEntity.setDescription("Payed");
                    orderDetailEntity.setOrder(orderEntity);
                    return orderDetailEntity;
                })
                .collect(Collectors.toList());

        orderEntity.setOrderDetails(orderDetailEntities);
        boolean allItemsUpdated = orderDto.getOrderDetails().stream().allMatch(this::updateItemQty);

        if (allItemsUpdated) {
            ordersDao.save(orderEntity);
            return "Order placed successfully";
        } else {
            throw new DataPersistFailException("place order failed");
        }


    }

    private boolean updateItemQty(OrderDetailDto orderDetailsDto) {
        ItemEntity item = itemDao.findById(orderDetailsDto.getItemCode()).orElse(null);
        if (item == null) {
            throw new ItemNotFound("Item not found");
        }

        if (item.getItemQty() < orderDetailsDto.getQty()) {
            throw new ItemNotFound("Item qty not enough");
        }

        item.setItemQty(item.getItemQty() - orderDetailsDto.getQty());
        itemDao.save(item);
        return true;
    }
}
