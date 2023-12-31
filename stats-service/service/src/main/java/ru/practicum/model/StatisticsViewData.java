package ru.practicum.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticsViewData {

    private String app;
    private String uri;
    private long count;

    public StatisticsViewData(String app, String uri, long hits) {
        this.app = app;
        this.uri = uri;
        this.count = hits;
    }
}
