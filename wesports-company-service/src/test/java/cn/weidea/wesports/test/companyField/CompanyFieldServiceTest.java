package cn.weidea.wesports.test.companyField;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.service.CompanyServiceApplication;
import cn.weidea.wesports.service.company.ICompanyFieldService;
import cn.weidea.wesports.vo.CompanyVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest(classes = CompanyServiceApplication.class)
@ExtendWith(SpringExtension.class)
@Slf4j
public class CompanyFieldServiceTest {

    @Autowired
    ICompanyFieldService companyFieldService;

    @Test
    public void test() {
        CompanyVo companyVo = new CompanyVo();
        companyVo.setName("乐乐子传销公司");
        companyVo.setAddress("前海微众银行818室");
        companyVo.setPassword("666666");
        companyVo.setUscc("loverabbit");
        companyVo.setProfile("empty profile");
//        CommonResult commonResult = companyFieldService.create(companyVo);
//        System.out.println(commonResult.getData());
    }

    @Test
    public void test2() {
        CommonResult commonResult = companyFieldService.selectAllField();
        System.out.println(commonResult.getData());
    }
}


