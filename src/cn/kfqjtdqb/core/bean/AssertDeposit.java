package cn.kfqjtdqb.core.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssertDeposit implements Serializable {
    private Long id;
    @NotNull

    private String property_leasing_num;
    @NotNull
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date rent_start_time;
    @NotNull
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date rent_end_time;
    @Min(0)
    @NotNull
    private BigDecimal reality_deposit;
    @Min(0)
    @NotNull
    private BigDecimal deposit;

    @NotNull
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date deadline;
    private Integer start;
    private Integer rows;
    private String state;


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


    public BigDecimal getReality_deposit() {
        return reality_deposit;
    }

    public void setReality_deposit(BigDecimal reality_deposit) {
        this.reality_deposit = reality_deposit;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "AssertDeposit{" +
                "id=" + id +
                ", property_leasing_num='" + property_leasing_num + '\'' +
                ", rent_start_time=" + rent_start_time +
                ", rent_end_time=" + rent_end_time +
                ", reality_deposit=" + reality_deposit +
                ", deposit=" + deposit +
                ", start=" + start +
                ", rows=" + rows +
                ", state='" + state + '\'' +
                '}';
    }
}
