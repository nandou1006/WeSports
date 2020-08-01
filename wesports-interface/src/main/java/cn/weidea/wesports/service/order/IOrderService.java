package cn.weidea.wesports.service.order;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.entity.Order;
import cn.weidea.wesports.entity.OrderCheckDto;
import cn.weidea.wesports.entity.OrderDto;
import cn.weidea.wesports.vo.OrderVO;

import java.util.List;

public interface IOrderService {
    //创建
    OrderDto create(OrderVO orderVO);
    //获取某个用户的订单列表
    List<OrderDto> getAllOrderList(String userId);
    //获取单个订单
    OrderDto getOneOrder(String orderId);
    //订单验票
    OrderCheckDto check(String userId, Integer companyId);
    //支付
    OrderDto payOrder(String orderId);
}
