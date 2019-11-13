package cn.kfqjtdqb.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class AssertInfolTotal implements Serializable {

    private String community_name;

    private Integer countSum;

    private Integer countRented;   //表示的是已经出租

    private Integer countUnrent;  //表示的是未出租

    private Integer  countOccupy ;  // 表示的是占用

    private BigDecimal assertRate;  //表示的是出租率

    public String getCommunity_name() {
        return community_name;
    }

    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }

    public Integer getCountSum() {
        return countSum;
    }

    public void setCountSum(Integer countSum) {
        this.countSum = countSum;
    }

    public Integer getCountRented() {
        return countRented;
    }

    public void setCountRented(Integer countRented) {
        this.countRented = countRented;
    }

    public Integer getCountUnrent() {
        return countUnrent;
    }

    public void setCountUnrent(Integer countUnrent) {
        this.countUnrent = countUnrent;
    }

    public Integer getCountOccupy() {
        return countOccupy;
    }

    public void setCountOccupy(Integer countOccupy) {
        this.countOccupy = countOccupy;
    }


    public BigDecimal getAssertRate() {
        return assertRate;
    }

    public void setAssertRate(BigDecimal assertRate) {
        this.assertRate = assertRate;
    }
}
