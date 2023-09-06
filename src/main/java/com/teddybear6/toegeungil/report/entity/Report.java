package com.teddybear6.toegeungil.report.entity;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "report")
@Table(name = "report")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_num")         // 신고 번호
    private int reportNum;

    @Column(name = "user_id")
    private String userId;              // 신고 하는 회원 아이디

    @Column(name = "user_id2")
    private String userId2;             // 신고 당하는 회원 아이디

    @Column(name = "category_num")
    private int categoryNum;            // 카테고리 번호

    @Column(name = "report_content")
    private String reportContent;       // 신고 내용

    @Column(name = "report_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date reportDate;            // 신고 상태

    @Column(name = "report_status", columnDefinition = "varchar(1)")
    private String reportStatus;

    public Report() {
    }

    public Report(int reportNum, String userId, String userId2, int categoryNum, String reportContent, Date reportDate, String reportStatus) {
        this.reportNum = reportNum;
        this.userId = userId;
        this.userId2 = userId2;
        this.categoryNum = categoryNum;
        this.reportContent = reportContent;
        this.reportDate = reportDate;
        this.reportStatus = reportStatus;
    }

    public int getReportNum() {
        return reportNum;
    }

    public void setReportNum(int reportNum) {
        this.reportNum = reportNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }

    public int getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(int categoryNum) {
        this.categoryNum = categoryNum;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportNum=" + reportNum +
                ", userId='" + userId + '\'' +
                ", userId2='" + userId2 + '\'' +
                ", categoryNum=" + categoryNum +
                ", reportContent='" + reportContent + '\'' +
                ", reportDate=" + reportDate +
                ", reportStatus='" + reportStatus + '\'' +
                '}';
    }
}
