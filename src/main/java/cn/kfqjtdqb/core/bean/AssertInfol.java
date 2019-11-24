package cn.kfqjtdqb.core.bean;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 资产信息表
 */
public class AssertInfol implements Serializable {

    private Long id;
    @NotNull
    @Size(min = 0, max = 64)
    private String assert_num;
    @NotBlank
    private String card_num;
    @NotBlank
    private String community_name;
    @NotBlank
    private String building_num;
    @NotBlank
    private String room_number;
    @NotNull
    @Min(0)
    private Double floorage;
    @NotBlank
    private String floor_state;
    @NotBlank
    private String watermeter_num;
    @NotBlank
    private String electricmeter_num;
    @NotBlank
    private  String  assertType;
    private String  property_leasing_num;
    private String  status;
    private String remark;
    private Integer start;
    private Integer rows;
    private List<PropertyLeasing> propertyLeasings;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProperty_leasing_num() {
        return property_leasing_num;
    }

    public void setProperty_leasing_num(String property_leasing_num) {
        this.property_leasing_num = property_leasing_num;
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

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getCommunity_name() {
        return community_name;
    }

    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }

    public String getBuilding_num() {
        return building_num;
    }

    public void setBuilding_num(String building_num) {
        this.building_num = building_num;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public Double getFloorage() {
        return floorage;
    }

    public void setFloorage(Double floorage) {
        this.floorage = floorage;
    }

    public String getFloor_state() {
        return floor_state;
    }

    public void setFloor_state(String floor_state) {
        this.floor_state = floor_state;
    }

    public String getWatermeter_num() {
        return watermeter_num;
    }

    public void setWatermeter_num(String watermeter_num) {
        this.watermeter_num = watermeter_num;
    }

    public String getElectricmeter_num() {
        return electricmeter_num;
    }

    public void setElectricmeter_num(String electricmeter_num) {
        this.electricmeter_num = electricmeter_num;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PropertyLeasing> getPropertyLeasings() {
        return propertyLeasings;
    }

    public void setPropertyLeasings(List<PropertyLeasing> propertyLeasings) {
        this.propertyLeasings = propertyLeasings;
    }


    public String getAssertType() {
        return assertType;
    }

    public void setAssertType(String assertType) {
        this.assertType = assertType;
    }

    @Override
    public String toString() {
        return "AssertInfol{" +
                "id=" + id +
                ", assert_num='" + assert_num + '\'' +
                ", card_num='" + card_num + '\'' +
                ", community_name='" + community_name + '\'' +
                ", building_num='" + building_num + '\'' +
                ", room_number='" + room_number + '\'' +
                ", floorage=" + floorage +
                ", floor_state='" + floor_state + '\'' +
                ", watermeter_num='" + watermeter_num + '\'' +
                ", electricmeter_num='" + electricmeter_num + '\'' +
                ", start=" + start +
                ", rows=" + rows +
                ", propertyLeasings=" + propertyLeasings +
                '}';
    }
}

