package com.teddybear6.toegeungil.report.controller;

import com.teddybear6.toegeungil.report.entity.Report;
import com.teddybear6.toegeungil.report.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /* <GET> /reports: 신고 목록 조회 */


    /* <GET> /reports/{reportID} : 신고  상세 조회 */


    /* <POST> /reports: 신고 등록 */
    @PostMapping
    private ResponseEntity<?> registReport(Report report){
        int result = reportService.registReport(report);

        if(result>0){
            return ResponseEntity.ok().body("신고 등록 성공입니다");
        }else {
            return ResponseEntity.status(500).body("신고 등록 실패입니다");
        }
    }

    /* <DELETE> /reports/{reportID} : 신고 삭제 */
}
