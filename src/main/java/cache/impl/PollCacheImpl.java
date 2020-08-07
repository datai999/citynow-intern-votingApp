package cache.impl;

import cache.IPollCache;
import model.dtO.comment.CommentPoll;
import model.dtO.poll.Poll;
import model.dtO.user.UserRole;
import model.dtO.vote.Vote;

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

    @Override
    public void pushVote(Vote vote) {
        if (lsPollCache == null) return;
        for (Poll poll: lsPollCache){
            if (poll.getId() == vote.getPollId()){
                poll.setNumBallot(poll.getNumBallot() + 1);
                for (int i = 0; i < 4; i++){
                    if (poll.getOption(i).getId() == vote.getPollOptionId())
                        poll.getOption(i).setCount(poll.getOption(i).getCount()+1);
                }
                break;
            }
        }
    }
}
