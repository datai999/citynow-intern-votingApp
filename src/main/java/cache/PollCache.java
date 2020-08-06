package cache;

import model.dto.comment.CommentPoll;
import model.dto.poll.Poll;
import model.dto.user.UserRole;

import java.util.ArrayList;
import java.util.List;

public class PollCache {


    List<Poll> topPollCache;
    int timeTopPollCache = 0;

    List<Poll> lsPollCache;

    private PollCache(){
    };
    private static class LazyHolder{
        public static final PollCache INSTANCE = new PollCache();
    }
    public static PollCache getInstance(){
        return PollCache.LazyHolder.INSTANCE;
    }


    int getTimeNow(){return (int) (System.currentTimeMillis()/1000);}


    public List<Poll> getTopPull(){
        return  (getTimeNow() > timeTopPollCache + 5*60)?null:topPollCache;
    }

    public void setTopPollCache(List<Poll> topPoll){
        this.topPollCache = topPoll;
        this.timeTopPollCache = (int) (System.currentTimeMillis()/1000);;
    }



    public List<Poll> getPoll(UserRole viewRole) {

        if (lsPollCache == null) return null;

        int timeNow = getTimeNow();

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

    public void setPollCache(List<Poll> lsPoll){
        lsPollCache = lsPoll;
    }

    public void clearPollCache(){
        lsPollCache.clear();
    }

    public void addComment(CommentPoll cmt){
        lsPollCache.forEach(poll ->  {if (poll.getId() == cmt.getPollId()) poll.addCmt(cmt);});
    }

}
