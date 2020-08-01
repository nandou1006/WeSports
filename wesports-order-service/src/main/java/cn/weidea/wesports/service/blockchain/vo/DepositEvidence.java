package cn.weidea.wesports.service.blockchain.vo;

import lombok.Data;


@Data
public class DepositEvidence{

    private String transactionHash;

    private int transactionIndex;

    private String blockHash;

    private int blockNumber;

    private int gasUsed;

    private String contractAddress;

    private String root;

    private String status;

    private String message;

    private String from;

    private String to;

    private String input;

    private String output;

    private int[] logs;

    private String logsBloom;

    private String txProof;

    private String receiptProof;

}
