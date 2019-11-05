package cn.kfqjtdqb.core.service.impl;
 
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.core.bean.AssertDeposit;
import cn.kfqjtdqb.core.bean.AssertEstate;
import cn.kfqjtdqb.core.bean.PropertyLeasing;
import cn.kfqjtdqb.core.dao.AssertDepositDao;
import cn.kfqjtdqb.core.dao.AssertEstateDao;
import cn.kfqjtdqb.core.dao.AssertRentalDao;
import cn.kfqjtdqb.core.dao.PropertyLeasingDao;
import cn.kfqjtdqb.core.utils.DateUtils;
import cn.kfqjtdqb.core.utils.RentUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Component
public class SendWsListener implements ServletContextListener {

	private final static Logger logger = Logger.getLogger(SendWsListener.class);

	@Autowired
	private PropertyLeasingDao propertyLeasingDao;

	@Autowired
	private AssertEstateDao assertEstateDao;
	@Autowired
	private AssertDepositDao assertDepositDao;

	@Autowired
	private AssertRentalDao assertRentalDao;

	private WebApplicationContext webApplicationContext;

	public void setPropertyLeasingDao(PropertyLeasingDao propertyLeasingDao) {
		this.propertyLeasingDao = propertyLeasingDao;
	}

	public void setAssertEstateDao(AssertEstateDao assertEstateDao) {
		this.assertEstateDao = assertEstateDao;
	}

	public void setAssertRentalDao(AssertRentalDao assertRentalDao) {
		this.assertRentalDao = assertRentalDao;
	}

	public void setAssertDepositDao(AssertDepositDao assertDepositDao) {
		this.assertDepositDao = assertDepositDao;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.debug("定时更新租金和物业费的状态");
	}
 
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		if (null == webApplicationContext){
			webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
			if(null != webApplicationContext){
				propertyLeasingDao = (PropertyLeasingDao)webApplicationContext.getBean("propertyLeasingDao");
				assertEstateDao= (AssertEstateDao)webApplicationContext.getBean("assertEstateDao");
				assertRentalDao=(AssertRentalDao)webApplicationContext.getBean("assertRentalDao");
				assertDepositDao=(AssertDepositDao)webApplicationContext.getBean("assertDepositDao");
			}
		}
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		/**
		 *  定制每天的8:00:00执行，若程序已超过8点启动,当天不再执行，等到明日八点再执行
		 *  这样保证了时间一直是8点，而不会变成程序启动时间
		 */
		calendar.set(year, month, day, 10, 17, 00);
		Date defaultdate = calendar.getTime();// 今天8点（默认发送时间）
		Date sendDate = new Date();
		// 8点后开机
		if (defaultdate.before(new Date())) {
			// 将发送时间设为明天8点
			calendar.add(Calendar.DATE, 1);
			defaultdate = calendar.getTime();
		}
		
/*		*//**
		 * ----------------每刻任务 ----------------
		 * 启动服务器后，若此时时间没过8点，等待。到了8点自动执行一次，15分钟后再执行一次，周而复始
		 * 启动服务器后，若此时时间超过8点，会立刻执行一次，等到15分钟后再次执行一次，周而复始
		 * 到了第二天，不会再判断是否是8点，这个开始时间，只会判断一次而已
		 *//*
		Timer qTimer = new Timer();
		qTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				logger.info("每刻任务已执行");

			}
		}, defaultdate, 15 * 60 * 1000);// 定时每15分钟
		logger.debug("每刻定时发送Xml信息监听--已启动！");*/

		/**
		 * ----------------每日任务 ----------------
		 * 启动服务器后，若此时时间没过8点，等待。到了8点自动执行一次，24小时后（第二天8点）再执行一次，周而复始
		 * 启动服务器后，若此时时间已经超过8点，会立刻执行一次，等到24小时后（第二天8点）再次执行一次，周而复始
		 *//*
		Timer dTimer = new Timer();
		dTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				logger.info("每日任务已经执行");
				TemsWsClient client = new TemsWsClient();
				client.sendXmlData(client.getBeanIdsDay());
			}
		}, sendDate, 24 * 60 * 60 * 1000);// 定时24小时：24 * 60 * 60 * 1000
		logger.debug("每日定时发送Xml信息监听--已启动！");*/
 
