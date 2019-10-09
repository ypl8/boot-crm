package cn.kfqjtdqb.core.web.controller;

import java.util.Date;
import java.util.List;

import cn.kfqjtdqb.core.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.BaseDict;
import cn.kfqjtdqb.core.bean.Customer;
import cn.kfqjtdqb.core.service.CustomerService;
import cn.kfqjtdqb.core.service.SystemService;

import javax.servlet.http.HttpSession;


/**
 * 客户管理
 * <p>Title: CustomerController</p>
 * <p>Description: </p>
 * <p>Company: www.kfqjtdqb.cn</p>
 * @version 1.0
 */
@Controller
public class CustomerController {

	// 依赖注入
	@Autowired
	private CustomerService customerService;
	@Autowired
	private SystemService systemService;
	@Value("${customer.from.type}")
	private String FROM_TYPE;
	@Value("${customer.industry.type}")
	private String INDUSTRY_TYPE;
	@Value("${customer.level.type}")
	private String LEVEL_TYPE;
	
	@RequestMapping(value = "/customer")
	public String showCumtomer() {
		return "redirect:/customer/list.action";
	}
	
	// 客户列表
	@RequestMapping(value = "/customer/list")
	public String list(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="10")Integer rows, 
		String custName, String custSource,	String custIndustry, String custLevel, Model model) {

		Page<Customer> customers = customerService.findCustomerList(page, rows, custName, custSource, custIndustry,
				custLevel);
		model.addAttribute("page", customers);
		//客户来源
		List<BaseDict> fromType = systemService.findBaseDictListByType(FROM_TYPE);
		//客户所属行业
		List<BaseDict> industryType = systemService.findBaseDictListByType(INDUSTRY_TYPE);
		//客户级别
		List<BaseDict> levelType = systemService.findBaseDictListByType(LEVEL_TYPE);
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);
		//参数回显
		model.addAttribute("custLevel", custLevel);
		model.addAttribute("custName", custName);
		model.addAttribute("custSource", custSource);
		model.addAttribute("custIndustry", custIndustry);
		return "customer";
	}


	/**
	 * 创建客户
	 */
	@RequestMapping("/customer/create")
	@ResponseBody
	public String customerCreate(Customer customer, HttpSession session) {
		//获取Session中的当前用户信息
		User user = (User) session.getAttribute("USER_SESSION");
		//将当前用户id存储在客户对象中
		customer.setCust_create_id(user.getUser_id());
		//创建date对象
		Date date = new Date();
		//得到一个Timestamp格式的时间，存入MySQL中的时间格式“yyyy/MM/dd HH:mm:ss”
		//Timestamp timestamp = new Timestamp(date);
		customer.setCust_createtime(date);
		//执行Service,返回的是受影响的行数
		int rows = customerService.createCustomer(customer);
		if (rows > 0) {
			return "OK";
		} else {
			return "FALSE";
		}
	}



	@RequestMapping("/customer/edit")
	@ResponseBody
	public Customer getCustomerById(Long id) {
		Customer customer = customerService.getCustomerById(id);
		return customer;
	}
	
	@RequestMapping("/customer/update")
	@ResponseBody
	public String customerUpdate(Customer customer) {
		customerService.updateCustomer(customer);
		return "OK";
	}
	@RequestMapping("/customer/delete")
	@ResponseBody
	public String customerDelete(Long id) {
		customerService.deleteCustomer(id);
		return "OK";
	}

}
