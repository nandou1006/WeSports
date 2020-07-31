package cn.weidea.wesports.web.controller.company;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.redis.RedisUtils;
import cn.weidea.wesports.service.company.ICompanyService;
import cn.weidea.wesports.vo.CompanyVo;
import cn.weidea.wesports.web.service.impl.common.LoginUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Reference
    private ICompanyService companyService;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult createCompany(@RequestParam CompanyVo companyVo){
        return companyService.create(companyVo);
    }

    @RequestMapping(value = "/delect",method = RequestMethod.DELETE)
    public CommonResult delectCompany(HttpServletRequest request){
        String userId = LoginUtils.getUserId(request, redisUtils);
        return companyService.delect(userId);
    }

    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public CommonResult selectCompany(HttpServletRequest request){
        String userId = LoginUtils.getUserId(request, redisUtils);
        return companyService.select(userId);
    }

    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public CommonResult selectAllCompany(HttpServletRequest request){
        return companyService.selectAll();
    }
}
