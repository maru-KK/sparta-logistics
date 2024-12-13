package com.sparta.logistics.hubcompany.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "p_company")
public class CompanyEntity extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "type", nullable = false, length = 45)
    private String type;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "hub_id", nullable = false)
    private HubEntity hub;

    @Column(name = "user_id", nullable = false)
    private Long userId;

}