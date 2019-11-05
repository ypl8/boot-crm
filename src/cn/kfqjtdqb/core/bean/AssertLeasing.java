package cn.kfqjtdqb.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class AssertLeasing  implements Serializable {

    private Long id;
    private String assert_num;
    private String property_leasing_num;
    private BigDecimal water_num;
    private BigDecimal  electric_num;




    public BigDecimal getWater_num() {
        return water_num;
    }

    public void setWater_num(BigDecimal water_num) {
        this.water_num = water_num;
    }

    public BigDecimal getElectric_num() {
        return electric_num;
    }

    public void setElectric_num(BigDecimal electric_num) {
        this.electric_num = electric_num;
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
}
