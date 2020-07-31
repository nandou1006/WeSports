package cn.weidea.wesports.service.order;

import cn.weidea.wesports.entity.Order;
import cn.weidea.wesports.entity.OrderDto;
import cn.weidea.wesports.vo.OrderVO;

import java.util.List;

public interface IOrderService {
    //创建
    boolean create(OrderVO orderVO);
    //获取某个用户的订单列表
    List<OrderDto> getAllOrderList(Integer userId);
    //获取单个订单
    OrderDto getOneOrder(String orderId);
    //订单验票
    boolean check(Integer userId, Integer companyId, Integer fieldId);
}
