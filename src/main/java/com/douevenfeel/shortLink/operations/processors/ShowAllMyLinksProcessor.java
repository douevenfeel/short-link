package com.douevenfeel.shortLink.operations.processors;

import com.douevenfeel.shortLink.link.LinkService;
import com.douevenfeel.shortLink.operations.ConsoleOperation;
import com.douevenfeel.shortLink.operations.OperationCommandProcessor;
import com.douevenfeel.shortLink.session.SessionService;

import java.util.UUID;

public class ShowAllMyLinksProcessor implements OperationCommandProcessor {
    private final SessionService sessionService;
    private final LinkService linkService;

    public ShowAllMyLinksProcessor(SessionService sessionService, LinkService linkService) {
        this.sessionService = sessionService;
        this.linkService = linkService;
    }

    @Override
    public void process() {
        if (sessionService.getCurrentUser() == null) throw new IllegalStateException("Not logged in");
        UUID userId = sessionService.getCurrentUser().getId();
        System.out.println("List of all my links: ");
        linkService.findByUserId(userId).forEach(System.out::println);
    }

    @Override
    public ConsoleOperation getOperation() {
        return ConsoleOperation.SHOW_ALL_MY_LINKS;
    }
}
