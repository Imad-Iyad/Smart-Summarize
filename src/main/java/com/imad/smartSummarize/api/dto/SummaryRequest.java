package com.imad.smartSummarize.api.dto;


import com.imad.smartSummarize.domain.model.SummaryLength;
import jakarta.validation.constraints.NotBlank;

public record SummaryRequest(
        @NotBlank(message = "Text must not be empty")
        String text,
        SummaryLength length // ممكن ييجي null ونحط MEDIUM كـ default في الخدمة
) {}
