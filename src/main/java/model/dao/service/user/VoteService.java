package model.dao.service.user;

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

    public boolean vote(Vote vote, int pollId){

        String queryInsert = "INSERT INTO vote (pollOptionId, userId, timeCreate) VALUES (?, ?, ?)";
        List<Object> paramsInsert = Arrays.asList(vote.getArrObj());
        int countInsert = execute(queryInsert, paramsInsert);


        String queryUpdatePoll = "UPDATE poll SET numBallot = numBallot + 1 WHERE id = ? ";
        List<Object> paramsUpdatePoll = Arrays.asList(new Object[]{pollId});
        int countUpdatePoll = execute(queryUpdatePoll, paramsUpdatePoll);

        String queryUpdateOption = "UPDATE poll_option SET count = count + 1 WHERE id = ? ";
        List<Object> paramsUpdateOption = Arrays.asList(new Object[]{vote.getPollOptionId()});
        int countUpdateOption = execute(queryUpdateOption, paramsUpdateOption);



        return countInsert>0 && countUpdatePoll>0 && countUpdateOption>0;
    }
}
