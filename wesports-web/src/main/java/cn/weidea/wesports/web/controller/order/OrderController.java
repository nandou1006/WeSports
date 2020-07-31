package cn.weidea.wesports.web.controller.order;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.entity.OrderDto;
import cn.weidea.wesports.service.order.OrderService;
import cn.weidea.wesports.vo.OrderVO;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {

//    @Reference(version = "${wespotrs.service.version}")
//    private OrderService orderService;

    /**
     * 查询用户订单列表
     *
     * @param orderVO
     * @return
     */
    @RequestMapping("/orders")
    public CommonResult<OrderDto> getAllOrders(@RequestParam OrderVO orderVO) {
        return null;
    }
}
