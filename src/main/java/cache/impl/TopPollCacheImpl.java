package cache.impl;

import cache.BasePollCacheImpl;

public class TopPollCacheImpl extends BasePollCacheImpl {

    private TopPollCacheImpl(){
    };

    private static class LazyHolder{
        public static final TopPollCacheImpl INSTANCE = new TopPollCacheImpl();
    }
    public static TopPollCacheImpl getInstance(){
        return TopPollCacheImpl.LazyHolder.INSTANCE;
    }


}
