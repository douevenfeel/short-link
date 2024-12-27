package com.douevenfeel.shortLink.operations;

public enum ConsoleOperation {
    /**
     * Создать короткую ссылку
     */
    CREATE_LINK,
    /**
     * Удалить короткую ссылку
     */
    DELETE_LINK,
    /**
     * Выход из приложения
     */
    EXIT_APP,
    /**
     * Авторизация в приложении
     */
    LOGIN,
    /**
     * Выход из сессии
     */
    LOGOUT,
    /**
     * Открыть короткую ссылку
     */
    OPEN_LINK,
    /**
     * Регистрация в приложении
     */
    REGISTER,
    /**
     * Получить все короткие ссылки пользователя
     */
    SHOW_ALL_MY_LINKS,
    /**
     * Обновить лимит на короткую ссылку
     */
    UPDATE_LINK_LIMIT
}
