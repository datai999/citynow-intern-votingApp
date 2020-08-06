package cache;

import model.dto.poll.Poll;
import model.dto.vote.Vote;

import java.util.List;

public interface ITopPollCache {

    List<Poll> getTopPoll();
    void setTopPollCache(List<Poll> topPoll);
    void pushVote(Vote vote);
}
