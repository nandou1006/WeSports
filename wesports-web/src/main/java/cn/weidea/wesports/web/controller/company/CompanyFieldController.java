package cn.weidea.wesports.web.controller.company;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.entity.CompanyField;
import cn.weidea.wesports.redis.RedisUtils;
import cn.weidea.wesports.service.impl.company.CompanyFieldServiceImpl;
import cn.weidea.wesports.vo.CompanyFieldVo;
import cn.weidea.wesports.web.service.impl.common.LoginUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/companyField")
public class CompanyFieldController {

    @Reference
    private CompanyFieldServiceImpl companyFieldService;

    @Autowired
    private CompanyField companyField;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult createField(@RequestParam CompanyFieldVo companyFieldVo, HttpServletRequest httpServletRequest){
       int userId =  Integer.parseInt(LoginUtils.getUserId(httpServletRequest,redisUtils));
       companyField.setCompanyId(userId);
       companyField.setOccupy(0);
       companyField.setNumber(companyFieldVo.getNumber());
       companyField.setType(companyFieldVo.getType());
       return companyFieldService.createField(companyField);
    }

    @RequestMapping(value = "/delect",method = RequestMethod.DELETE)
    public CommonResult delectField(@RequestParam CompanyFieldVo companyFieldVo,HttpServletRequest httpServletRequest){
        int userId =  Integer.parseInt(LoginUtils.getUserId(httpServletRequest,redisUtils));
        companyField.setCompanyId(userId);
        companyField.setType(companyFieldVo.getType());
        companyField.setNumber(companyFieldVo.getNumber());
        return companyFieldService.delectField(companyField);
    }

    @RequestMapping(value = "/selectTypeById",method = RequestMethod.GET)
    public CommonResult selectFieldTypeById(@RequestParam int id){
        return companyFieldService.selectTypeById(id);
    }

    @RequestMapping(value = "/selectAllField",method = RequestMethod.GET)
    public CommonResult selectAllField(){
        return companyFieldService.selectAllField();
    }


}
