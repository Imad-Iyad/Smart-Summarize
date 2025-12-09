package com.imad.smartSummarize.application.service;

import com.imad.smartSummarize.api.dto.SummaryRequest;
import com.imad.smartSummarize.api.dto.SummaryResponse;
import com.imad.smartSummarize.domain.entity.Summary;
import com.imad.smartSummarize.domain.model.SummaryLength;
import com.imad.smartSummarize.domain.repository.SummaryRepository;
import com.imad.smartSummarize.infrastructure.hf.HuggingFaceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final SummaryRepository summaryRepository;
    private final HuggingFaceClient huggingFaceClient;

    @Transactional
    public SummaryResponse createSummary(SummaryRequest request) {
        String text = request.text();
        SummaryLength length = request.length();

        SummaryLength effectiveLength = (length != null) ? length : SummaryLength.MEDIUM;

        String summaryText = huggingFaceClient.summarize(text, effectiveLength);

        Summary summary = Summary.builder()
                .originalText(text)
                .summaryText(summaryText)
                .originalTextLength(text != null ? text.length() : 0)
                .createdAt(LocalDateTime.now())
                .build();

        Summary saved = summaryRepository.save(summary);

        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<SummaryResponse> getAllSummaries() {
        return summaryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public SummaryResponse getSummaryById(Long id) {
        Summary summary = summaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Summary not found with id: " + id));

        return mapToResponse(summary);
    }

    private SummaryResponse mapToResponse(Summary summary) {
        return new SummaryResponse(
                summary.getId(),
                summary.getSummaryText(),
                summary.getOriginalTextLength(),
                summary.getCreatedAt()
        );
    }
}