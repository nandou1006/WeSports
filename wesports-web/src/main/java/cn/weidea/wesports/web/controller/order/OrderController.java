package cn.weidea.wesports.web.controller.order;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.entity.OrderCheckDto;
import cn.weidea.wesports.entity.OrderDto;
import cn.weidea.wesports.service.order.IOrderService;
import cn.weidea.wesports.vo.OrderVO;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {

    @Reference(version = "${wesports.service.version}")
    private IOrderService IOrderService;

    /**
     * 查询用户订单列表
     * @param
     * @return
     */
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public CommonResult getAllOrders(@RequestBody OrderVO orderVO) {
        List<OrderDto> orderDtoList = IOrderService.getAllOrderList(orderVO.getUserId());
        return CommonResult.success(orderDtoList);
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public CommonResult getOneOrder(@RequestBody OrderVO orderVO) {
        OrderDto orderDto = IOrderService.getOneOrder(orderVO.getOrderId());
        return CommonResult.success(orderDto);
    }

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public CommonResult createOrder(@RequestBody OrderVO orderVO) {
        //创建订单
        boolean result = IOrderService.create(orderVO);
        if (result)
            return CommonResult.success();
        return CommonResult.failure(9000, "创建失败");
    }

    @RequestMapping(value = "order/pay", method = RequestMethod.POST)
    public CommonResult payOrder(@RequestBody OrderVO orderVO) {
        OrderDto dto = IOrderService.payOrder(orderVO.getOrderId());
        return CommonResult.success(dto);
    }

    @RequestMapping(value = "/order/check", method = RequestMethod.POST)
    public CommonResult check(@RequestBody OrderVO orderVO) {
        OrderCheckDto orderCheckDto = IOrderService.check(orderVO.getUserId(), orderVO.getCompanyId());
        return CommonResult.success(orderCheckDto);
    }

}