		/**
		 * ----------------每月任务 ----------------
		 * 启动服务器后，若此时时间没过8点，等待。到了8点自动执行判断是否是当前月份的1号，若是则执行一次，
		 * 24小时后（第二天8点）再执行一次判断（每月1号以后后的29天或30天后才会是下月1号，再执行一次），周而复始
		 * 启动服务器后，若此时时间已经超过8点，会立刻执行一次，等到下个月1号再次执行一次，周而复始
		 */
		Timer mTimer = new Timer();
		mTimer.schedule(new TimerTask() {
 
			@Override
			public void run() {
				Calendar c = Calendar.getInstance();
				int day = c.get(Calendar.DAY_OF_MONTH);
				logger.info("月任务 判断中");
				if (day == 1) {
					// 每天执行，若为每月1号才执行
					logger.info("月任务执行已执行");
					updateState();
				}

			}
		}, defaultdate, 24 * 60 * 60 * 1000);// 每天执行一次检查
		logger.debug("每月定时更新数据状态--已启动！");
 
	}


	 public  void updateState(){
		 PropertyLeasing propertyLeasing=new PropertyLeasing();
		 List<PropertyLeasing> propertyLeasings = propertyLeasingDao.selectPropertyLeasingList(propertyLeasing);
		 for (int i = 0; i <propertyLeasings.size() ; i++) {
			 propertyLeasing.setProperty_leasing_num(propertyLeasings.get(i).getProperty_leasing_num());


			 //物业费判断
			 BigDecimal estate_recivied = assertEstateDao.getAssertEstateCountByLeasingNum(propertyLeasings.get(i).getProperty_leasing_num());   //表示的是已经收了的物业费

			 if(estate_recivied==null)  estate_recivied=new BigDecimal(0);
			 //截止本月应收物业费
			 BigDecimal estate_should = null;
			 Date startRentDate =propertyLeasings.get(i).getRent_start_time();  //租金开始时间
			 Date currentDate = new Date();  //表示的是当前时间
			 int month = DateUtils.getDifMonth(startRentDate, currentDate) + 1;   //需要交物业费的月份
			 estate_should =new BigDecimal(month).multiply( propertyLeasings.get(i).getEstate_charge_month());
			 if (estate_should .compareTo(estate_recivied)<=0) {
				 propertyLeasing.setEstateState(ConstUtils.ESTATESTATESUCCESS);  //表示的已经缴清
			 } else {
				 propertyLeasing.setEstateState(ConstUtils.ESTATESTATEFAIL);
			 }


			 //租金的判断
			 BigDecimal rent_recivied = assertRentalDao.getAssertRentalCountByLeasingNum(propertyLeasings.get(i).getProperty_leasing_num());
			 if (rent_recivied == null) rent_recivied = BigDecimal.ZERO;
			 //截止本月应收租金
			 BigDecimal rent_should = BigDecimal.ZERO;
			 rent_should = new BigDecimal( RentUtils.getToalRentByCurrentDate(propertyLeasings.get(i), currentDate)+"");
			 if (rent_should .compareTo( rent_recivied)<=0) {
				 propertyLeasing.setRentalState(ConstUtils.RENTALSTATESUCCESS);  //表示的已经缴清
			 } else {
				 propertyLeasing.setRentalState(ConstUtils.RENTALSTATEFAIL);
			 }

			 //押金的判断
			 AssertDeposit assertDeposit= assertDepositDao.getAssertDepositByLeasingNum(propertyLeasings.get(i).getProperty_leasing_num());
			 if(assertDeposit!=null){
				 propertyLeasing.setDepositState(assertDeposit.getState());
			 }else{
				 propertyLeasing.setDepositState(ConstUtils.DEPOSITSTATEFAIL);
			 }
			 propertyLeasingDao.updatePropertyLeasing(propertyLeasing);

		 }
	 }
}