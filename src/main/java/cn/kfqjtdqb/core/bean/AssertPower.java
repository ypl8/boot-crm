package cn.kfqjtdqb.core.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssertPower implements Serializable {
    private Long id;
    @NotNull
    private String assert_num;
    @NotNull
    private String property_leasing_num;
    @Min(0)
    @NotNull
    private BigDecimal power_num;
    @NotNull
    private String powermeter_num;

    private String status;


    @NotNull
    @DateTimeFormat(pattern="yyyyMMdd")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date deadline;
    private Integer start;
    private Integer rows;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAssert_num() {
        return assert_num;
    }

    public void setAssert_num(String assert_num) {
        this.assert_num = assert_num;
    }

    public String getProperty_leasing_num() {
        return property_leasing_num;
    }

    public void setProperty_leasing_num(String property_leasing_num) {
        this.property_leasing_num = property_leasing_num;
    }


    public BigDecimal getPower_num() {
        return power_num;
    }

    public void setPower_num(BigDecimal power_num) {
        this.power_num = power_num;
    }

    public String getPowermeter_num() {
        return powermeter_num;
    }

    public void setPowermeter_num(String powermeter_num) {
        this.powermeter_num = powermeter_num;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
