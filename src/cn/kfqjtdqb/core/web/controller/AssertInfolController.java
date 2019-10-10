package cn.kfqjtdqb.core.web.controller;


import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.service.AssertInfolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AssertInfolController {

    // 依赖注入
    @Autowired
    private AssertInfolService assertInfolService;

    @RequestMapping(value = "/assertInfol")
    public String showAssertInfol() {
        return "redirect:/assertInfol/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertInfol/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String assert_num, Model model) {

        Page<AssertInfol> assertInfolList = assertInfolService.findAssertInfolList(page, rows, assert_num);
        model.addAttribute("page", assertInfolList);
        //参数回显
        model.addAttribute("assert_num", assert_num);
        return "assertInfol";
    }

    @RequestMapping("/assertInfol/edit")
    @ResponseBody
    public AssertInfol getAssertInfolById(Long id) {
        AssertInfol assertInfol = assertInfolService.getAssertInfolById(id);
        System.out.println(assertInfol);
        return assertInfol;
    }

    @RequestMapping("/assertInfol/update")
    @ResponseBody
    public String updateAssertInfol(AssertInfol assertInfol) {
        assertInfolService.updateAssertInfol(assertInfol);
        return "OK";
    }

    @RequestMapping("/assertInfol/delete")
    @ResponseBody
    public String deleteAssertInfol(Long id) {
        assertInfolService.deleteAssertInfol(id);
        return "OK";
    }


    /**
     * 创建客户
     */
    @RequestMapping("/assertInfol/create")
    @ResponseBody
    public String caseCreate(String assert_num, String card_num, String community_name, String building_num, String room_number, String floorage, String floor_state, String watermeter_num, String electricmeter_num) {

        AssertInfol assertInfol = new AssertInfol();
        assertInfol.setAssert_num(assert_num);
        assertInfol.setCard_num(card_num);
        assertInfol.setCommunity_name(community_name);
        assertInfol.setBuilding_num(building_num);
        assertInfol.setRoom_number(room_number);
        assertInfol.setFloorage(Double.parseDouble(floorage));
        assertInfol.setFloor_state(floor_state.charAt(0));
        assertInfol.setWatermeter_num(watermeter_num);
        assertInfol.setElectricmeter_num(electricmeter_num);
        int rows = assertInfolService.createAssertInfol(assertInfol);
        if (rows > 0) {
            return "OK";
        } else {
            return "FALSE";
        }
    }

    @RequestMapping("/assertInfol/idlestate")
    @ResponseBody
    public List<AssertInfol> getAssertInfolIdleState() {
        List<AssertInfol> assertInfols = assertInfolService.findAssertInfolIdleStateList();
        return assertInfols;
    }

}
