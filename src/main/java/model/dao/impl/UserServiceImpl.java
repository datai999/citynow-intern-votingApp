package model.dao.impl;

import model.dao.service.user.*;
import model.dto.comment.Comment;
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
    public List<Object> getPollBeforeEnd(int timeNow) {
        return GetPollService.getInstance().getPollBeforeEnd(timeNow);
    }

    @Override
    public boolean vote(Vote vote, int pollId) {
        return VoteService.getInstance().vote(vote, pollId);
    }

    @Override
    public List<Object> getVoteByUserId(int timeEnd, int minPollId, int userId) {
        return GetVoteService.getInstance().getVoteByUserId(timeEnd, minPollId, userId);
    }

    @Override
    public List<Object> getTopVote(int timeLeft, int timeRight) {
        return GetVoteService.getInstance().getTopVote(timeLeft, timeRight);
    }

    @Override
    public boolean comment(Comment comment) {
        return CommentService.getInstance().comment(comment);
    }

    @Override
    public List<Comment> getCommentByPollId(int pollId) {
        return GetCommentService.getInstance().getCommentByPollId(pollId);
    }
}
