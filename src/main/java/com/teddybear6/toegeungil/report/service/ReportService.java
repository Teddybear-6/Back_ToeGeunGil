package com.teddybear6.toegeungil.report.service;

import com.teddybear6.toegeungil.report.entity.Report;
import com.teddybear6.toegeungil.report.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report findReportByCode(int reportNum) {
        Report report = reportRepository.findById(reportNum);
        return report;
    }

    public List<Report> findAllReport() {
        List<Report> reportList = reportRepository.findAll();
        return reportList;
    }

    public int registReport(Report report) {
        Report result = reportRepository.save(report);

        if (Objects.isNull(result)) {
            return 0;
        } else {
            return 1;
        }
    }

    public int deleteReport(int reportNum) {
        reportRepository.deleteById(reportNum);

        Report result = reportRepository.findById(reportNum);

        if (Objects.isNull(result)){
            return 1;
        }else {
            return 0;
        }
    }
}
