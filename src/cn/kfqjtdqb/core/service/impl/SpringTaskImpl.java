package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.core.bean.AssertDeposit;
import cn.kfqjtdqb.core.bean.PropertyLeasing;
import cn.kfqjtdqb.core.dao.AssertDepositDao;
import cn.kfqjtdqb.core.dao.AssertEstateDao;
import cn.kfqjtdqb.core.dao.AssertRentalDao;
import cn.kfqjtdqb.core.dao.PropertyLeasingDao;
import cn.kfqjtdqb.core.utils.DateUtils;
import cn.kfqjtdqb.core.utils.RentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class SpringTaskImpl {

    @Autowired
    private PropertyLeasingDao propertyLeasingDao;

    @Autowired
    private AssertEstateDao assertEstateDao;
    @Autowired
    private AssertDepositDao assertDepositDao;

    @Autowired
    private AssertRentalDao assertRentalDao;

    @Scheduled(cron = "0 0/1 0/1 1/1 1/1 ? ")
    public void task() {
        propertyLeasingDao.updateState();
    }


    @Scheduled(cron = "0 0 1 1 * ?")
    public void task2() {
        updateState();
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
