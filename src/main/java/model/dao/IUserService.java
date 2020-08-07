package model.dao;

import model.dtO.comment.CommentPoll;
import model.dtO.poll.Poll;
import model.dtO.user.UserAccount;
import model.dtO.user.UserRole;
import model.dtO.vote.Vote;

import java.util.List;

public interface IUserService {

    UserAccount login(String username, String password);

    boolean register(UserAccount user);

    List<Poll> getPollBeforeEnd(int timeNow, UserRole viewRole);

    boolean vote(Vote vote);

    /*
    * return 0 if user didn't vote
    * return Poll Option Id ip user voted
    * */
    void getVoteOptionByUserId(List<Poll> lsPoll, int userId);

    List<Poll> getTopVote(int timeLeft, int timeRight);

    boolean comment(CommentPoll comment);

    void getCommentByPollId(List<Poll> lsPoll);
}
