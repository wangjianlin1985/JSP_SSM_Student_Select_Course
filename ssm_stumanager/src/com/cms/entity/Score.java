// 
// 
// 

package com.cms.entity;

public class Score
{
    private Integer id;
    private Integer score;
    private String result;
    private Integer cId;
    private String sId;
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public Integer getScore() {
        return this.score;
    }
    
    public void setScore(final Integer score) {
        this.score = score;
    }
    
    public String getResult() {
        return this.result;
    }
    
    public void setResult(final String result) {
        this.result = ((result == null) ? null : result.trim());
    }
    
    public Integer getcId() {
        return this.cId;
    }
    
    public void setcId(final Integer cId) {
        this.cId = cId;
    }
    
    public String getsId() {
        return this.sId;
    }
    
    public void setsId(final String sId) {
        this.sId = ((sId == null) ? null : sId.trim());
    }
    
    @Override
    public String toString() {
        return "Score [id=" + this.id + ", score=" + this.score + ", result=" + this.result + ", cId=" + this.cId + ", sId=" + this.sId + "]";
    }
}
