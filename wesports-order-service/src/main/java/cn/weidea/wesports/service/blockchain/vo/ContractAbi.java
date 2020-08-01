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
public class ContractAbi {

    private boolean constant;
    private List<Inputs> inputs;
    private String name;
    private List<String> outputs;
    private boolean payable;
    private String stateMutability;
    private String type;
    private int funcId;
    public void setConstant(boolean constant) {
         this.constant = constant;
     }
     public boolean getConstant() {
         return constant;
     }

    public void setInputs(List<Inputs> inputs) {
         this.inputs = inputs;
     }
     public List<Inputs> getInputs() {
         return inputs;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setOutputs(List<String> outputs) {
         this.outputs = outputs;
     }
     public List<String> getOutputs() {
         return outputs;
     }

    public void setPayable(boolean payable) {
         this.payable = payable;
     }
     public boolean getPayable() {
         return payable;
     }

    public void setStateMutability(String stateMutability) {
         this.stateMutability = stateMutability;
     }
     public String getStateMutability() {
         return stateMutability;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setFuncId(int funcId) {
         this.funcId = funcId;
     }
     public int getFuncId() {
         return funcId;
     }

}