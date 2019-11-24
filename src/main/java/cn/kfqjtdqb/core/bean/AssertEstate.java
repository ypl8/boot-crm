package cn.kfqjtdqb.core.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssertEstate  implements Serializable {
    private Long id;
    @NotBlank
    private String property_leasing_num;
    @NotNull
    @DateTimeFormat(pattern="yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date rent_start_time;
    @NotNull
    @DateTimeFormat(pattern="yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date rent_end_time;
    @NotNull
    @Min(0)
    private BigDecimal reality_estate;   //当前已收租金
    @NotNull
    @Min(0)
    private BigDecimal estate;   //本次收的租金
    @Min(0)
    private BigDecimal estate_recivied;   // 截止本月应收金额
    private Integer start;
    private Integer rows;
    private String state;
    private String  year_months;

    private  String  status;

    @NotNull
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date deadline;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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


    public BigDecimal getReality_estate() {
        return reality_estate;
    }

    public void setReality_estate(BigDecimal reality_estate) {
        this.reality_estate = reality_estate;
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

    public String getState() {
        return state;
    }


    public String getYear_months() {
        return year_months;
    }

    public void setYear_months(String year_months) {
        this.year_months = year_months;
    }

    public void setState(String state) {
        this.state = state;
    }
}
