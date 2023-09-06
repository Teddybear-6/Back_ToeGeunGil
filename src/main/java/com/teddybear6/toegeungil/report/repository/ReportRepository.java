package com.teddybear6.toegeungil.report.repository;

import com.teddybear6.toegeungil.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}
