package cn.weidea.wesports.service.impl;

import cn.weidea.wesports.entity.Order;
import cn.weidea.wesports.entity.OrderDto;
import cn.weidea.wesports.mapper.OrderMapper;
import cn.weidea.wesports.service.order.OrderService;
import cn.weidea.wesports.vo.OrderVO;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service(version = "${wesports.service.version}", interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean create(OrderVO orderVO) {
        if(orderVO == null) {
            return false;
        }
        Order order = new Order();
        order.setId(1);
//        order.setUserId(orderVO.getUserId());
//        order.setCompanyId(orderVO.getCompanyId());
//        order.setFieldId(orderVO.getFieldId());
//        order.setCost(orderVO.getCost());
//        order.setStartTime(orderVO.getStartTime());
//        order.setEndTime(orderVO.getEndTime());
//        order.setOrderId("2020");
//        order.setStat(0);
        int ret = orderMapper.insert(order);
        return ret > 0;
    }

    @Override
    public List<OrderDto> getAllOrderList(Integer userId) {
        return null;
    }

    @Override
    public OrderDto getOneOrder(String orderId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
//        queryWrapper.
//        orderMapper.selectOne()
        return null;
    }

    @Override
    public boolean check(Integer userId, Integer companyId, Integer fieldId) {
        return false;
    }


    /* helper methods */

}
