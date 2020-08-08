package cache;

import model.dto.comment.CommentPoll;
import model.dto.poll.Poll;
import model.dto.user.UserRole;
import model.dto.vote.Vote;

import java.util.List;

public interface IPollCache {

    List<Poll> getPoll(UserRole viewRole);
    void setPollCache(List<Poll> lsPoll);
    void clearPollCache();

    void pushVote(Vote vote);

    void pushComment(CommentPoll cmt);
}
