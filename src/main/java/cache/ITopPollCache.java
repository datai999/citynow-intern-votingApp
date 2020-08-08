package cache;

import model.dto.poll.Poll;
import model.dto.user.UserRole;
import model.dto.vote.Vote;

import java.util.List;

public interface ITopPollCache {

    List<Poll> getTopPoll(UserRole viewRole);
    void setTopPollCache(List<Poll> topPoll);
    void pushVote(Vote vote);
}
