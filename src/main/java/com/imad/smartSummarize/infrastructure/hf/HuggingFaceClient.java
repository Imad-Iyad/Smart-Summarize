package com.imad.smartSummarize.infrastructure.hf;


import com.imad.smartSummarize.domain.model.SummaryLength;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class HuggingFaceClient {

    @Value("${huggingface.api.key}")
    private String apiKey;

    @Value("${huggingface.model.url}")
    private String modelUrl;

    private final WebClient.Builder webClientBuilder;

    public String summarize(String text, SummaryLength length) {

        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Text must not be empty");
        }

        SummaryLength effectiveLength = (length != null) ? length : SummaryLength.MEDIUM;

        Map<String, Object> parameters = buildParameters(effectiveLength);

        Map<String, Object> body = new HashMap<>();
        body.put("inputs", text);
        body.put("parameters", parameters);

        try {
            WebClient client = webClientBuilder.build();

            List<Map<String, Object>> response = client.post()
                    .uri(modelUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
                    .block();

            if (response == null || response.isEmpty()) {
                throw new RuntimeException("Empty response from Hugging Face");
            }

            Object summaryText = response.get(0).get("summary_text");
            if (summaryText == null) {
                throw new RuntimeException("No summary_text returned from Hugging Face");
            }

            return summaryText.toString();

        } catch (WebClientResponseException e) {
            System.err.println("HF API error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw new RuntimeException("Failed calling HF API", e);
        }
    }

    private Map<String, Object> buildParameters(SummaryLength length) {

        Map<String, Object> params = new HashMap<>();

        switch (length) {
            case SHORT -> {
                params.put("max_length", 60);
                params.put("min_length", 20);
            }
            case MEDIUM -> {
                params.put("max_length", 120);
                params.put("min_length", 40);
            }
            case LONG -> {
                params.put("max_length", 200);
                params.put("min_length", 80);
            }
        }

        params.put("no_repeat_ngram_size", 3);

        return params;
    }
}
