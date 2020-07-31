package cn.weidea.wesports.service.company;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.vo.CompanyVo;

public interface ICompanyService {

    public CommonResult create(CompanyVo companyVo);

    public CommonResult delect(String id);

    public CommonResult upDate(String id,String password);

    public CommonResult select(String id);

    public CommonResult selectAll();
}
