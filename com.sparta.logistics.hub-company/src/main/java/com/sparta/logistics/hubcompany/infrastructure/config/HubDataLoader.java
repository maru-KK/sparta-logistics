package com.sparta.logistics.hubcompany.infrastructure.config;

import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.repository.HubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HubDataLoader implements CommandLineRunner {

    private final HubRepository hubRepository;

    @Override
    public void run(String... args) {
        if (hubRepository.count() == 0) {
            hubRepository.saveAll(List.of(
                    HubEntity.builder()
                            .name("서울특별시 센터")
                            .address("서울특별시 송파구 송파대로 55")
                            .latitude(new BigDecimal("37.514574"))
                            .longitude(new BigDecimal("127.112236"))
                            .userId(1L)
                            .build(),
                    HubEntity.builder()
                            .name("경기 북부 센터")
                            .address("경기도 고양시 덕양구 권율대로 570")
                            .latitude(new BigDecimal("37.658347"))
                            .longitude(new BigDecimal("126.832010"))
                            .userId(2L)
                            .build(),
                    HubEntity.builder()
                            .name("경기 남부 센터")
                            .address("경기도 이천시 덕평로 257-21")
                            .latitude(new BigDecimal("37.277902"))
                            .longitude(new BigDecimal("127.460717"))
                            .userId(3L)
                            .build(),
                    HubEntity.builder()
                            .name("부산광역시 센터")
                            .address("부산 동구 중앙대로 206")
                            .latitude(new BigDecimal("35.107903"))
                            .longitude(new BigDecimal("129.040270"))
                            .userId(4L)
                            .build(),
                    HubEntity.builder()
                            .name("대구광역시 센터")
                            .address("대구 북구 태평로 161")
                            .latitude(new BigDecimal("35.872289"))
                            .longitude(new BigDecimal("128.602153"))
                            .userId(5L)
                            .build(),
                    HubEntity.builder()
                            .name("인천광역시 센터")
                            .address("인천 남동구 정각로 29")
                            .latitude(new BigDecimal("37.456256"))
                            .longitude(new BigDecimal("126.705017"))
                            .userId(6L)
                            .build(),
                    HubEntity.builder()
                            .name("광주광역시 센터")
                            .address("광주 서구 내방로 111")
                            .latitude(new BigDecimal("35.159523"))
                            .longitude(new BigDecimal("126.852556"))
                            .userId(7L)
                            .build(),
                    HubEntity.builder()
                            .name("대전광역시 센터")
                            .address("대전 서구 둔산로 100")
                            .latitude(new BigDecimal("36.350412"))
                            .longitude(new BigDecimal("127.384547"))
                            .userId(8L)
                            .build(),
                    HubEntity.builder()
                            .name("울산광역시 센터")
                            .address("울산 남구 중앙로 201")
                            .latitude(new BigDecimal("35.539352"))
                            .longitude(new BigDecimal("129.314286"))
                            .userId(9L)
                            .build(),
                    HubEntity.builder()
                            .name("세종특별자치시 센터")
                            .address("세종특별자치시 한누리대로 2130")
                            .latitude(new BigDecimal("36.480033"))
                            .longitude(new BigDecimal("127.289347"))
                            .userId(10L)
                            .build(),
                    HubEntity.builder()
                            .name("강원특별자치도 센터")
                            .address("강원특별자치도 춘천시 중앙로 1")
                            .latitude(new BigDecimal("37.880932"))
                            .longitude(new BigDecimal("127.727033"))
                            .userId(11L)
                            .build(),
                    HubEntity.builder()
                            .name("충청북도 센터")
                            .address("충북 청주시 상당구 상당로 82")
                            .latitude(new BigDecimal("36.635561"))
                            .longitude(new BigDecimal("127.491733"))
                            .userId(12L)
                            .build(),
                    HubEntity.builder()
                            .name("충청남도 센터")
                            .address("충남 홍성군 홍북읍 충남대로 21")
                            .latitude(new BigDecimal("36.654344"))
                            .longitude(new BigDecimal("126.680501"))
                            .userId(13L)
                            .build(),
                    HubEntity.builder()
                            .name("전북특별자치도 센터")
                            .address("전북특별자치도 전주시 완산구 효자로 225")
                            .latitude(new BigDecimal("35.822643"))
                            .longitude(new BigDecimal("127.148699"))
                            .userId(14L)
                            .build(),
                    HubEntity.builder()
                            .name("전라남도 센터")
                            .address("전남 무안군 삼향읍 오룡길 1")
                            .latitude(new BigDecimal("34.973538"))
                            .longitude(new BigDecimal("126.732119"))
                            .userId(15L)
                            .build(),
                    HubEntity.builder()
                            .name("경상북도 센터")
                            .address("경북 안동시 풍천면 도청대로 455")
                            .latitude(new BigDecimal("36.566107"))
                            .longitude(new BigDecimal("128.729467"))
                            .userId(16L)
                            .build(),
                    HubEntity.builder()
                            .name("경상남도 센터")
                            .address("경남 창원시 의창구 중앙대로 300")
                            .latitude(new BigDecimal("35.230660"))
                            .longitude(new BigDecimal("128.682355"))
                            .userId(17L)
                            .build()
            ));
        }
    }
}