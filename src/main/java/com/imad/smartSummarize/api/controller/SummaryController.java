package com.imad.smartSummarize.api.controller;

import com.imad.smartSummarize.api.dto.SummaryRequest;
import com.imad.smartSummarize.api.dto.SummaryResponse;
import com.imad.smartSummarize.application.service.SummaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/summaries")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    /**
     * Create a new summary from raw text.
     * POST /api/summaries
     */
    @PostMapping
    public ResponseEntity<SummaryResponse> createSummary(@Valid @RequestBody SummaryRequest request) {
        SummaryResponse response = summaryService.createSummary(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get all summaries (history).
     * GET /api/summaries
     */
    @GetMapping
    public ResponseEntity<List<SummaryResponse>> getAllSummaries() {
        List<SummaryResponse> summaries = summaryService.getAllSummaries();
        return ResponseEntity.ok(summaries);
    }

    /**
     * Get a single summary by its ID.
     * GET /api/summaries/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<SummaryResponse> getSummaryById(@PathVariable Long id) {
        SummaryResponse summary = summaryService.getSummaryById(id);
        return ResponseEntity.ok(summary);
    }
}