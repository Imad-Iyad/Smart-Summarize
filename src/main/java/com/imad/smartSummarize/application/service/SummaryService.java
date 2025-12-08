package com.imad.smartSummarize.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummaryService {
/*

    private final SummaryRepository summaryRepository;
    private final HuggingFaceClient huggingFaceClient;

    *//**
     * يستقبل النص من الـ API، يبعته لـ Hugging Face عشان التلخيص،
     * يخزّن النتيجة في DB، ويرجع SummaryResponse للـ Controller.
     *//*
    @Transactional
    public SummaryResponse createSummary(SummaryRequest request) {
        String text = request.text();
        SummaryLength length = request.length(); // ممكن تكون null، نتعامل معها في HF client

        // 1) نجيب الملخّص من Hugging Face
        String summaryText = huggingFaceClient.summarize(text, length);

        // 2) ننشئ الـ Entity
        Summary summary = Summary.builder()
                .originalText(text)
                .summaryText(summaryText)
                .originalTextLength(text != null ? text.length() : 0)
                .createdAt(LocalDateTime.now())
                .build();

        // 3) نحفظ في DB
        Summary saved = summaryRepository.save(summary);

        // 4) نرجّع Response
        return mapToResponse(saved);
    }

    *//**
     * إرجاع كل الملخصات (مثلاً لصفحة History في الفرونت اند)
     *//*
    @Transactional(readOnly = true)
    public List<SummaryResponse> getAllSummaries() {
        return summaryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    *//**
     * إرجاع ملخص واحد حسب الـ ID
     *//*
    @Transactional(readOnly = true)
    public SummaryResponse getSummaryById(Long id) {
        Summary summary = summaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Summary not found with id: " + id));
        return mapToResponse(summary);
    }

    *//**
     * تحويل الـ Entity إلى DTO للـ API
     *//*
    private SummaryResponse mapToResponse(Summary summary) {
        return new SummaryResponse(
                summary.getId(),
                summary.getSummaryText(),
                summary.getOriginalTextLength(),
                summary.getCreatedAt()
        );
    }*/
}
