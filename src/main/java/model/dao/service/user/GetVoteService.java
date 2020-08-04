package model.dao.service.user;

import model.dao.service.BaseDao;
import model.dto.poll.Poll;
import model.dto.user.UserAccount;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GetVoteService extends BaseDao{

    private GetVoteService(){
        super();
    }
    private static class LazyHolder{
        public static final GetVoteService INSTANCE = new GetVoteService();
    }
    public static GetVoteService getInstance(){
        return GetVoteService.LazyHolder.INSTANCE;
    }



    public int getVoteOptionByUserId(int pollId, int userId){

        AtomicInteger pollOptionId = new AtomicInteger(0);

        String query = "SELECT pollOptionId FROM poll_option ";
                query += "INNER JOIN vote ON poll_option.id = vote.pollOptionId ";
                query += "WHERE poll_option.pollId = ? AND vote.userId = ?";


        List<Object> params = Arrays.asList(new Object[]{pollId, userId});

        execute(query, params, rs ->{
            try {
                while (rs.next()) {
                    pollOptionId.set(rs.getInt("pollOptionId"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


        return pollOptionId.get();
    }

    public List<Poll> getTopVote(int timeLeft, int timeRight){

        List<Poll> lsPoll = new ArrayList<>();

        String query = "SELECT * FROM poll INNER JOIN user ON poll.userId = user.id ";
                query += "WHERE timeStart >= ? AND timeStart <= ? ORDER BY numBallot DESC LIMIT ?";

        List<Object> params = Arrays.asList(new Object[]{timeLeft, timeRight, 3});

        execute(query, params, rs ->{
            try {
                while (rs.next()) {
                    Poll poll = new Poll(rs);
                    poll.setCreator(new UserAccount(rs));
                    lsPoll.add(poll);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        return lsPoll;
    }
}
