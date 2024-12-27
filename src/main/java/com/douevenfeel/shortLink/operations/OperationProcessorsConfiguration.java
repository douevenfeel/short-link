package com.douevenfeel.shortLink.operations;

import com.douevenfeel.shortLink.link.LinkService;
import com.douevenfeel.shortLink.operations.processors.*;
import com.douevenfeel.shortLink.session.SessionService;
import com.douevenfeel.shortLink.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class OperationProcessorsConfiguration {

    @Bean
    public CreateUserProcessor createUserProcessor(UserService userService, SessionService sessionService) {
        return new CreateUserProcessor(userService, sessionService);
    }

    @Bean
    public LoginUserProcessor loginUserProcessor(Scanner scanner, UserService userService, SessionService sessionService) {
        return new LoginUserProcessor(scanner, userService, sessionService);
    }

    @Bean
    public LogoutUserProcessor logoutUserProcessor(SessionService sessionService) {
        return new LogoutUserProcessor(sessionService);
    }


    @Bean
    public OpenLinkProcessor visitLinkProcessor(Scanner scanner, LinkService linkService) {
        return new OpenLinkProcessor(scanner, linkService);
    }

    @Bean
    public CreateLinkProcessor createLinkProcessor(Scanner scanner, LinkService linkService, SessionService sessionService) {
        return new CreateLinkProcessor(scanner, linkService, sessionService);
    }

    @Bean
    public DeleteLinkProcessor deleteLinkProcessor(Scanner scanner, LinkService linkService, SessionService sessionsService) {
        return new DeleteLinkProcessor(scanner, linkService, sessionsService);
    }

    @Bean
    public UpdateLinkLimitProcessor updateLinkLimitProcessor(Scanner scanner, LinkService linkService) {
        return new UpdateLinkLimitProcessor(scanner, linkService);
    }

    @Bean
    public ShowAllMyLinksProcessor showAllMyLinksProcessor(SessionService sessionsService, LinkService linkService) {
        return new ShowAllMyLinksProcessor(sessionsService, linkService);
    }

    @Bean
    public ExitAppProcessor exitAppProcessor() {
        return new ExitAppProcessor();
    }
}
