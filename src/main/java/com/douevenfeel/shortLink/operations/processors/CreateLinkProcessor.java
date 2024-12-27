package com.douevenfeel.shortLink.operations.processors;

import com.douevenfeel.shortLink.link.Link;
import com.douevenfeel.shortLink.link.LinkService;
import com.douevenfeel.shortLink.operations.ConsoleOperation;
import com.douevenfeel.shortLink.operations.OperationCommandProcessor;
import com.douevenfeel.shortLink.session.SessionService;
import com.douevenfeel.shortLink.user.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class CreateLinkProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final LinkService linkService;
    private final SessionService sessionService;

    public CreateLinkProcessor(Scanner scanner, LinkService linkService, SessionService sessionService) {
        this.scanner = scanner;
        this.linkService = linkService;
        this.sessionService = sessionService;
    }


    @Override
    public void process() {
        User currentUser = sessionService.getCurrentUser();
        if (currentUser == null) throw new IllegalStateException("Not logged in");
        URI uri = null;
        System.out.print("Enter the URI you want to shorten (type \"cancel\" to exit): ");
        while (uri == null) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("cancel")) return;
            try {
                URI inputURI = new URI(input);
                if (!inputURI.isAbsolute()) {
                    System.out.println("Please enter an absolute URI (e.g., http://example.com)");
                } else {
                    uri = URI.create(input);
                }
            } catch (URISyntaxException e) {
                System.out.println("Invalid URI: " + e.getMessage());
            }
        }
        int limit = 0;
        System.out.println("Enter limit of visits");
        while (limit == 0) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input <= 0) {
                    System.out.println("Invalid limit");
                } else {
                    limit = input;
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid limit");
            }
        }
        int timeLimit = 0;
        System.out.println("Enter time limit of link in minutes");
        while (timeLimit == 0) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input <= 0) {
                    System.out.println("Invalid limit");
                } else {
                    timeLimit = input;
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid limit");
            }
        }
        Link link = linkService.create(uri, limit, timeLimit, currentUser.getId());
        System.out.println(link);
    }

    @Override
    public ConsoleOperation getOperation() {
        return ConsoleOperation.CREATE_LINK;
    }
}
