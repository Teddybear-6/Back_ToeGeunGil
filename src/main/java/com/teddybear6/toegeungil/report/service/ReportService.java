package com.teddybear6.toegeungil.report.service;

import com.teddybear6.toegeungil.report.entity.Report;
import com.teddybear6.toegeungil.report.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public int registReport(Report report) {
        Report result = reportRepository.save(report);

        if(Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }
}
