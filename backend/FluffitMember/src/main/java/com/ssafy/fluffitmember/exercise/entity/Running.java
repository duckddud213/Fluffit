package com.ssafy.fluffitmember.exercise.entity;

import com.ssafy.fluffitmember.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Running {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne(fetch = FetchType.LAZY) // 성능 최적화를 위해 LAZY 로딩 설정
    @JoinColumn(name = "member_pid") // 외래 키 이름 설정
    private Member member;

    @Column(nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Column(nullable = false)
    private int calorie;

    @Column(name = "member_id",nullable = false)
    private String memberId;

    @Builder
    public Running(Member member, LocalDateTime startTime, LocalDateTime endTime, int calorie, String memberId) {
        this.member = member;
        this.startTime = startTime;
        this.endTime = endTime;
        this.calorie = calorie;
        this.memberId = memberId;
    }
    public static Running of(Member member, LocalDateTime startTime, LocalDateTime endTime, int calorie,String memberId) {
        return builder()
                .member(member)
                .startTime(startTime)
                .endTime(endTime)
                .calorie(calorie)
                .memberId(memberId)
                .build();
    }
}
