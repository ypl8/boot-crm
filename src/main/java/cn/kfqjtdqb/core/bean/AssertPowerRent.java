package cn.kfqjtdqb.core.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssertPowerRent implements Serializable {
    private Long id;
    @NotNull
    private String property_leasing_num;
    @NotNull
    private String assert_num;
    @DateTimeFormat(pattern="yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date rent_start_time;
    @DateTimeFormat(pattern="yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date rent_end_time;
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date deadline;
    @NotNull
    private String powermeter_num;
    @NotNull
    @Min(0)
    private BigDecimal power_num;  //本次收取费用的水表对应的度数
    @NotNull
    @Min(0)
    private BigDecimal reality_power_rent;   //本次收取的金额
    @NotNull
    @Min(0)
    private BigDecimal power_rent;   //截止上次抄表应收的金额
    @Min(0)
    private BigDecimal power_rent_recivied;   // 已经收了的水费
    private Integer start;
    private Integer rows;
    private String state;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAssert_num() {
        return assert_num;
    }

    public void setAssert_num(String assert_num) {
        this.assert_num = assert_num;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getPowermeter_num() {
        return powermeter_num;
    }

    public void setPowermeter_num(String powermeter_num) {
        this.powermeter_num = powermeter_num;
    }


    public BigDecimal getPower_num() {
        return power_num;
    }

    public void setPower_num(BigDecimal power_num) {
        this.power_num = power_num;
    }

    public BigDecimal getReality_power_rent() {
        return reality_power_rent;
    }

    public void setReality_power_rent(BigDecimal reality_power_rent) {
        this.reality_power_rent = reality_power_rent;
    }

    public BigDecimal getPower_rent() {
        return power_rent;
    }

    public void setPower_rent(BigDecimal power_rent) {
        this.power_rent = power_rent;
    }

    public BigDecimal getPower_rent_recivied() {
        return power_rent_recivied;
    }

    public void setPower_rent_recivied(BigDecimal power_rent_recivied) {
        this.power_rent_recivied = power_rent_recivied;
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

    public void setState(String state) {
        this.state = state;
    }
}
