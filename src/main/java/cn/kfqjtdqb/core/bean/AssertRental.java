package cn.kfqjtdqb.core.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssertRental implements Serializable {
    private Long id;
    @NotNull
    private String property_leasing_num;
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date rent_start_time;
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date rent_end_time;
    @NotNull
    @Min(0)
    private BigDecimal reality_rental;   //当前已收租金
    @NotNull
    @Min(0)
    private BigDecimal rental;   //本次收的租金
    @NotNull
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date deadline;
    @Min(0)
    private BigDecimal rent_recivied;   // 截止本月应收金额
    private String  year_months;
    private Integer start;
    private Integer rows;
    private String state;


    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

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




    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public BigDecimal getReality_rental() {
        return reality_rental;
    }

    public void setReality_rental(BigDecimal reality_rental) {
        this.reality_rental = reality_rental;
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

    public String getYear_months() {
        return year_months;
    }

    public void setYear_months(String year_months) {
        this.year_months = year_months;
    }
}
