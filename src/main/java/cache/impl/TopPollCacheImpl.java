package cache.impl;

import cache.ITopPollCache;
import model.dto.poll.Poll;
import model.dto.user.UserRole;
import model.dto.vote.Vote;

import java.util.ArrayList;
import java.util.List;

public class TopPollCacheImpl implements ITopPollCache {

    List<Poll> topPollCache;

    private TopPollCacheImpl(){};
    private static class LazyHolder{
        public static final TopPollCacheImpl INSTANCE = new TopPollCacheImpl();
    }
    public static TopPollCacheImpl getInstance(){
        return TopPollCacheImpl.LazyHolder.INSTANCE;
    }

    @Override
    public List<Poll> getTopPoll(UserRole viewRole) {

        if (topPollCache == null) return null;

        int timeNow = (int) (System.currentTimeMillis()/1000);

        List<Poll> result = new ArrayList<>();

        for (Poll poll: topPollCache) {
            if (poll.getTimeStart() <= timeNow){
                if (poll.getTimeEnd() >= timeNow - 3*24*60*60){
                    if (poll.getViewRole().value <= viewRole.value){
                        result.add(poll);
                    }
                }
                else topPollCache.remove(poll);
            }
        }

        return result;
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
