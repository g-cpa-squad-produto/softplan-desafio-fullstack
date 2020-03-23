package com.softplan.teste.process.model;

public class OpinionReviewCount {
    private Long opnionId;
    private Long reviewCount;

    public OpinionReviewCount(Long opnionId, Long reviewCount) {
        this.opnionId = opnionId;
        this.reviewCount = reviewCount;
    }

    public Long getOpnionId() {
        return opnionId;
    }

    public void setOpnionId(Long opnionId) {
        this.opnionId = opnionId;
    }

    public Long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Long reviewCount) {
        this.reviewCount = reviewCount;
    }
}