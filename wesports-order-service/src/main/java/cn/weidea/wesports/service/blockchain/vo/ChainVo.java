package cn.weidea.wesports.service.blockchain.vo;

import cn.weidea.wesports.entity.OrderDto;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@Data
public class ChainVo implements Serializable {

    private String contractName = "dataStorage";

    private String contractAddress = "0xef396ba8ea7f7003c054f53db11ecc789a5e1dba";

    private String funcName = "set";

    private ArrayList contractAbi;

    private ArrayList funcParam;



    public ChainVo(String userId,String orderMessage){
        this.funcParam = new ArrayList();
        funcParam.add(userId);
        funcParam.add(orderMessage);
        init();
    }

    private void init() {
        ArrayList<HashMap> contractAbi = new ArrayList<>();
        ArrayList<HashMap> inputs = new ArrayList<>();
        ArrayList<HashMap> inputsInner = new ArrayList<>();
        HashMap contractAbiMap = new HashMap();
////
        HashMap inputsInnerMap1 = new HashMap();
        HashMap inputsInnerMap2 = new HashMap();
        inputsInnerMap1.put("name","id");
        inputsInnerMap1.put("type","uint256");
        inputs.add(inputsInnerMap1);
        inputsInnerMap2.put("name","data");
        inputsInnerMap2.put("type","string");
        inputs.add(inputsInnerMap2);
////
        contractAbiMap.put("constant", false);
        contractAbiMap.put("inputs", inputs);
        contractAbiMap.put("name", "set");
        contractAbiMap.put("outputs", null);//是否用null?????
        contractAbiMap.put("payable","false");
        contractAbiMap.put("stateMutability", "nonpayable");
        contractAbiMap.put("type", "function");
        contractAbiMap.put("funcId", "1");
        this.contractAbi = contractAbi;
    }



}
