package cn.weidea.wesports.service.impl;

import cn.weidea.wesports.entity.*;
import cn.weidea.wesports.mapper.CompanyMapper;
import cn.weidea.wesports.mapper.OrderMapper;
import cn.weidea.wesports.mapper.UserInfoMapper;
import cn.weidea.wesports.service.order.IOrderService;
import cn.weidea.wesports.vo.OrderVO;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Component
@Service(version = "${wesports.service.version}", interfaceClass = IOrderService.class)
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public OrderDto create(OrderVO orderVO) {
        if(orderVO == null) {
            return null;
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
        order.setPoints(orderVO.getPoints());
        order.setStat(0);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        int ret = orderMapper.insert(order);
        OrderDto dto = new OrderDto();
        //dto.setOrderId(orderId);
        BeanUtils.copyProperties(order, dto);
        if (ret>0)
            return dto;
        else
            return null;
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

        List<Order> orders = orderMapper.selectList(queryWrapper);
        log.info("orders size:" + orders.size());
        if (orders.size() >= 1) {
            //user name
            QueryWrapper<UserInfo> userNameQuery = new QueryWrapper<>();
            userNameQuery.eq("user_id",userId);
            String name = userInfoMapper.selectOne(userNameQuery).getName();
            dto.setUsername(name);
            Order order = orders.get(0);
//        Order order = orderMapper.selectOne(queryWrapper);
            String temperature = new DecimalFormat("0.00").format(temp);
            dto.setTemp(temperature);
            if (order != null && temp < 38 && health) {
                dto.setStat(String.valueOf(2));
                order.setStat(2);
                orderMapper.updateById(order);
                dto.setHealth("健康");
            } else {
                dto.setHealth("不健康");
                dto.setStat(String.valueOf(order.getStat()));
            }
            //上传到链
            log.info(dto.toString());
            return dto;
        }
        else {
            OrderCheckDto ret = new OrderCheckDto();
            ret.setStat(String.valueOf(-1));

            ret.setTemp(new DecimalFormat("0.00").format(getTemp()));
            if (temp > 38) {
                ret.setHealth("不健康");
            }
            else {
                if(!getHealthMessage())
                    ret.setHealth("不健康");
                else ret.setHealth("健康");
            }
            QueryWrapper<UserInfo> userNameQuery = new QueryWrapper<>();
            userNameQuery.eq("user_id",userId);
            String name = userInfoMapper.selectOne(userNameQuery).getName();
            ret.setUsername(name);
            return ret;
        }

    }

    @Override
    public OrderDto payOrder(String orderId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);

        Order order = orderMapper.selectOne(queryWrapper);
        order.setStat(1);
        order.setUpdateTime(new Date());
        orderMapper.updateById(order);
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto);
        return orderDto;
    }

    @Override
    public List<CompanyOrderDto> getCompanyOrders(Integer companyId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id", companyId);
        List<Order> orders = orderMapper.selectList(queryWrapper);
        Company company = companyMapper.selectById(companyId);
        List<CompanyOrderDto> dtos= new ArrayList<>();
        log.info(String.valueOf(orders.size()));
        log.info(company.toString());
        for (int i = 0; i < orders.size(); i++) {
            CompanyOrderDto dto = new CompanyOrderDto();
            BeanUtils.copyProperties(orders.get(i), dto);
            dtos.add(dto);
        }
        return dtos;
    }


    /* helper methods */
    private float getTemp() {
        Random random = new Random();
        return (random.nextFloat() * 4) + 35;
    }

    private boolean getHealthMessage() {
        Random random = new Random();
        return random.nextFloat() > 0.02;
    }
}
