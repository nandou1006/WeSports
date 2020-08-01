/**
  * Copyright 2020 bejson.com 
  */
package cn.weidea.wesports.service.blockchain.vo;
import java.util.List;

/**
 * Auto-generated: 2020-08-02 0:26:31
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ChainVo {

    private String contractName;
    private String contractAddress;
    private String funcName;
    private List<ContractAbi> contractAbi;
    private List<String> funcParam;
    private String groupId;
    private boolean useAes;
    private String user;
    private String version;
    public void setContractName(String contractName) {
         this.contractName = contractName;
     }
     public String getContractName() {
         return contractName;
     }

    public void setContractAddress(String contractAddress) {
         this.contractAddress = contractAddress;
     }
     public String getContractAddress() {
         return contractAddress;
     }

    public void setFuncName(String funcName) {
         this.funcName = funcName;
     }
     public String getFuncName() {
         return funcName;
     }

    public void setContractAbi(List<ContractAbi> contractAbi) {
         this.contractAbi = contractAbi;
     }
     public List<ContractAbi> getContractAbi() {
         return contractAbi;
     }

    public void setFuncParam(List<String> funcParam) {
         this.funcParam = funcParam;
     }
     public List<String> getFuncParam() {
         return funcParam;
     }

    public void setGroupId(String groupId) {
         this.groupId = groupId;
     }
     public String getGroupId() {
         return groupId;
     }

    public void setUseAes(boolean useAes) {
         this.useAes = useAes;
     }
     public boolean getUseAes() {
         return useAes;
     }

    public void setUser(String user) {
         this.user = user;
     }
     public String getUser() {
         return user;
     }

    public void setVersion(String version) {
         this.version = version;
     }
     public String getVersion() {
         return version;
     }

}