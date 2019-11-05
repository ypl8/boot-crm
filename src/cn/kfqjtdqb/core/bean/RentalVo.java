package cn.kfqjtdqb.core.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RentalVo implements Serializable {
    private BigDecimal rental;
    private BigDecimal rent_recivied;
    private BigDecimal reality_rental;   //当前已收租金
    private String property_leasing_num;
    private List<TotalRental>  totalRentals;

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


    public BigDecimal getRental() {
        return rental;
    }

    public void setRental(BigDecimal rental) {
        this.rental = rental;
    }

    public BigDecimal getRent_recivied() {
        return rent_recivied;
    }

    public void setRent_recivied(BigDecimal rent_recivied) {
        this.rent_recivied = rent_recivied;
    }

    public void setReality_rental(BigDecimal reality_rental) {
        this.reality_rental = reality_rental;
    }

    public String getProperty_leasing_num() {
        return property_leasing_num;
    }

    public void setProperty_leasing_num(String property_leasing_num) {
        this.property_leasing_num = property_leasing_num;
    }

    public List<TotalRental> getTotalRentals() {
        return totalRentals;
    }

    public void setTotalRentals(List<TotalRental> totalRentals) {
        this.totalRentals = totalRentals;
    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


    public BigDecimal getReality_rental() {
        return reality_rental;
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
}
