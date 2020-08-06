package cache;

import model.dto.comment.CommentPoll;
import model.dto.poll.Poll;
import model.dto.user.UserRole;

import java.util.List;

public interface IPollCache {

//    ViewPoll
    List<Poll> getPoll(UserRole viewRole);
    void setPollCache(List<Poll> lsPoll);
    void clearPollCache();

//    Comment
    void pushComment(CommentPoll cmt);
}
