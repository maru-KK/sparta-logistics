package com.sparta.logistics.infra.infrastructure.persistence.adapter;

import com.sparta.logistics.infra.domain.slack.Slack;
import com.sparta.logistics.infra.domain.slack.SlackToSendMessage;
import com.sparta.logistics.infra.infrastructure.persistence.entity.SnsEntity;
import com.sparta.logistics.infra.infrastructure.persistence.repository.SnsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SlackPersistenceAdapter {

    private final SnsRepository snsRepository;

    public List<Slack> save(SlackToSendMessage message) {
        List<SnsEntity> entities = SnsEntity.from(message);
        List<SnsEntity> snsEntities = snsRepository.saveAll(entities);

        return snsEntities.stream()
            .map(SnsEntity::toDomain)
            .toList();
    }
}
