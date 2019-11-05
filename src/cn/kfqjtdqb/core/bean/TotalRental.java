package cn.kfqjtdqb.core.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TotalRental implements Serializable {

    private Long id;
   @NotNull
    private String property_leasing_num;
    @NotNull
    @Min(0)
    private BigDecimal reality_rental;   //当前已收租
    @NotNull
    @Min(0)
    private BigDecimal rental;   //本次应收的租金
    @NotNull
    private  String  year_months;    //对应的哪一个月
    @NotNull
    private String community_name;  //小区名称
    @NotNull
    private String tenant;
    @NotNull
    private String building_num;
    @NotNull
    private String rentalLocation;
    private BigDecimal  difference;
    private BigDecimal   collectionRate;

    private Integer start;
    private Integer rows;

    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date deadline;  //本次交租金的时间

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



    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }


    public String getYear_months() {
        return year_months;
    }

    public void setYear_months(String year_months) {
        this.year_months = year_months;
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


    public String getCommunity_name() {
        return community_name;
    }

    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getBuilding_num() {
        return building_num;
    }

    public void setBuilding_num(String building_num) {
        this.building_num = building_num;
    }

    public String getRentalLocation() {
        return rentalLocation;
    }

    public void setRentalLocation(String rentalLocation) {
        this.rentalLocation = rentalLocation;
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

    public BigDecimal getDifference() {
        return difference;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public BigDecimal getCollectionRate() {
        return collectionRate;
    }

    public void setCollectionRate(BigDecimal collectionRate) {
        this.collectionRate = collectionRate;
    }
}
