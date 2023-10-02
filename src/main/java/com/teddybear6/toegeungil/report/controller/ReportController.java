package com.teddybear6.toegeungil.report.controller;

import com.teddybear6.toegeungil.report.entity.Report;
import com.teddybear6.toegeungil.report.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/reports")
@Api(value = "신고하기 Api", tags = {"06. Report Info"}, description = "신고하기 Api")
@ApiResponses({
        @ApiResponse(code = 200,message = "성공"),
        @ApiResponse(code = 404,message = "잘못된 접근") ,
        @ApiResponse(code = 500,message = "서버에러")
})
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /* <GET> /reports: 신고 목록 조회 */
    @GetMapping
    @ApiOperation(value = "신고하기 전체 조회 Api", notes = "신고하기 전체 목록을 조회한다.")
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
    @ApiOperation(value = "신고하기 단일 조회 Api", notes = "신고 번호로 해당 게시글을 조회한다.")
    public ResponseEntity<Object> findReportByCode(@PathVariable int reportNum){
        Report report = reportService.findReportByCode(reportNum);

        if(Objects.isNull(reportNum)){
            return ResponseEntity.status(404).body(new String("신고번호가 존재하지 않습니다"));
        }
        return ResponseEntity.ok().body(report);
    }


    /* <POST> /reports: 신고 등록 */
    @PostMapping
    @ApiOperation(value = "신고하기 작성 Api", notes = "신고하기 게시글을 작성한다.")
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
    @ApiOperation(value = "신고 삭제 Api", notes = "신고 번호로 해당 게시글을 삭제한다.")
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
