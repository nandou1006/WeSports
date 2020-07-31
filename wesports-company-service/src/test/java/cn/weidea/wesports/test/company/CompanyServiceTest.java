package cn.weidea.wesports.test.company;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.enums.GroupIdEnum;
import cn.weidea.wesports.service.app.CompanyServiceApplication;
import cn.weidea.wesports.service.common.login.ILoginService;
import cn.weidea.wesports.service.company.ICompanyService;
import cn.weidea.wesports.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest(classes = CompanyServiceApplication.class)
@ExtendWith(SpringExtension.class)
@Slf4j
public class CompanyServiceTest {

    @Autowired
    ICompanyService companyService;

    @Test
    public void test() {

    }
}


