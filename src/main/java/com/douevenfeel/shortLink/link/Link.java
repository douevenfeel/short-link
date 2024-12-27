package com.douevenfeel.shortLink.link;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

public class Link {
    private final UUID id;
    private final URI originalUrl;
    private final String shortUrl;
    private final LocalDateTime createdAt;
    private final UUID userId;

    private int limit;
    private int timeLimit;
    private int visits;

    public Link(URI originalLink, int limit, int timeLimit, UUID userId) {
        this.id = UUID.randomUUID();
        this.originalUrl = originalLink;
        this.limit = limit;
        this.timeLimit = timeLimit;
        this.visits = 0;
        this.shortUrl = "http://sh.rt/" + UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.userId = userId;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public UUID getId() {
        return id;
    }

    public URI getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UUID getUserId() {
        return userId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", originalUrl=" + originalUrl +
                ", shortUrl='" + shortUrl + '\'' +
                ", createdAt=" + createdAt +
                ", userId=" + userId +
                ", limit=" + limit +
                ", timeLimit=" + timeLimit +
                ", visits=" + visits +
                '}';
    }
}
