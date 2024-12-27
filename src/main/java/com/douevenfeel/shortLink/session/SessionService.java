package com.douevenfeel.shortLink.session;

import com.douevenfeel.shortLink.user.User;

public class SessionService {
    private User currentUser;

    public SessionService() {
    }

    public User getCurrentUser() {
        return currentUser;
    }


    public void start(User user) {
        System.out.println(user);
        currentUser = user;
    }

    public void end() {
        currentUser = null;
    }
}
