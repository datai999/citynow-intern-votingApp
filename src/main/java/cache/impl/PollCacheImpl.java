package cache.impl;

import cache.IPollCache;
import model.dao.service.user.CommentService;
import model.dto.comment.CommentPoll;
import model.dto.poll.Poll;
import model.dto.user.UserRole;

import java.util.ArrayList;
import java.util.List;

public class PollCacheImpl implements IPollCache {

    List<Poll> lsPollCache;

    private PollCacheImpl(){};
    private static class LazyHolder{
        public static final PollCacheImpl INSTANCE = new PollCacheImpl();
    }
    public static PollCacheImpl getInstance(){
        return PollCacheImpl.LazyHolder.INSTANCE;
    }

    @Override
    public List<Poll> getPoll(UserRole viewRole) {

        if (lsPollCache == null) return null;

        int timeNow = (int) (System.currentTimeMillis()/1000);

        List<Poll> result = new ArrayList<>();

        for (Poll poll: lsPollCache) {
            if (poll.getTimeStart() <= timeNow){
                if (poll.getTimeEnd() >= timeNow - 3*24*60*60){
                    if (poll.getViewRole().value <= viewRole.value){
                        result.add(poll);
                    }
                }
                else lsPollCache.remove(poll);
            }
        }

        return result;
    }

    @Override
    public void setPollCache(List<Poll> lsPoll) {
        if (lsPollCache !=null) lsPollCache.clear();
        lsPollCache = lsPoll;
    }

    @Override
    public void clearPollCache() {
        lsPollCache.clear();
    }

    @Override
    public void pushComment(CommentPoll cmt) {
        lsPollCache.forEach(poll ->  {if (poll.getId() == cmt.getPollId()) poll.addCmt(cmt);});
    }
}
