package cn.weidea.wesports.service.impl;

import cn.weidea.wesports.entity.Order;
import cn.weidea.wesports.entity.OrderCheckDto;
import cn.weidea.wesports.entity.OrderDto;
import cn.weidea.wesports.mapper.CompanyMapper;
import cn.weidea.wesports.mapper.OrderMapper;
import cn.weidea.wesports.service.order.IOrderService;
import cn.weidea.wesports.vo.OrderVO;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Service(version = "${wesports.service.version}", interfaceClass = IOrderService.class)
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public boolean create(OrderVO orderVO) {
        if(orderVO == null) {
            return false;
        }
        Date date = new Date();
        Random random = new Random();
        SimpleDateFormat format = new SimpleDateFormat("HHMMssSSS");
        String orderId = format.format(date);
        Integer tmp = 100000 * random.nextInt();
        orderId += String.valueOf(tmp);
        Order order = new Order();
        order.setOrderId(orderId);
        order.setUserId(orderVO.getUserId());
        order.setCompanyId(orderVO.getCompanyId());
        order.setFieldId(orderVO.getFieldId());
        order.setCost(orderVO.getCost());
        order.setStartTime(orderVO.getStartTime());
        order.setEndTime(orderVO.getEndTime());
        order.setStat(0);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        int ret = orderMapper.insert(order);
        return ret > 0;
    }

    @Override
    public List<OrderDto> getAllOrderList(String userId) {
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
    public OrderCheckDto check(String userId, Integer companyId) {
        OrderCheckDto dto = new OrderCheckDto();
        //1.体温 2.健康信息码 3.订单状态
        //TODO 获取体温并判断是否符合
        Float temp = getTemp();

        //TODO 获取健康码信息
        boolean health = getHealthMessage();

        //check
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("company_id", companyId).eq("stat", 1);
        if(orderMapper.selectOne(queryWrapper) != null) dto.setStat(2);

        String temperature = new DecimalFormat("0.00").format(temp);
        dto.setTemp(temperature);
        if (temp < 38 && health)
            dto.setHealth("健康");
        else
            dto.setHealth("不健康");

        return dto;
    }

    @Override
    public OrderDto payOrder(String orderId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);

        Order order = orderMapper.selectOne(queryWrapper);
        order.setStat(1);
        orderMapper.updateById(order);
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto);
        return orderDto;
    }


    /* helper methods */
    private float getTemp() {
        Random random = new Random();
        return (random.nextFloat() * 4) + 35;
    }

    private boolean getHealthMessage() {
        Random random = new Random();
        return random.nextFloat() > 0.2;
    }
}
