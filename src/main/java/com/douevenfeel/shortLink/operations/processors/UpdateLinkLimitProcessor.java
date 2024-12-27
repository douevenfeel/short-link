package com.douevenfeel.shortLink.operations.processors;

import com.douevenfeel.shortLink.link.Link;
import com.douevenfeel.shortLink.link.LinkService;
import com.douevenfeel.shortLink.operations.ConsoleOperation;
import com.douevenfeel.shortLink.operations.OperationCommandProcessor;

import java.util.Scanner;

public class UpdateLinkLimitProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final LinkService linkService;

    public UpdateLinkLimitProcessor(Scanner scanner, LinkService linkService) {
        this.scanner = scanner;
        this.linkService = linkService;
    }

    @Override
    public void process() {
        System.out.println("Enter short link (type \"cancel\" to exit): ");
        String shortLink = scanner.nextLine();
        if (shortLink.equalsIgnoreCase("cancel")) {
            return;
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
        Link link = linkService.updateLimit(shortLink, limit);
        System.out.println(link);
    }

    @Override
    public ConsoleOperation getOperation() {
        return ConsoleOperation.UPDATE_LINK_LIMIT;
    }
}
