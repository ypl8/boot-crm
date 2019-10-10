package cn.kfqjtdqb.core.bean;


import java.io.Serializable;

/**
 * 资产信息表
 */
public class AssertInfol  implements Serializable {

    private Long id;
    private String assert_num;
    private String card_num;
    private String community_name;
    private String building_num;
    private String room_number;
    private Double floorage;
    private char floor_state;
    private String watermeter_num;
    private String electricmeter_num;
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

    public char getFloor_state() {
        return floor_state;
    }

    public void setFloor_state(char floor_state) {
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
}

