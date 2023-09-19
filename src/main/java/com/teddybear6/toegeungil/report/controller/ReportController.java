package com.teddybear6.toegeungil.report.controller;

import com.teddybear6.toegeungil.report.entity.Report;
import com.teddybear6.toegeungil.report.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /* <GET> /reports: 신고 목록 조회 */
    @GetMapping
    public ResponseEntity<List<?>> findAllReport(){
        List<Report> reportList = reportService.findAllReport();

        if (reportList.size() <= 0){
            List<String> error = new ArrayList<>();
            error.add("String");
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok().body(reportList);
    }

    /* <GET> /reports/{reportID} : 신고 상세 조회 */
    @GetMapping("/{reportNum}")
    public ResponseEntity<Object> findReportByCode(@PathVariable int reportNum){
        Report report = reportService.findReportByCode(reportNum);

        if(Objects.isNull(reportNum)){
            return ResponseEntity.status(404).body(new String("신고번호가 존재하지 않습니다"));
        }
        return ResponseEntity.ok().body(report);
    }


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
    @DeleteMapping("/{reportNum}")
    private ResponseEntity<?> deleteReport(@PathVariable int reportNum){
        Report report = reportService.findReportByCode(reportNum);

        if (Objects.isNull(report)){
            return ResponseEntity.status(404).body("신고 내역이 존재하지 않습니다");
        }

        int result = reportService.deleteReport(reportNum);

        if (result > 0){
            return ResponseEntity.ok().body("신고내역 삭제 성공입니다");
        }else {
            return ResponseEntity.status(400).body("신고내역 삭제 실패입니다");
        }
    }
}
