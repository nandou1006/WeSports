package cn.weidea.wesports.service.blockchain.vo;

import com.alibaba.fastjson.JSONObject;

public class test {

    public static void main(String[] args) {
        String s = "{\n" +
                "    \"contractName\":\"dataStorage\",\n" +
                "    \"contractAddress\":\"0xef396ba8ea7f7003c054f53db11ecc789a5e1dba\",\n" +
                "    \"funcName\":\"set\",\n" +
                "    \"contractAbi\":[\n" +
                "       {\"constant\":false,\n" +
                "       \"inputs\":[{\"name\":\"id\",\n" +
                "            \"type\":\"uint256\"},\n" +
                "            {\"name\":\"data\",\"type\":\"string\"}],\n" +
                "       \"name\":\"set\",\n" +
                "       \"outputs\":[],\n" +
                "       \"payable\":false,\n" +
                "       \"stateMutability\":\"nonpayable\",\n" +
                "       \"type\":\"function\",\n" +
                "       \"funcId\":1}],\n" +
                "    \"funcParam\":[\"" +
                "userId" +
                "\",\"" +
                "orderDto" +
                "\"],\n" +
                "    \"groupId\" :\"1\",\n" +
                "    \"useAes\" : false,\n" +
                "    \"user\":\"0xf4f55528bc30da2284b3d81a7a0e2df9c72df8f7\",\n" +
                "    \"version\" : null\n" +
                "}";
        ChainVo parse = (ChainVo)JSONObject.parseObject(s, ChainVo.class);
        String q = JSONObject.toJSONString(parse);
        System.out.println(q);
        System.out.println(parse);
    }
}
