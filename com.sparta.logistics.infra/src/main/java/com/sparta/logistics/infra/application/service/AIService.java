package com.sparta.logistics.infra.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.logistics.infra.application.dto.InfraRequestDto;
import com.sparta.logistics.infra.infrastructure.persistence.entity.AIEntity;
import com.sparta.logistics.infra.infrastructure.persistence.repository.AIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AIService {

    @Value("${ai.apiKey}")
    private String apiKey;

    private final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent";
    private final AIRepository aiRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AIEntity generateResponse(InfraRequestDto requestDto) {
        String aiPrompt = buildAIRequestPrompt(requestDto);

        String requestBody = "{ \"contents\": [ { \"parts\": [ { \"text\": \"" + aiPrompt.replace("\"", "\\\"") + "\" } ] } ] }";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                API_URL + "?key=" + apiKey, HttpMethod.POST, request, String.class
        );

        String aiResponse = response.getBody();
        JsonNode rootNode = null;

        try {
            rootNode = objectMapper.readTree(aiResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        JsonNode candidates = rootNode.path("candidates");
        StringBuilder formattedResponse = new StringBuilder();

        if (candidates.isArray() && candidates.size() > 0) {
            JsonNode candidate = candidates.get(0);
            JsonNode content = candidate.path("content");
            if (content.has("parts")) {
                for (JsonNode part : content.path("parts")) {
                    String text = part.path("text").asText();
                    text = text.replaceAll("\\\\", " ");
                    String datePattern = "\\d{1,2}월 \\d{1,2}일 \\d{1,2}시";
                    Pattern pattern = Pattern.compile(datePattern);
                    Matcher matcher = pattern.matcher(text);
                    if (matcher.find()) {
                        formattedResponse.append(matcher.group());
                    }
                }
            }
        }

        AIEntity aiDescription = AIEntity.builder()
                .question(aiPrompt)
                .answer(formattedResponse.toString())
                .build();

        return aiRepository.save(aiDescription);
    }

    private String buildAIRequestPrompt(InfraRequestDto requestDto) {
        return String.format(
                "상품: %s, 수량: %d, 요청: %s, 출발지: %s, 경유지: %s, 목적지: %s: "
                        + "위 데이터를 바탕으로 최종 발송 시한을 00월 00일 00시 형식으로만 답변해 주세요.",
                requestDto.getProductName(), requestDto.getProductQuantity(), requestDto.getRequest(),
                requestDto.getOriginHub(), requestDto.getDestinationHub(), requestDto.getCompanyAddress()
        );
    }
}
