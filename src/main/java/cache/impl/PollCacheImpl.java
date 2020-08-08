package cache.impl;

import cache.BasePollCacheImpl;
import cache.IPollCache;
import model.dto.comment.CommentPoll;
import model.dto.poll.Poll;
import model.dto.user.UserRole;
import model.dto.vote.Vote;

import java.util.ArrayList;
import java.util.List;

public class PollCacheImpl extends BasePollCacheImpl {

    private PollCacheImpl(){};
    private static class LazyHolder{
        public static final PollCacheImpl INSTANCE = new PollCacheImpl();
    }
    public static PollCacheImpl getInstance(){
        return PollCacheImpl.LazyHolder.INSTANCE;
    }


    @Override
    public void setPollCache(List<Poll> lsPoll) {
        super.setPollCache(lsPoll);
        sortPollByOutDate();
    }

    void sortPollByOutDate(){
        int currentTime = (int) (System.currentTimeMillis()/1000);
        int index = 0;
        for (Poll poll:lsPollCache){
            if (poll.getTimeEnd() > currentTime){
                index = lsPollCache.indexOf(poll);
                break;
            }
        }
        if (index < 1) return;
        List<Poll> result = new ArrayList<>(lsPollCache.subList(index, lsPollCache.size()));
        result.addAll(lsPollCache.subList(0,index));
        lsPollCache.clear();
        lsPollCache = result;
    }

    @Override
    public void pushComment(CommentPoll cmt) {
        lsPollCache.forEach(poll ->  {if (poll.getId() == cmt.getPollId()) poll.addCmt(cmt);});
    }
}
