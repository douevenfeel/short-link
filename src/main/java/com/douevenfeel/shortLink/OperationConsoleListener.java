package com.douevenfeel.shortLink;

import com.douevenfeel.shortLink.operations.ConsoleOperation;
import com.douevenfeel.shortLink.operations.OperationCommandProcessor;

import java.util.Map;
import java.util.Scanner;

public class OperationConsoleListener {
    private final Scanner scanner;
    private final Map<ConsoleOperation, OperationCommandProcessor> processorMap;

    public OperationConsoleListener(Scanner scanner, Map<ConsoleOperation, OperationCommandProcessor> processorMap) {
        this.scanner = scanner;
        this.processorMap = processorMap;
    }

    public void start() {
        System.out.println("Operation console listener started");
        listenUpdates();
    }

    public void listenUpdates() {
        while (true) {
            ConsoleOperation operationType = listenNextOperation();
            processNextOperation(operationType);
        }
    }

    private ConsoleOperation listenNextOperation() {
        printAvailableOperations();
        while (true) {
            String operation = scanner.nextLine();
            try {
                return ConsoleOperation.valueOf(operation);
            } catch (IllegalArgumentException e) {
                System.out.println("Not a valid operation");
            }
        }
    }

    private void processNextOperation(ConsoleOperation operation) {
        try {
            OperationCommandProcessor processor = processorMap.get(operation);
            processor.process();
        } catch (Exception e) {
            System.out.printf("Error executing command %s: %s%n", operation, e.getMessage());
        }
    }

    private void printAvailableOperations() {
        System.out.println("\nEnter command:");
        processorMap.keySet().forEach(System.out::println);
    }
}
