package com.example.commutesystem.dto.commute.response;

import java.util.List;

public class CommuteDetailResponse {

    private List<DailyDetail> detail;

    private long sum;

    public CommuteDetailResponse(List<DailyDetail> detail, long sum) {
        this.detail = detail;
        this.sum = sum;
    }

    public List<DailyDetail> getDetail() {
        return detail;
    }

    public long getSum() {
        return sum;
    }
}
