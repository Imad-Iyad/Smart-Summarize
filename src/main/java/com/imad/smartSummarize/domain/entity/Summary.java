package com.imad.smartSummarize.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "summaries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_text", columnDefinition = "TEXT", nullable = false)
    private String originalText;

    @Column(name = "summary_text", columnDefinition = "TEXT", nullable = false)
    private String summaryText;

    @Column(name = "original_text_length")
    private int originalTextLength;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}