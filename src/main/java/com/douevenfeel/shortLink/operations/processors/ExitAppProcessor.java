package com.douevenfeel.shortLink.operations.processors;

import com.douevenfeel.shortLink.operations.ConsoleOperation;
import com.douevenfeel.shortLink.operations.OperationCommandProcessor;

public class ExitAppProcessor implements OperationCommandProcessor {
    public ExitAppProcessor() {
    }

    @Override
    public void process() {
        System.out.println("Exiting app...");
        System.exit(0);
    }

    @Override
    public ConsoleOperation getOperation() {
        return ConsoleOperation.EXIT_APP;
    }
}
