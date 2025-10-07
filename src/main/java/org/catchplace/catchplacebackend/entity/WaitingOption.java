package org.catchplace.catchplacebackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "waiting_option")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class WaitingOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waiting_id", nullable = false)
    private Waiting waiting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booth_option_id", nullable = false)
    private BoothOption boothOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booth_option_item_id", nullable = false)
    private BoothOptionItem boothOptionItem;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}