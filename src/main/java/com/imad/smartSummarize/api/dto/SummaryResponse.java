package com.imad.smartSummarize.api.dto;

import java.time.LocalDateTime;

public record SummaryResponse(
        Long id,
        String summary,
        int originalTextLength,
        LocalDateTime createdAt
) {}
