package cn.kfqjtdqb.core.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EstateVo implements Serializable {
    private BigDecimal estate;
    private BigDecimal estate_recivied;
    private BigDecimal reality_estate;   //当前已收租金
    private String property_leasing_num;
    private List<TotalEstate>  totalEstates;
    @DateTimeFormat(pattern="yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone="GMT+8")
    private Date rent_start_time;
    @DateTimeFormat(pattern="yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone="GMT+8")
    private  Date rent_end_time;
    private Long id;
    private String  day;
    private  String  state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    public String getProperty_leasing_num() {
        return property_leasing_num;
    }

    public void setProperty_leasing_num(String property_leasing_num) {
        this.property_leasing_num = property_leasing_num;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public BigDecimal getEstate() {
        return estate;
    }

    public void setEstate(BigDecimal estate) {
        this.estate = estate;
    }

    public BigDecimal getEstate_recivied() {
        return estate_recivied;
    }

    public void setEstate_recivied(BigDecimal estate_recivied) {
        this.estate_recivied = estate_recivied;
    }

    public BigDecimal getReality_estate() {
        return reality_estate;
    }

    public void setReality_estate(BigDecimal reality_estate) {
        this.reality_estate = reality_estate;
    }

    public List<TotalEstate> getTotalEstates() {
        return totalEstates;
    }

    public void setTotalEstates(List<TotalEstate> totalEstates) {
        this.totalEstates = totalEstates;
    }
}
