package com.douevenfeel.shortLink.operations.processors;

import com.douevenfeel.shortLink.link.LinkService;
import com.douevenfeel.shortLink.operations.ConsoleOperation;
import com.douevenfeel.shortLink.operations.OperationCommandProcessor;

import java.util.Scanner;

public class OpenLinkProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final LinkService linkService;

    public OpenLinkProcessor(Scanner scanner, LinkService linkService) {
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
        linkService.open(shortLink);
    }

    @Override
    public ConsoleOperation getOperation() {
        return ConsoleOperation.OPEN_LINK;
    }
}
