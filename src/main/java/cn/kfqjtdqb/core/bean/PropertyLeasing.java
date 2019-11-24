package cn.kfqjtdqb.core.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 资产合同信息表
 */
public class PropertyLeasing implements Serializable {

    private Long id;
    @NotBlank
    private String property_leasing_num;
    @NotBlank
    private String tenant;

    @NotBlank
    private String building_num;
    @NotBlank
    private String rentalLocation;
    @NotBlank
    private String community_name;
    @NotNull
    @Min(0)
    private BigDecimal rental_area;
    @NotNull
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date sign_in_time;
    @NotBlank
    private String rent_charge_standard;
    @NotBlank
    private String monthly_rental;
    @NotBlank
    private Integer rent_free_period;
    @NotBlank
    private Integer rent_period;
    @NotBlank
    private String collect_rent_way;
    @NotBlank
    private String collect_rent_time;
    @NotNull
    @Min(0)
    private BigDecimal estate_charge_standard;
    @NotNull
    @Min(0)
    private BigDecimal estate_charge_month;
    @NotNull
    @Min(0)
    private BigDecimal deposit;

    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date deposit_time;
    @NotNull
    @Min(0)
    private BigDecimal water_rate;
    @NotNull
    @Min(0)
    private BigDecimal power_rate;
    @NotNull
    private String collect_rate_way;
    @NotNull
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date rent_start_time;
    @NotNull
    @DateTimeFormat(pattern = "yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date rent_end_time;
    @NotBlank
    private String property_leasing_state; // 合同的状态

    @NotBlank
    private  String  idCard;
    @NotBlank
    private  String  phoneNumber;

    private String remark;
    private String property_leasing_type;
    private String depositState;
    private String rentalState;
    private String estateState;
    private String waterState;
    private String powerState;



    private Double total_rent;  //总共租金
    private List<AssertInfol> assertInfols;  //对应的资源列表
    private Integer start;
    private Integer rows;
    private String assert_num;
    private  String  status;

    public String getAssert_num() {
        return assert_num;
    }

    public void setAssert_num(String assert_num) {
        this.assert_num = assert_num;
    }

    public String getBuilding_num() {
        return building_num;
    }

    public void setBuilding_num(String building_num) {
        this.building_num = building_num;
    }

    public List<AssertInfol> getAssertInfols() {
        return assertInfols;
    }

    public void setAssertInfols(List<AssertInfol> assertInfols) {
        this.assertInfols = assertInfols;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getProperty_leasing_type() {
        return property_leasing_type;
    }

    public void setProperty_leasing_type(String property_leasing_type) {
        this.property_leasing_type = property_leasing_type;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProperty_leasing_num() {
        return property_leasing_num;
    }

    public void setProperty_leasing_num(String property_leasing_num) {
        this.property_leasing_num = property_leasing_num;
    }



    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }



    public Date getSign_in_time() {
        return sign_in_time;
    }

    public void setSign_in_time(Date sign_in_time) {
        this.sign_in_time = sign_in_time;
    }


    public String getRent_charge_standard() {
        return rent_charge_standard;
    }

    public void setRent_charge_standard(String rent_charge_standard) {
        this.rent_charge_standard = rent_charge_standard;
    }

    public String getMonthly_rental() {
        return monthly_rental;
    }

    public void setMonthly_rental(String monthly_rental) {
        this.monthly_rental = monthly_rental;
    }

    public Integer getRent_free_period() {
        return rent_free_period;
    }

    public void setRent_free_period(Integer rent_free_period) {
        this.rent_free_period = rent_free_period;
    }

    public Integer getRent_period() {
        return rent_period;
    }

    public void setRent_period(Integer rent_period) {
        this.rent_period = rent_period;
    }

    public String getCollect_rent_way() {
        return collect_rent_way;
    }

    public void setCollect_rent_way(String collect_rent_way) {
        this.collect_rent_way = collect_rent_way;
    }



    public Date getDeposit_time() {
        return deposit_time;
    }

    public void setDeposit_time(Date deposit_time) {
        this.deposit_time = deposit_time;
    }



    public String getCollect_rate_way() {
        return collect_rate_way;
    }

    public void setCollect_rate_way(String collect_rate_way) {
        this.collect_rate_way = collect_rate_way;
    }

    public Date getRent_start_time() {
        return rent_start_time;
    }

    public void setRent_start_time(Date rent_start_time) {
        this.rent_start_time = rent_start_time;
    }

    public Date getRent_end_time() {
        return rent_end_time;
    }

    public void setRent_end_time(Date rent_end_time) {
        this.rent_end_time = rent_end_time;
    }

    public String getProperty_leasing_state() {
        return property_leasing_state;
    }

    public void setProperty_leasing_state(String property_leasing_state) {
        this.property_leasing_state = property_leasing_state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getCommunity_name() {
        return community_name;
    }

    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }

    public String getRentalLocation() {
        return rentalLocation;
    }

    public void setRentalLocation(String rentalLocation) {
        this.rentalLocation = rentalLocation;
    }

    public String getCollect_rent_time() {
        return collect_rent_time;
    }

    public void setCollect_rent_time(String collect_rent_time) {
        this.collect_rent_time = collect_rent_time;
    }

    public Double getTotal_rent() {
        return total_rent;
    }

    public void setTotal_rent(Double total_rent) {
        this.total_rent = total_rent;
    }


    public String getDepositState() {
        return depositState;
    }

    public void setDepositState(String depositState) {
        this.depositState = depositState;
    }

    public String getRentalState() {
        return rentalState;
    }

    public void setRentalState(String rentalState) {
        this.rentalState = rentalState;
    }

    public String getEstateState() {
        return estateState;
    }

    public void setEstateState(String estateState) {
        this.estateState = estateState;
    }

    public String getWaterState() {
        return waterState;
    }

    public void setWaterState(String waterState) {
        this.waterState = waterState;
    }

    public String getPowerState() {
        return powerState;
    }

    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }

    public BigDecimal getRental_area() {
        return rental_area;
    }

    public void setRental_area(BigDecimal rental_area) {
        this.rental_area = rental_area;
    }

    public BigDecimal getEstate_charge_standard() {
        return estate_charge_standard;
    }

    public void setEstate_charge_standard(BigDecimal estate_charge_standard) {
        this.estate_charge_standard = estate_charge_standard;
    }

    public BigDecimal getEstate_charge_month() {
        return estate_charge_month;
    }

    public void setEstate_charge_month(BigDecimal estate_charge_month) {
        this.estate_charge_month = estate_charge_month;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getWater_rate() {
        return water_rate;
    }

    public void setWater_rate(BigDecimal water_rate) {
        this.water_rate = water_rate;
    }

    public BigDecimal getPower_rate() {
        return power_rate;
    }

    public void setPower_rate(BigDecimal power_rate) {
        this.power_rate = power_rate;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PropertyLeasing{" +
                "id=" + id +
                ", property_leasing_num='" + property_leasing_num + '\'' +
                ", tenant='" + tenant + '\'' +
                ", rental_area=" + rental_area +
                ", sign_in_time=" + sign_in_time +
                ", rent_charge_standard=" + rent_charge_standard +
                ", monthly_rental=" + monthly_rental +
                ", rent_free_period=" + rent_free_period +
                ", rent_period=" + rent_period +
                ", collect_rent_way=" + collect_rent_way +
                ", collect_rent_time='" + collect_rent_time + '\'' +
                ", estate_charge_standard=" + estate_charge_standard +
                ", estate_charge_month=" + estate_charge_month +
                ", deposit=" + deposit +
                ", deposit_time=" + deposit_time +
                ", water_rate=" + water_rate +
                ", power_rate=" + power_rate +
                ", collect_rate_way=" + collect_rate_way +
                ", rent_start_time=" + rent_start_time +
                ", rent_end_time=" + rent_end_time +
                ", property_leasing_state=" + property_leasing_state +
                ", remark='" + remark + '\'' +
                ", assertInfols=" + assertInfols +
                ", start=" + start +
                ", rows=" + rows +
                '}';
    }
}
