package cn.weidea.wesports.service.companyField;

public class CompanyField extends AbstractCompanyField{
    private int companyId;
    private String type;
    private int number;
    private String remark;

    public CompanyField(int companyId, String type, int number, String remark) {
        this.companyId = companyId;
        this.type = type;
        this.number = number;
        this.remark = remark;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public String getRemark() {
        return remark;
    }
}
