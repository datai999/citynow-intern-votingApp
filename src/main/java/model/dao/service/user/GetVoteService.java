package model.dao.service.user;

import model.dao.service.BaseDao;
import model.dtO.poll.Poll;
import model.dtO.user.UserAccount;
import model.dtO.vote.Vote;

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



    public void getVoteOptionByUserId(List<Poll> lsPoll, int userId){

        int firstPollId = lsPoll.get(0).getId();

        lsPoll.forEach(poll -> poll.setVotedId(0));

        String query = "SELECT * FROM vote WHERE pollId >= ? AND vote.userId = ?";
        List<Object> params = Arrays.asList(new Object[]{firstPollId, userId});

        execute(query, params, rs ->{
            try {
                while (rs.next()) {
                    Vote vote = new Vote(rs);
                    for(Poll poll:lsPoll){
                        if (vote.getPollId() == poll.getId()){
                            poll.setVotedId(vote.getPollOptionId());
                            break;
                        }
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
