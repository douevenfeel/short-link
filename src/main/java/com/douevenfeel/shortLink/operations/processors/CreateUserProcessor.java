package com.douevenfeel.shortLink.operations.processors;

import com.douevenfeel.shortLink.operations.ConsoleOperation;
import com.douevenfeel.shortLink.operations.OperationCommandProcessor;
import com.douevenfeel.shortLink.session.SessionService;
import com.douevenfeel.shortLink.user.User;
import com.douevenfeel.shortLink.user.UserService;

public class CreateUserProcessor implements OperationCommandProcessor {
    private final UserService userService;
    private final SessionService sessionService;

    public CreateUserProcessor(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @Override
    public void process() {
        if (sessionService.getCurrentUser() != null) throw new IllegalStateException("Already logged in");
        User user = userService.create();
        sessionService.start(user);
    }

    @Override
    public ConsoleOperation getOperation() {
        return ConsoleOperation.REGISTER;
    }
}
