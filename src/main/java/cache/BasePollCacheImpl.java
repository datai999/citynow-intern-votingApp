package cache;

import model.dto.comment.CommentPoll;
import model.dto.poll.Poll;
import model.dto.user.UserRole;
import model.dto.vote.Vote;

import java.util.List;

public abstract class BasePollCacheImpl implements IPollCache{

    @Override
    public List<Poll> getPoll(UserRole viewRole) {
        return null;
    }

    @Override
    public void setPollCache(List<Poll> lsPoll) {

    }

    @Override
    public void clearPollCache() {

    }
}
