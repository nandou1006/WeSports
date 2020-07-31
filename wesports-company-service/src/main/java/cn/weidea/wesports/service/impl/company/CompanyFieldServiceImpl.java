package cn.weidea.wesports.service.impl.company;


import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.entity.CompanyField;
import cn.weidea.wesports.enums.ErrorCodeEnum;
import cn.weidea.wesports.mapper.CompanyFieldMapper;
import cn.weidea.wesports.service.company.ICompanyFieldService;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Service(version = "${wesports.service.version}",interfaceClass = ICompanyFieldService.class)
@Repository
public class CompanyFieldServiceImpl implements ICompanyFieldService {

    @Autowired
    private CompanyFieldMapper companyFieldMapper;

    @Override
    public CommonResult createField(CompanyField companyField) {
        int result = companyFieldMapper.insert(companyField);
        if(result != 0){
            return CommonResult.success(result);
        }else{
            return CommonResult.failure(ErrorCodeEnum.CREATE_FAIL);
        }
    }



    @Override
    public CommonResult delectField(CompanyField companyField) {
        HashMap<String,Object> map = new HashMap();
        map.put("company_id",Integer.toString(companyField.getCompanyId()));
        map.put("type",Integer.toString(companyField.getType()));
        map.put("number",Integer.toString(companyField.getNumber()));
        int result = companyFieldMapper.deleteByMap(map);
        if(result != 0){
            return CommonResult.success(result);
        }else{
            return CommonResult.failure(ErrorCodeEnum.DELECT_FAIL);
        }
    }

    @Override
    public CommonResult selectTypeById(int id) {
        CompanyField companyField = companyFieldMapper.selectById(id);
        int type = companyField.getType();
        return CommonResult.success(type);
    }


    @Override
    public CommonResult selectAllField() {
        QueryWrapper qw = new QueryWrapper();
        qw.isNotNull("id");
        List list = companyFieldMapper.selectList(qw);
        if(!list.isEmpty()){
            return CommonResult.success(list);
        }else{
            return CommonResult.failure(ErrorCodeEnum.NODATA);
        }
    }
}
