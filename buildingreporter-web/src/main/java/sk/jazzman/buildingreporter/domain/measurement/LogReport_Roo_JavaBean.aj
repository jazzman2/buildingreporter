// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sk.jazzman.buildingreporter.domain.measurement;

import java.sql.Timestamp;
import sk.jazzman.buildingreporter.domain.measurement.LogReport;

privileged aspect LogReport_Roo_JavaBean {
    
    public Double LogReport.getAverageValue() {
        return this.averageValue;
    }
    
    public void LogReport.setAverageValue(Double averageValue) {
        this.averageValue = averageValue;
    }
    
    public Timestamp LogReport.getLogDate() {
        return this.logDate;
    }
    
    public void LogReport.setLogDate(Timestamp logDate) {
        this.logDate = logDate;
    }
    
    public String LogReport.getUnit() {
        return this.unit;
    }
    
    public void LogReport.setUnit(String unit) {
        this.unit = unit;
    }
    
    public Double LogReport.getDiff() {
        return this.diff;
    }
    
    public void LogReport.setDiff(Double diff) {
        this.diff = diff;
    }
    
}
