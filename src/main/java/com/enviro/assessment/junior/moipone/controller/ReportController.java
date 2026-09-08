package com.enviro.assessment.junior.moipone.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enviro.assessment.junior.moipone.entity.Withdrawal;
import com.enviro.assessment.junior.moipone.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/withdrawals")
    public ResponseEntity<String> exportWithdrawalReport(
            @RequestParam(required = false) String investorId) {

        List<Withdrawal> withdrawals
                = reportService.getWithdrawalsForReport(investorId);

        StringBuilder csv = new StringBuilder();

        csv.append("ID,Investor ID,Investment ID,Amount,Withdrawal Type,Reason,Status,Request Date\n");

        for (Withdrawal withdrawal : withdrawals) {
            csv.append(withdrawal.getId()).append(",");
            csv.append(withdrawal.getInvestorId()).append(",");
            csv.append(withdrawal.getInvestmentId()).append(",");
            csv.append(withdrawal.getAmount()).append(",");
            csv.append(withdrawal.getWithdrawalType()).append(",");
            csv.append(withdrawal.getReason()).append(",");
            csv.append(withdrawal.getStatus()).append(",");
            csv.append(withdrawal.getRequestDate()).append("\n");
        }

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=withdrawal-report.csv"
                )
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv.toString());
    }
}
