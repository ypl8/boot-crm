package cn.kfqjtdqb.core.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssertWater  implements Serializable {
    private Long id;
    @NotNull
    private String assert_num;
    @NotNull
    private String property_leasing_num;
    @NotNull
    @Min(0)
    private BigDecimal water_num;
    @NotNull
    private String watermeter_num;
    @NotNull
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date deadline;
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


    public BigDecimal getWater_num() {
        return water_num;
    }

    public void setWater_num(BigDecimal water_num) {
        this.water_num = water_num;
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
