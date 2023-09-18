package com.teddybear6.toegeungil.report.dto;

import java.sql.Date;

public class ReportDTO {

    private int reportNum; // 신고 번호
    private String reportMember; // 신고 하는 회원
    private String receiveMember; // 신고 받는 회원
    private String reportContent; // 신고하기 내용
    private Date reportDate; // 신고 날짜
    private String reportStatus; // 신고 상태

    public ReportDTO() {
    }

    public ReportDTO(int reportNum, String reportMember, String receiveMember, String reportContent, Date reportDate, String reportStatus) {
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
        return "ReportDTO{" +
                "reportNum=" + reportNum +
                ", reportMember='" + reportMember + '\'' +
                ", receiveMember='" + receiveMember + '\'' +
                ", reportContent='" + reportContent + '\'' +
                ", reportDate=" + reportDate +
                ", reportStatus='" + reportStatus + '\'' +
                '}';
    }
}
