package com.douevenfeel.shortLink.link;

import com.douevenfeel.shortLink.session.SessionService;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class LinkService {
    private final SessionService sessionService;
    private final Map<UUID, Link> linkRepository;
    private final int minLinkLimit;
    private final int minLinkTime;

    public LinkService(SessionService sessionService, Map<UUID, Link> linkRepository, int minLinkLimit, int minLinkTime) {
        this.sessionService = sessionService;
        this.linkRepository = linkRepository;
        this.minLinkLimit = minLinkLimit;
        this.minLinkTime = minLinkTime;
    }


    public Optional<Link> findById(UUID id) {
        return Optional.ofNullable(linkRepository.get(id));
    }

    public Optional<Link> findByShortUrl(String shortUrl) {
        return linkRepository.values().stream()
                .filter(link -> link.getShortUrl().equals(shortUrl))
                .findFirst();
    }

    public List<Link> findByUserId(UUID userId) {
        return linkRepository.values().stream()
                .filter(link -> link.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public Link create(URI url, int limit, int timeLimit, UUID userId) {
        Link link = new Link(url, Math.max(limit, minLinkLimit), Math.max(timeLimit, minLinkTime), userId);
        linkRepository.put(link.getId(), link);
        return link;
    }

    public void open(String url) {
        Link link = findByShortUrl(url).orElseThrow(() -> new RuntimeException("Link not found"));
        if (!checkLimit(link)) {
            deleteById(link.getId());
            System.out.println("Link limit reached");
            return;
        }
        if (isTimeLimitExceeded(link)) {
            deleteById(link.getId());
            System.out.println("Link time limit exceeded");
            return;
        }
        link.setVisits(link.getVisits() + 1);
        try {
            Desktop.getDesktop().browse(link.getOriginalUrl());
            System.out.println("Original URL: " + link.getOriginalUrl());
            System.out.println("Visits remaining: " + (link.getLimit() - link.getVisits()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkLimit(Link link) {
        return link.getVisits() < link.getLimit();
    }

    private boolean isTimeLimitExceeded(Link link) {
        LocalDateTime now = LocalDateTime.now();
        long minutesPassed = ChronoUnit.MINUTES.between(link.getCreatedAt(), now);
        System.out.println("minutes passed: " + minutesPassed);
        return minutesPassed >= link.getTimeLimit();
    }

    public Link updateLimit(String shortLink, int limit) {
        Link link = findByShortUrl(shortLink).orElseThrow(() -> new RuntimeException("Link not found"));
        link.setLimit(Math.max(limit, minLinkLimit));
        return link;
    }

    public void deleteById(UUID id) {
        Link link = findById(id).orElseThrow(() -> new RuntimeException("Link not found"));
        if (link.getUserId() != sessionService.getCurrentUser().getId()) {
            throw new RuntimeException("You can't delete this link");
        }
        linkRepository.remove(id);
    }
}
