package cn.itcast.core.web.controller;

import cn.itcast.common.utils.Page;
import cn.itcast.common.utils.StringUtils;
import cn.itcast.core.bean.BaseDict;
import cn.itcast.core.bean.Case;
import cn.itcast.core.bean.Customer;
import cn.itcast.core.bean.User;
import cn.itcast.core.service.CaseService;
import cn.itcast.core.service.CustomerService;
import cn.itcast.core.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


/**
 * 客户管理
 * <p>Title: CustomerController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p>
 *
 * @version 1.0
 */
@Controller
public class CaseController {

    // 依赖注入
    @Autowired
    private CaseService caseService;


    @RequestMapping(value = "/case")
    public String showCumtomer() {
        return "redirect:/case/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/case/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String caseNo, Model model) {

        Page<Case> casers = caseService.findCaseList(page, rows, caseNo);
        model.addAttribute("page", casers);
        //参数回显
        model.addAttribute("caseNo", caseNo);
        return "case";
    }

    @RequestMapping("/case/edit")
    @ResponseBody
    public Case getCustomerById(Long id) {
        Case caser = caseService.getCaseById(id);
        return caser;
    }

    @RequestMapping("/case/update")
    @ResponseBody
    public String customerUpdate(Case caser) {
        caseService.updateCase(caser);
        return "OK";
    }

    @RequestMapping("/case/delete")
    @ResponseBody
    public String customerDelete(Long id) {
        caseService.deleteCase(id);
        return "OK";
    }


    /**
     * 创建客户
     */
    @RequestMapping("/case/create")
    @ResponseBody
    public String caseCreate(String  case_no,String case_judgepeople,String  case_partypeople,String  case_state, String case_startime,String  case_endtime) {
        Case caser=new Case();
        caser.setCase_createtime(new Date());
        caser.setCase_no(case_no);
        caser.setCase_state(case_state);
        caser.setCase_judgepeople(case_judgepeople);
        caser.setCase_partypeople(case_partypeople);
        caser.setCase_startime( StringUtils.convert(case_startime));
        caser.setCase_endtime( StringUtils.convert(case_endtime));
        //执行Service,返回的是受影响的行数
        int rows = caseService.createCase(caser);
        if (rows > 0) {
            return "OK";
        } else {
            return "FALSE";
        }
    }

}
