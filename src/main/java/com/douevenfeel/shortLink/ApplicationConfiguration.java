package com.douevenfeel.shortLink;

import com.douevenfeel.shortLink.link.Link;
import com.douevenfeel.shortLink.link.LinkService;
import com.douevenfeel.shortLink.operations.ConsoleOperation;
import com.douevenfeel.shortLink.operations.OperationCommandProcessor;
import com.douevenfeel.shortLink.session.SessionService;
import com.douevenfeel.shortLink.user.User;
import com.douevenfeel.shortLink.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public OperationConsoleListener operationConsoleListener(
            Scanner scanner,
            List<OperationCommandProcessor> commandProcessorList
    ) {
        Map<ConsoleOperation, OperationCommandProcessor> processorMap = commandProcessorList
                .stream()
                .collect(
                        Collectors.toMap(
                                OperationCommandProcessor::getOperation,
                                p -> p
                        ));
        return new OperationConsoleListener(
                scanner,
                processorMap
        );
    }

    @Bean
    public LinkService linkService(SessionService sessionService, Map<UUID, Link> linkRepository, @Value("${link.min-visits}") int minLinkLimit, @Value("${link.min-time}") int minLinkTime) {
        return new LinkService(sessionService, linkRepository, minLinkLimit, minLinkTime);
    }

    @Bean
    public UserService userService(Map<UUID, User> userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public SessionService sessionsService() {
        return new SessionService();
    }

    @Bean
    public Map<UUID, Link> linkRepository() {
        return new HashMap<>();
    }

    @Bean
    public Map<UUID, User> userRepository() {
        return new HashMap<>();
    }
}
