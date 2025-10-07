package org.catchplace.catchplacebackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catchplace.catchplacebackend.constant.WaitingStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "waiting")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Waiting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booth_id", nullable = false)
    private Booth booth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 등록자

    @Column(nullable = false)
    @Builder.Default
    private Integer adultCount = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer childCount = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer infantCount = 0;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private WaitingStatus status = WaitingStatus.WAITING;

    @OneToMany(mappedBy = "waiting", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<WaitingOption> waitingOptions = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // 상태 변경 메서드
    public void updateStatus(WaitingStatus newStatus) {
        this.status = newStatus;
    }

    // 총 인원 조회 메서드
    public Integer getTotalPartySize() {
        return adultCount + childCount + infantCount;
    }

    // 인원 유효성 검사
    @PrePersist
    @PreUpdate
    private void validatePartySize() {
        if (adultCount < 0 || childCount < 0 || infantCount < 0) {
            throw new IllegalArgumentException("인원은 0명 이상이어야 합니다.");
        }
        if (getTotalPartySize() < 1) {
            throw new IllegalArgumentException("총 인원은 1명 이상이어야 합니다.");
        }
    }
}