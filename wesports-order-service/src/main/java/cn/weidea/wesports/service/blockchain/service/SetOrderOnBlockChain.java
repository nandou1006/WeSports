package cn.weidea.wesports.service.blockchain.service;

import cn.weidea.wesports.entity.OrderDto;
import cn.weidea.wesports.service.blockchain.vo.ChainVo;
import cn.weidea.wesports.service.blockchain.vo.DepositEvidence;
import org.springframework.stereotype.Component;

@Component
public class SetOrderOnBlockChain {

    public DepositEvidence set(String userId,OrderDto orderDto){
        DepositEvidence dee = new DepositEvidence();
        ChainVo chainVo = new ChainVo(userId,"or");
        //Todo 上传订单至区块链
        return dee;
    }
}
