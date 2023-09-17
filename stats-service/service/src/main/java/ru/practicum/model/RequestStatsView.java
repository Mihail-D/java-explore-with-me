package ru.practicum.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestStatsView {

    private String uri;
    private String app;
    private long count;

    public RequestStatsView(String app, String uri, long hits) {
        this.app = app;
        this.uri = uri;
        this.count = hits;
    }
}