package model.dao;

import model.dto.user.UserAccount;

import java.util.List;

public interface IUserService {

    UserAccount login(String username, String password);

    boolean register(UserAccount user);

    List<Object> getAllPoll();
}
