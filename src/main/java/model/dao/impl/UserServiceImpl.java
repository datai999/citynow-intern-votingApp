package model.dao.impl;

import model.dao.service.user.VoteService;
import model.dto.poll.Poll;
import model.dto.user.UserAccount;
import model.dao.IUserService;
import model.dao.service.user.GetPollService;
import model.dao.service.user.LoginDaoService;
import model.dao.service.user.RegisterService;
import model.dto.vote.Vote;

import java.util.List;

public class UserServiceImpl implements IUserService {

    @Override
    public UserAccount login(String username, String password) {
        return LoginDaoService.getInstance().login(username, password);
    }

    @Override
    public boolean register(UserAccount user) {
        return RegisterService.getInstance().register(user);
    }

    @Override
    public List<Object> getAllPoll() {
        return GetPollService.getInstance().getAllPoll();
    }

    @Override
    public boolean vote(Vote vote) {
        return VoteService.getInstance().vote(vote);
    }
}
