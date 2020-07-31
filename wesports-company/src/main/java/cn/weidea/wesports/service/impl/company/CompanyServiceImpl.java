package cn.weidea.wesports.service.impl.company;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.entity.Company;
import cn.weidea.wesports.enums.ErrorCodeEnum;
import cn.weidea.wesports.mapper.CompanyMapper;
import cn.weidea.wesports.redis.RedisUtils;
import cn.weidea.wesports.service.company.ICompanyService;
import cn.weidea.wesports.vo.CompanyVo;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    public CompanyMapper companyMapper;

    @Override
    public CommonResult create(CompanyVo companyVo){
        Company company = new Company();
        company.setName(companyVo.getName());
        company.setUscc(companyVo.getUscc());
        company.setPassword(companyVo.getPassword());
        company.setProfile(companyVo.getProfile());
        company.setAddress(companyVo.getAddress());
        int result = companyMapper.insert(company);
        if(result != 0){
            return CommonResult.success(result);
        }else{
            return CommonResult.failure(ErrorCodeEnum.CREATE_FAIL);
        }
    }

    @Override
    public CommonResult delect(String id){
        if(id != null){
            int result = companyMapper.deleteById(id);
            if(result != 0){
                return CommonResult.success(result);
            }else{
                return CommonResult.failure(ErrorCodeEnum.DELECT_FAIL);
            }
        }else{
            return CommonResult.failure(ErrorCodeEnum.ID_ERROR);//错误码：id不存在
        }
    }

    @Override
    public CommonResult upDate(String id,String password){
        if(id != null){
            Company company = new Company();
            company.setPassword(password);
            int result = companyMapper.updateById(company);
            if(result != 0){
                return CommonResult.success(result);
            }else{
                return CommonResult.failure(ErrorCodeEnum.NODATA);
            }
        }else{
            return CommonResult.failure(ErrorCodeEnum.ID_ERROR);//错误码：id不存在
        }
    }

    @Override
    public CommonResult select(String id){
        Company company = companyMapper.selectById(id);
        return CommonResult.success(company);
    }

    @Override
    public CommonResult selectAll(){
        QueryWrapper qw = new QueryWrapper();
        qw.isNotNull("id");
        List list = companyMapper.selectList(qw);
        if(!list.isEmpty()){
            return CommonResult.success(list);
        }else{
            return CommonResult.failure(ErrorCodeEnum.NODATA);
        }
    }
}
