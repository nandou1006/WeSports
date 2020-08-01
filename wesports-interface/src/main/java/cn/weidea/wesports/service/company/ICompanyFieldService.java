package cn.weidea.wesports.service.company;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.entity.CompanyField;
import cn.weidea.wesports.vo.CompanyFieldVo;
import org.springframework.stereotype.Repository;

public interface ICompanyFieldService {

    public CommonResult createField(CompanyField companyField);

    public CommonResult delectField(CompanyField companyField);

    public CommonResult selectTypeById(int id);

    public CommonResult selectAllField();

}
