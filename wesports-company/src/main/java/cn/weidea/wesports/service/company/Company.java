package cn.weidea.wesports.service.company;

public class Company extends AbstractCompany {
    private String companyName;

    private String uscc;

    private String password;

    private String profile;

    public void company(String companyName,String uscc,String password,String profile){
        this.companyName = companyName;
        this.uscc = uscc;
        this.password = password;
        this.profile = profile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getUscc() {
        return uscc;
    }

    public String getPassword() {
        return password;
    }

    public String getProfile() {
        return profile;
    }
}
