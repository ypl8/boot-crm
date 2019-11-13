package cn.kfqjtdqb.core.bean;

import java.io.Serializable;
import java.util.Date;

public class Case implements Serializable {
    private Long case_id;
    private String case_no;
    private String case_judgepeople;
    private String case_partypeople;
    private String case_state;
    private Date case_startime;
    private Date case_createtime;
    private Date case_endtime;
    private Integer start;
    private Integer rows;

    public Long getCase_id() {
        return case_id;
    }

    public void setCase_id(Long case_id) {
        this.case_id = case_id;
    }

    public String getCase_no() {
        return case_no;
    }

    public void setCase_no(String case_no) {
        this.case_no = case_no;
    }

    public String getCase_judgepeople() {
        return case_judgepeople;
    }

    public void setCase_judgepeople(String case_judgepeople) {
        this.case_judgepeople = case_judgepeople;
    }

    public String getCase_partypeople() {
        return case_partypeople;
    }

    public void setCase_partypeople(String case_partypeople) {
        this.case_partypeople = case_partypeople;
    }

    public String getCase_state() {
        return case_state;
    }

    public void setCase_state(String case_state) {
        this.case_state = case_state;
    }

    public Date getCase_startime() {
        return case_startime;
    }

    public void setCase_startime(Date case_startime) {
        this.case_startime = case_startime;
    }

    public Date getCase_createtime() {
        return case_createtime;
    }

    public void setCase_createtime(Date case_createtime) {
        this.case_createtime = case_createtime;
    }

    public Date getCase_endtime() {
        return case_endtime;
    }

    public void setCase_endtime(Date case_endtime) {
        this.case_endtime = case_endtime;
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
}
