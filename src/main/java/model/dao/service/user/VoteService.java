package model.dao.service.user;

import cache.impl.TopPollCacheImpl;
import model.dao.service.BaseDao;
import model.dto.vote.Vote;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class VoteService extends BaseDao {

    private VoteService(){}
    private static class LazyHolder{
        public static final VoteService INSTANCE = new VoteService();
    }
    public static VoteService getInstance(){
        return VoteService.LazyHolder.INSTANCE;
    }

    public boolean vote(Vote vote){

        String queryInsert = "INSERT INTO vote (pollOptionId, pollId, userId, timeCreate) VALUES (?,?, ?, ?)";
        List<Object> paramsInsert = Arrays.asList(vote.getArrObj());
        int countInsert = execute(queryInsert, paramsInsert);

        if (countInsert < 1) return false;

        String queryUpdatePoll = "UPDATE poll SET numBallot = numBallot + 1 WHERE id = ? ";
        List<Object> paramsUpdatePoll = Arrays.asList(new Object[]{vote.getPollId()});
        int countUpdatePoll = execute(queryUpdatePoll, paramsUpdatePoll);

        if (countUpdatePoll < 1) return false;

        String queryUpdateOption = "UPDATE poll_option SET count = count + 1 WHERE id = ? ";
        List<Object> paramsUpdateOption = Arrays.asList(new Object[]{vote.getPollOptionId()});
        int countUpdateOption = execute(queryUpdateOption, paramsUpdateOption);

        if (countUpdateOption >0 ) TopPollCacheImpl.getInstance().pushVote(vote);

        return countUpdateOption>0;
    }
}
