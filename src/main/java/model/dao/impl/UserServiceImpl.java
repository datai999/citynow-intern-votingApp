package model.dao.impl;

import model.dao.service.user.*;
import model.dto.comment.CommentPoll;
import model.dto.poll.Poll;
import model.dto.user.UserAccount;
import model.dao.IUserService;
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
    public List<Poll> getPollBeforeEnd(int timeNow) {
        return GetPollService.getInstance().getPollBeforeEnd(timeNow);
    }

    @Override
    public boolean vote(Vote vote) {
        return VoteService.getInstance().vote(vote);
    }

    @Override
    public void getVoteOptionByUserId(List<Poll> lsPoll, int userId) {
         GetVoteService.getInstance().getVoteOptionByUserId(lsPoll, userId);
    }

    @Override
    public List<Poll> getTopVote(int timeLeft, int timeRight) {
        return GetVoteService.getInstance().getTopVote(timeLeft, timeRight);
    }

    @Override
    public boolean comment(CommentPoll comment) {
        return CommentService.getInstance().comment(comment);
    }

    @Override
    public void getCommentByPollId(List<Poll> lsPoll) {
        GetCommentService.getInstance().getCommentByPollId(lsPoll);
    }
}
