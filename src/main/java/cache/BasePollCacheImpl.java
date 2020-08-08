package cache;

import model.dto.comment.CommentPoll;
import model.dto.poll.Poll;
import model.dto.user.UserRole;
import model.dto.vote.Vote;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePollCacheImpl implements IPollCache{

    protected List<Poll> lsPollCache = null;

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
        if (lsPollCache != null) lsPollCache.clear();
        this.lsPollCache = lsPoll;
    }

    @Override
    public void clearPollCache() {
        lsPollCache.clear();
    }

    @Override
    public void pushVote(Vote vote){

        if (lsPollCache == null) return;

        for (Poll poll: lsPollCache){
            if (poll.getId() == vote.getPollId()){
                poll.setNumBallot(poll.getNumBallot() + 1);
                if (poll.getOption(0) != null){
                    for (int i = 0; i < 4; i++){
                        if (poll.getOption(i).getId() == vote.getPollOptionId())
                            poll.getOption(i).setCount(poll.getOption(i).getCount()+1);
                    }
                }
                break;
            }
        }
    }

    @Override
    public void pushComment(CommentPoll cmt){};


}
