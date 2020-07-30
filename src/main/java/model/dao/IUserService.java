package model.dao;

import model.dto.user.UserAccount;
import model.dto.vote.Vote;

import java.util.List;

public interface IUserService {

    UserAccount login(String username, String password);

    boolean register(UserAccount user);

    List<Object> getAllPoll();

    boolean vote(Vote vote);
}
