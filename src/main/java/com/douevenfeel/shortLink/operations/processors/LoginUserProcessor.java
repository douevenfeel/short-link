package com.douevenfeel.shortLink.operations.processors;

import com.douevenfeel.shortLink.operations.ConsoleOperation;
import com.douevenfeel.shortLink.operations.OperationCommandProcessor;
import com.douevenfeel.shortLink.session.SessionService;
import com.douevenfeel.shortLink.user.User;
import com.douevenfeel.shortLink.user.UserService;

import java.util.Scanner;
import java.util.UUID;

public class LoginUserProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final UserService userService;
    private final SessionService sessionService;

    public LoginUserProcessor(Scanner scanner, UserService userService, SessionService sessionService) {
        this.scanner = scanner;
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @Override
    public void process() {
        if (sessionService.getCurrentUser() != null) throw new IllegalStateException("Already logged in");
        System.out.println("Please enter your id (type \"cancel\" to exit): ");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("cancel")) return;
            try {
                UUID id = UUID.fromString(input);
                User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
                sessionService.start(user);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid id");
            }
        }
    }

    @Override
    public ConsoleOperation getOperation() {
        return ConsoleOperation.LOGIN;
    }
}
