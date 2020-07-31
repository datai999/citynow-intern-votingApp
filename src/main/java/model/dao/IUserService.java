package model.dao;

import model.dto.comment.Comment;
import model.dto.user.UserAccount;
import model.dto.vote.Vote;

import java.util.List;

public interface IUserService {

    UserAccount login(String username, String password);

    boolean register(UserAccount user);

    List<Object> getPollBeforeEnd(int timeNow);

    boolean vote(Vote vote, int pollId);

    List<Object> getVoteByUserId(int timeEnd, int minPollId, int userId);

    List<Object> getTopVote(int timeLeft, int timeRight);

    boolean comment(Comment comment);
}
