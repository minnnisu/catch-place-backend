package org.catchplace.catchplacebackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "booth_option_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class BoothOptionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booth_option_id", nullable = false)
    private BoothOption boothOption;

    @Column(nullable = false, length = 100)
    private String name; // 항목 이름 (예: "컴퓨터공학과", "전자정보통신공학과")

    @Column(nullable = false)
    @Builder.Default
    private Integer displayOrder = 0; // 표시 순서

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}