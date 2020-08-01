package cn.weidea.wesports.web.controller.order;

import cn.weidea.wesports.entity.*;
import cn.weidea.wesports.service.order.IOrderService;
import cn.weidea.wesports.vo.OrderVO;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class OrderController {

    @Reference(version = "${wesports.service.version}")
    private IOrderService IOrderService;

    /**
     * 查询用户订单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/api/v1/orders", method = RequestMethod.POST)
    public CommonResult getAllOrders(@RequestBody OrderVO orderVO) {
        List<OrderDto> orderDtoList = IOrderService.getAllOrderList(orderVO.getUserId());
        if (orderDtoList == null)
            return CommonResult.success("没有订单数据");
        return CommonResult.success(orderDtoList);
    }

    @RequestMapping(value = "/api/v1/order", method = RequestMethod.POST)
    public CommonResult getOneOrder(@RequestBody OrderVO orderVO) {
        OrderDto orderDto = IOrderService.getOneOrder(orderVO.getOrderId());
        return CommonResult.success(orderDto);
    }

    @RequestMapping(value = "/api/v1/order/create", method = RequestMethod.POST)
    public CommonResult createOrder(@RequestBody OrderVO orderVO) {
        //创建订单
        OrderDto result = IOrderService.create(orderVO);
        if (result != null)
            return CommonResult.success(result);
        return CommonResult.failure(9000, "创建失败");
    }

    @RequestMapping(value = "/api/v1/order/pay", method = RequestMethod.POST)
    public CommonResult payOrder(@RequestBody OrderVO orderVO) {
        OrderDto dto = IOrderService.payOrder(orderVO.getOrderId());
        return CommonResult.success(dto);
    }

    @RequestMapping(value = "/order/check", method = RequestMethod.POST)
    @CrossOrigin
    public CommonResult check(@RequestBody OrderVO orderVO) {
        OrderCheckDto orderCheckDto = IOrderService.check(orderVO.getUserId(), orderVO.getCompanyId());
        return CommonResult.success(orderCheckDto);
    }

    //
    @RequestMapping(value = "/api/v1/orders/company", method = RequestMethod.POST)
    public Object getCompanyOrders(@RequestBody OrderVO orderVO) {
        List<CompanyOrderDto> dtos = IOrderService.getCompanyOrders(orderVO.getCompanyId());
        log.info("{}", dtos);
        Map<String, Object> result = new HashMap<>();
        result.put("total", dtos.size());
        result.put("rows", dtos);
        return result;
    }

}
