package model.dao.service.user;

import model.dao.service.BaseDao;
import model.dto.poll.Poll;
import model.dto.poll.PollOption;
import model.dto.user.UserAccount;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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



    public List<Object> getVoteByUserId(int timeEnd, int minPollId, int userId){

        List<Integer> lsPollId = new ArrayList<>();
        List<Integer> lsPollOptionId = new ArrayList<>();

        String query = "SELECT pollId,pollOptionId FROM poll_option ";
                query += "INNER JOIN vote ON poll_option.id = vote.pollOptionId ";
                query += "INNER JOIN poll ON poll_option.pollId = poll.id ";
                query += "WHERE poll.timeEnd >= ? AND poll_option.pollId >= ? AND vote.userId = ?";


        List<Object> params = Arrays.asList(new Object[]{timeEnd, minPollId, userId});

        execute(query, params, rs ->{
            try {
                while (rs.next()) {
                    lsPollId.add(rs.getInt("pollId"));
                    lsPollOptionId.add(rs.getInt("pollOptionId"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        return Arrays.asList(new Object[]{lsPollId, lsPollOptionId});
    }

    public List<Object> getTopVote(int timeLeft, int timeRight){

        List<Poll> lsPoll = new ArrayList<>();
        List<UserAccount> lsUser = new ArrayList<>();

        String query = "SELECT * FROM poll INNER JOIN user ON poll.userId = user.id ";
                query += "WHERE timeStart >= ? AND timeStart <= ? ORDER BY numBallot DESC LIMIT ?";

        List<Object> params = Arrays.asList(new Object[]{timeLeft, timeRight, 10});

        execute(query, params, rs ->{
            try {
                while (rs.next()) {
                    lsPoll.add(new Poll(rs));
                    lsUser.add(new UserAccount(rs));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        return Arrays.asList(new Object[]{lsPoll,lsUser});
    }
}
