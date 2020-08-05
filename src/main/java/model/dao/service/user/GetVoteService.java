package model.dao.service.user;

import model.dao.service.BaseDao;
import model.dto.poll.Poll;
import model.dto.user.UserAccount;
import model.dto.vote.Vote;

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



    public void getVoteOptionByUserId(List<Poll> lsPoll, int userId){

        int firstPollId = lsPoll.get(0).getId();

        String query = "SELECT * FROM vote WHERE pollId >= ? AND vote.userId = ?";
        List<Object> params = Arrays.asList(new Object[]{firstPollId, userId});

        execute(query, params, rs ->{
            try {
                int index = 0;
                Poll currentPoll = lsPoll.get(index);
                while (rs.next()) {
                    Vote vote = new Vote(rs);
                    while (index < lsPoll.size()){
                        if (vote.getPollId() == currentPoll.getId()){
                            currentPoll.setVotedId(vote.getPollOptionId());
                            break;
                        }
                        currentPoll.setVotedId(0);
                        currentPoll = lsPoll.get(++index);
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }

    public List<Poll> getTopVote(int timeLeft, int timeRight){

        List<Poll> lsPoll = new ArrayList<>();

        String query = "SELECT * FROM poll INNER JOIN user ON poll.userId = user.id ";
                query += "WHERE timeStart >= ? AND timeStart <= ? ORDER BY numBallot DESC LIMIT ?";

        List<Object> params = Arrays.asList(new Object[]{timeLeft, timeRight, 10});

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
