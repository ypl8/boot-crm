package cn.kfqjtdqb.core.bean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 资产合同信息表
 */
public class PropertyLeasing   implements Serializable {

    private Long id;
    private String property_leasing_num;
    private String tenant;
    private Double rental_area;
    @JsonFormat(pattern = "yyyyMMdd", timezone="GMT+8")
    private Date sign_in_time;
    private Double rent_charge_standard;
    private Double monthly_rental;
    private Double rent_free_period;
    private Double rent_period;
    private char collect_rent_way;
    private String  collect_rent_time;
    private Double estate_charge_standard;
    private Double estate_charge_month;
    private Double deposit;
    @JsonFormat(pattern = "yyyyMMdd", timezone="GMT+8")
    private Date deposit_time;
    private Double water_rate;
    private Double power_rate;
    private  char collect_rate_way;
    @JsonFormat(pattern = "yyyyMMdd", timezone="GMT+8")
    private  Date rent_start_time;
    @JsonFormat(pattern = "yyyyMMdd", timezone="GMT+8")
    private  Date rent_end_time;
    private  char property_leasing_state; // 合同的状态
    private  String  remark;
    private Integer start;
    private Integer rows;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
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

    public Double getRental_area() {
        return rental_area;
    }

    public void setRental_area(Double rental_area) {
        this.rental_area = rental_area;
    }

    public Date getSign_in_time() {
        return sign_in_time;
    }

    public void setSign_in_time(Date sign_in_time) {
        this.sign_in_time = sign_in_time;
    }

    public Double getRent_charge_standard() {
        return rent_charge_standard;
    }

    public void setRent_charge_standard(Double rent_charge_standard) {
        this.rent_charge_standard = rent_charge_standard;
    }

    public Double getMonthly_rental() {
        return monthly_rental;
    }

    public void setMonthly_rental(Double monthly_rental) {
        this.monthly_rental = monthly_rental;
    }

    public Double getRent_free_period() {
        return rent_free_period;
    }

    public void setRent_free_period(Double rent_free_period) {
        this.rent_free_period = rent_free_period;
    }

    public Double getRent_period() {
        return rent_period;
    }

    public void setRent_period(Double rent_period) {
        this.rent_period = rent_period;
    }

    public char getCollect_rent_way() {
        return collect_rent_way;
    }

    public void setCollect_rent_way(char collect_rent_way) {
        this.collect_rent_way = collect_rent_way;
    }

    public Double getEstate_charge_standard() {
        return estate_charge_standard;
    }

    public void setEstate_charge_standard(Double estate_charge_standard) {
        this.estate_charge_standard = estate_charge_standard;
    }

    public Double getEstate_charge_month() {
        return estate_charge_month;
    }

    public void setEstate_charge_month(Double estate_charge_month) {
        this.estate_charge_month = estate_charge_month;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Date getDeposit_time() {
        return deposit_time;
    }

    public void setDeposit_time(Date deposit_time) {
        this.deposit_time = deposit_time;
    }

    public Double getWater_rate() {
        return water_rate;
    }

    public void setWater_rate(Double water_rate) {
        this.water_rate = water_rate;
    }

    public Double getPower_rate() {
        return power_rate;
    }

    public void setPower_rate(Double power_rate) {
        this.power_rate = power_rate;
    }

    public char getCollect_rate_way() {
        return collect_rate_way;
    }

    public void setCollect_rate_way(char collect_rate_way) {
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

    public char getProperty_leasing_state() {
        return property_leasing_state;
    }

    public void setProperty_leasing_state(char property_leasing_state) {
        this.property_leasing_state = property_leasing_state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getCollect_rent_time() {
        return collect_rent_time;
    }

    public void setCollect_rent_time(String collect_rent_time) {
        this.collect_rent_time = collect_rent_time;
    }
}
