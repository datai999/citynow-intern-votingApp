package cache.impl;

import cache.ITopPollCache;
import model.dto.poll.Poll;
import model.dto.vote.Vote;

import java.util.List;

public class TopPollCacheImpl implements ITopPollCache {

    List<Poll> topPollCache;
    int timeTopPollCache = 0;

    private TopPollCacheImpl(){};
    private static class LazyHolder{
        public static final TopPollCacheImpl INSTANCE = new TopPollCacheImpl();
    }
    public static TopPollCacheImpl getInstance(){
        return TopPollCacheImpl.LazyHolder.INSTANCE;
    }

    int getTimeNow(){return (int) (System.currentTimeMillis()/1000);}

    @Override
    public List<Poll> getTopPoll() {
        return (getTimeNow() < timeTopPollCache + 5*60)?topPollCache:null;
    }

    @Override
    public void setTopPollCache(List<Poll> topPoll) {
        if (topPollCache != null) topPollCache.clear();
        this.topPollCache = topPoll;
    }

    @Override
    public void pushVote(Vote vote) {
        if (topPollCache == null) return;
        for (Poll poll: topPollCache){
            if (poll.getId() == vote.getPollId()){
                poll.setNumBallot(poll.getNumBallot() + 1);
                break;
            }
        }
    }
}
