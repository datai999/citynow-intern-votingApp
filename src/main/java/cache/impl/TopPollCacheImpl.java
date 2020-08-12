package cache.impl;

import cache.BasePollCacheImpl;
import model.dto.poll.Poll;
import model.dto.user.UserRole;

import java.util.List;

public class TopPollCacheImpl extends BasePollCacheImpl {

    int timeCache = 0;

    private TopPollCacheImpl(){
    };

    private static class LazyHolder{
        public static final TopPollCacheImpl INSTANCE = new TopPollCacheImpl();
    }
    public static TopPollCacheImpl getInstance(){
        return TopPollCacheImpl.LazyHolder.INSTANCE;
    }

    @Override
    public List<Poll> getPoll(UserRole viewRole){
        int timeNow = (int) (System.currentTimeMillis()/1000);
        if (timeNow < timeCache + 60) return super.getPoll(viewRole);
        timeCache = timeNow;
        super.clearPollCache();
        return lsPollCache;
    }

}
