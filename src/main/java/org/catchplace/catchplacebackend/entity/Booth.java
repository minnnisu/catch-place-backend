package org.catchplace.catchplacebackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Booth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 200)
    private String location;

    @OneToMany(mappedBy = "booth", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<BoothCategory> boothCategories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User operator;

    @OneToMany(mappedBy = "booth", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "booth", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Waiting> waitings = new ArrayList<>();

    @OneToMany(mappedBy = "booth", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<BoothOption> boothOptions = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime startDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private java.time.LocalTime openTime;

    @Column(nullable = false)
    private java.time.LocalTime endTime;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}