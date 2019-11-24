package cn.kfqjtdqb.core.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssertWaterRent  implements Serializable {
    private Long id;
    @NotBlank
    private String property_leasing_num;
    @NotBlank
    private String assert_num;
    @NotNull
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date rent_start_time;
    @NotNull
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date rent_end_time;
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date deadline;
    @NotNull
    private String watermeter_num;
    @Min(0)
    @NotNull
    private BigDecimal water_num;  //本次收取费用的水表对应的度数
    @Min(0)
    @NotNull
    private BigDecimal reality_water_rent;   //本次收取的金额
    @Min(0)
    @NotNull
    private BigDecimal water_rent;   //截止上次抄表应收的金额
    @Min(0)
    private BigDecimal water_rent_recivied;   // 已经收了的水费
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


    public BigDecimal getWater_num() {
        return water_num;
    }

    public void setWater_num(BigDecimal water_num) {
        this.water_num = water_num;
    }

    public BigDecimal getReality_water_rent() {
        return reality_water_rent;
    }

    public void setReality_water_rent(BigDecimal reality_water_rent) {
        this.reality_water_rent = reality_water_rent;
    }

    public BigDecimal getWater_rent() {
        return water_rent;
    }

    public void setWater_rent(BigDecimal water_rent) {
        this.water_rent = water_rent;
    }

    public BigDecimal getWater_rent_recivied() {
        return water_rent_recivied;
    }

    public void setWater_rent_recivied(BigDecimal water_rent_recivied) {
        this.water_rent_recivied = water_rent_recivied;
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

    public String getWatermeter_num() {
        return watermeter_num;
    }

    public void setWatermeter_num(String watermeter_num) {
        this.watermeter_num = watermeter_num;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
