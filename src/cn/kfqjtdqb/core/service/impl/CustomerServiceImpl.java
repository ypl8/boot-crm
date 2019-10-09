package cn.kfqjtdqb.core.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.Customer;
import cn.kfqjtdqb.core.dao.BaseDictDao;
import cn.kfqjtdqb.core.dao.CustomerDao;
import cn.kfqjtdqb.core.service.CustomerService;
/**
 * 客户管理
 * @author lx
 *
 */

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	// 定义dao属性
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private BaseDictDao baseDictDao;

	
	public Page<Customer> findCustomerList(Integer page, Integer rows, 
			String custName,  String custSource,String custIndustry,String custLevel) {
		Customer customer = new Customer();
		//判断客户名称(公司名称)
		if(StringUtils.isNotBlank(custName)){
			customer.setCust_name(custName);
		}
		//判断客户信息来源
		if(StringUtils.isNotBlank(custSource)){
			customer.setCust_source(custSource);
		}
		//判断客户所属行业
		if(StringUtils.isNotBlank(custIndustry)){
			customer.setCust_industry(custIndustry);
		}
		//判断客户级别
		if(StringUtils.isNotBlank(custLevel)){
			customer.setCust_level(custLevel);
		}
		//当前页
		customer.setStart((page-1) * rows) ;
		//每页数
		customer.setRows(rows);
		//查询客户列表
		List<Customer> customers = customerDao.selectCustomerList(customer);
		//查询客户列表总记录数
		Integer count = customerDao.selectCustomerListCount(customer);
		//创建Page返回对象
		Page<Customer> result = new Page<>();
		result.setPage(page);
		result.setRows(customers);
		result.setSize(rows);
		result.setTotal(count);
		return result;

	}


	@Override
	public Customer getCustomerById(Long id) {
		
		Customer customer = customerDao.getCustomerById(id);
		return customer;
		
	}


	@Override
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
		
	}


	@Override
	public void deleteCustomer(Long id) {
		customerDao.deleteCustomer(id);
		
	}

	/**
	 * 创建客户
	 * @param customer
	 * @return
	 */
	@Override
	public Integer createCustomer(Customer customer) {
		return customerDao.createCustomer(customer);
	}



}
