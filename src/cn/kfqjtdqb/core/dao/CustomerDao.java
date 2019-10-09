package cn.kfqjtdqb.core.dao;

import java.util.List;

import cn.kfqjtdqb.core.bean.Customer;

public interface CustomerDao {

	List<Customer> selectCustomerList(Customer customer);
	Integer selectCustomerListCount(Customer customer);
	Customer getCustomerById(Long id);
	void updateCustomer(Customer customer);
	void deleteCustomer(Long id);
	//创建客户
	Integer createCustomer(Customer customer);

	
}