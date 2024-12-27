package com.douevenfeel.shortLink.operations.processors;

import com.douevenfeel.shortLink.link.LinkService;
import com.douevenfeel.shortLink.operations.ConsoleOperation;
import com.douevenfeel.shortLink.operations.OperationCommandProcessor;
import com.douevenfeel.shortLink.session.SessionService;

import java.util.Scanner;
import java.util.UUID;

public class DeleteLinkProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final LinkService linkService;
    private final SessionService sessionService;

    public DeleteLinkProcessor(Scanner scanner, LinkService linkService, SessionService sessionService) {
        this.scanner = scanner;
        this.linkService = linkService;
        this.sessionService = sessionService;
    }

    @Override
    public void process() {
        if (sessionService.getCurrentUser() == null) throw new IllegalStateException("Not logged in");
        System.out.println("Enter link id to delete (type \"cancel\" to exit): ");
        while (true) {
            String input = scanner.nextLine();
            try {
                UUID id = UUID.fromString(input);
                linkService.deleteById(id);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid link id");
            }
        }
    }

    @Override
    public ConsoleOperation getOperation() {
        return ConsoleOperation.DELETE_LINK;
    }
}
