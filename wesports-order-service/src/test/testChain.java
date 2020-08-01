import cn.weidea.wesports.entity.Order;
import cn.weidea.wesports.entity.OrderDto;
import cn.weidea.wesports.service.blockchain.service.SetOrderOnBlockChain;

public class testChain {
    public static void main(String[] args) {
        OrderDto orderDto = new OrderDto();
        orderDto.setCompanyId(666);
        SetOrderOnBlockChain setOrderOnBlockChain = new SetOrderOnBlockChain();
        setOrderOnBlockChain.set("2", orderDto);
    }
}
