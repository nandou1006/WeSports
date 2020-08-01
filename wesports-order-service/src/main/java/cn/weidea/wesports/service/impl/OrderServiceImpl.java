package cn.weidea.wesports.service.impl;

import cn.weidea.wesports.entity.Order;
import cn.weidea.wesports.entity.OrderDto;
import cn.weidea.wesports.mapper.OrderMapper;
import cn.weidea.wesports.service.order.IOrderService;
import cn.weidea.wesports.vo.OrderVO;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
@Service(version = "${wesports.service.version}", interfaceClass = IOrderService.class)
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean create(OrderVO orderVO) {
        if(orderVO == null) {
            return false;
        }
        Order order = new Order();
        order.setId("1");
        order.setUserId(orderVO.getUserId());
        order.setCompanyId(orderVO.getCompanyId());
        order.setFieldId(orderVO.getFieldId());
        order.setCost(orderVO.getCost());
        order.setStartTime(orderVO.getStartTime());
        order.setEndTime(orderVO.getEndTime());
        order.setOrderId("2020");
        order.setStat(0);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        int ret = orderMapper.insert(order);
        return ret > 0;
    }

    @Override
    public List<OrderDto> getAllOrderList(Integer userId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Order> orders = orderMapper.selectList(queryWrapper);
        List<OrderDto> dtoList = new ArrayList<>();
        for (Order order : orders) {
            OrderDto dto = new OrderDto();
            BeanUtils.copyProperties(order, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public OrderDto getOneOrder(String orderId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        Order order = orderMapper.selectOne(queryWrapper);
        OrderDto dto = new OrderDto();
        BeanUtils.copyProperties(order, dto);
        return dto;
    }

    @Override
    public boolean check(Integer userId, Integer companyId, Integer fieldId) {
        return false;
    }


    /* helper methods */

}
