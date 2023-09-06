package com.teddybear6.toegeungil.report.dto;

import java.sql.Date;

public class ReportDTO {

    private int reportNum; // 신고 번호
    private String userId; // 신고 하는 회원(강사) 아이디 (수정하기)
    private String userId2; // 신고 당하는 회원(강사) 아이디 (수정하기)
    private int categoryNum; // 카테고리 번호
    private String reportContent; // 신고하기 내용
    private Date reportDate; // 신고 날짜
    private String reportStatus; // 신고 상태

    public ReportDTO() {
    }

    public ReportDTO(int reportNum, String userId, String userId2, int categoryNum, String reportContent, Date reportDate, String reportStatus) {
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
        return "ReportDTO{" +
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
