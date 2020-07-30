package cn.weidea.wesports.service;

import cn.weidea.wesports.entity.Company;
import cn.weidea.wesports.service.admin.*;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    public Company company;

    public int create(String name,String uscc,String password,String profile){

    }
}
