package cn.weidea.wesports.service.blockchain.service;

import cn.weidea.wesports.entity.OrderDto;
import cn.weidea.wesports.service.blockchain.vo.ChainVo;
import cn.weidea.wesports.service.blockchain.vo.DepositEvidence;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SetOrderOnBlockChain {


    final String s = "{\"contractAbi\":[{\"constant\":false,\"funcId\":1,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"data\",\"type\":\"string\"}],\"name\":\"set\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}],\"contractAddress\":\"0xef396ba8ea7f7003c054f53db11ecc789a5e1dba\",\"contractName\":\"dataStorage\",\"funcName\":\"set\",\"funcParam\":[\"userId\",\"orderDto\"],\"groupId\":\"1\",\"useAes\":false,\"user\":\"0xf4f55528bc30da2284b3d81a7a0e2df9c72df8f7\"}\n";

    public String set(String userId,OrderDto orderDto){
        String orderString = JSONObject.toJSONString(orderDto);
        ChainVo chainVo = changeToChainVo(userId,orderString);
        return request(chainVo); //Todo 上传订单至区块链
    }


    private String request(ChainVo chainVo) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://119.23.46.126:5002/WeBASE-Front/trans/handle";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(chainVo,httpHeaders);
        String depo = restTemplate.exchange(url,HttpMethod.POST,httpEntity,String.class ).getBody();
        DepositEvidence depositEvidence = JSONObject.parseObject(depo,DepositEvidence.class);
        System.out.println(depositEvidence.getTransactionHash());
        return depositEvidence.getTransactionHash();
    }

    private ChainVo changeToChainVo(String userId, String orderDto) {

        ChainVo chainVo = JSONObject.parseObject(s, ChainVo.class);
        chainVo.setFuncParam(Arrays.asList(userId,orderDto));
        return chainVo;
    }
}
