package cn.weidea.wesports.web.controller.order;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.entity.OrderDto;
import cn.weidea.wesports.service.order.OrderService;
import cn.weidea.wesports.vo.OrderVO;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {

    @Reference(version = "${wesports.service.version}")
    private OrderService orderService;

    /**
     * 查询用户订单列表
     * @param
     * @return
     */
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public CommonResult getAllOrders(@RequestBody OrderVO orderVO) {
        List<OrderDto> orderDtoList = orderService.getAllOrderList(orderVO.getUserId());
        return CommonResult.success(orderDtoList);
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public CommonResult getOneOrder(@RequestBody OrderVO orderVO) {
        OrderDto orderDto = orderService.getOneOrder(orderVO.getOrderId());
        return CommonResult.success(orderDto);
    }

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public CommonResult createOrder(@RequestBody OrderVO orderVO) {
        //创建订单
        boolean result = orderService.create(orderVO);
        if (result)
            return CommonResult.success();
        return CommonResult.failure(9000, "创建失败");
    }


}
