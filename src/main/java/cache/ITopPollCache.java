package cache;

import model.dtO.poll.Poll;
import model.dtO.vote.Vote;

import java.util.List;

public interface ITopPollCache {

    List<Poll> getTopPoll();
    void setTopPollCache(List<Poll> topPoll);
    void pushVote(Vote vote);
}
