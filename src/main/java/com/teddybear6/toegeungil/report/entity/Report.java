package com.teddybear6.toegeungil.report.entity;

import org.hibernate.annotations.ColumnDefault;
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

    @Column(name = "report_member")
    private String reportMember;        // 신고 하는 회원

    @Column(name = "receive_member")
    private String receiveMember;       // 신고 받는 회원

    @Column(name = "report_content")
    private String reportContent;       // 신고 내용

    @Column(name = "report_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date reportDate;            // 신고 날짜

    @Column(name = "report_status", columnDefinition = "varchar(1)")
    @ColumnDefault("'Y'")
    private String reportStatus;        // 신고 상태

    public Report() {
    }

    public Report(int reportNum, String reportMember, String receiveMember, String reportContent, Date reportDate, String reportStatus) {
        this.reportNum = reportNum;
        this.reportMember = reportMember;
        this.receiveMember = receiveMember;
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

    public String getReportMember() {
        return reportMember;
    }

    public void setReportMember(String reportMember) {
        this.reportMember = reportMember;
    }

    public String getReceiveMember() {
        return receiveMember;
    }

    public void setReceiveMember(String receiveMember) {
        this.receiveMember = receiveMember;
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
                ", reportMember='" + reportMember + '\'' +
                ", receiveMember='" + receiveMember + '\'' +
                ", reportContent='" + reportContent + '\'' +
                ", reportDate=" + reportDate +
                ", reportStatus='" + reportStatus + '\'' +
                '}';
    }
}
