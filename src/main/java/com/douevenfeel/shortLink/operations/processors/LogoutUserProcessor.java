package com.douevenfeel.shortLink.operations.processors;

import com.douevenfeel.shortLink.operations.ConsoleOperation;
import com.douevenfeel.shortLink.operations.OperationCommandProcessor;
import com.douevenfeel.shortLink.session.SessionService;

public class LogoutUserProcessor implements OperationCommandProcessor {
    private final SessionService sessionService;

    public LogoutUserProcessor(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void process() {
        if (sessionService.getCurrentUser() == null) throw new IllegalStateException("Not logged in");
        sessionService.end();
        System.out.println("Logged out");
    }

    @Override
    public ConsoleOperation getOperation() {
        return ConsoleOperation.LOGOUT;
    }
}
