package cache;

import model.dtO.comment.CommentPoll;
import model.dtO.poll.Poll;
import model.dtO.user.UserRole;
import model.dtO.vote.Vote;

import java.util.List;

public interface IPollCache {

//    ViewPoll
    List<Poll> getPoll(UserRole viewRole);
    void setPollCache(List<Poll> lsPoll);
    void clearPollCache();

//    Comment
    void pushComment(CommentPoll cmt);

    void pushVote(Vote vote);
}
