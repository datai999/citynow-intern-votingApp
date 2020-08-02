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
    public boolean vote(Vote vote, int pollId) {
        return VoteService.getInstance().vote(vote, pollId);
    }

    @Override
    public int getVoteOptionByUserId(int pollId, int userId) {
        return GetVoteService.getInstance().getVoteOptionByUserId(pollId, userId);
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
    public List<CommentPoll> getCommentByPollId(int pollId) {
        return GetCommentService.getInstance().getCommentByPollId(pollId);
    }
}
