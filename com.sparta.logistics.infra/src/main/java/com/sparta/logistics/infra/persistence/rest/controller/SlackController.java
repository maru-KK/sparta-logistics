package com.sparta.logistics.infra.persistence.rest.controller;

import com.sparta.logistics.infra.domain.slack.Slack;
import com.sparta.logistics.infra.domain.slack.SlackToSendMessage;
import com.sparta.logistics.infra.infrastructure.slack.adapter.SlackMessageAdapter;
import com.sparta.logistics.infra.persistence.rest.dto.SlackSendMessage;
import com.sparta.logistics.infra.persistence.rest.dto.SlackSendMessage.Response;
import com.sparta.logistics.infra.persistence.rest.util.ApiResponse;
import com.sparta.logistics.infra.persistence.rest.util.ApiResponse.Success;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/infra/sns")
@RestController
public class SlackController {

    private final SlackMessageAdapter slackMessageAdapter;

    @PostMapping("/send")
    public ResponseEntity<Success<List<Response>>> sendMessage(
        @Valid @RequestBody SlackSendMessage.Request request
    ) {
        SlackToSendMessage slackToSendMessage = request.toDomain();
        List<Slack> slacks = slackMessageAdapter.sendMessage(slackToSendMessage);

        List<Response> response = slacks.stream().map(Response::from).toList();

        return ApiResponse.success(response, HttpStatus.OK);
    }
}
