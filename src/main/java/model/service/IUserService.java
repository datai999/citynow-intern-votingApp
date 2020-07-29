package model.service;

import model.dto.UserAccount;

public interface IUserService {

    UserAccount login(String username, String password);

    boolean register(UserAccount user);
}
