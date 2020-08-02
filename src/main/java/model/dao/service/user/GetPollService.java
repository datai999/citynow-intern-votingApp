package model.dao.service.user;

import model.dto.poll.Poll;
import model.dao.service.BaseDao;
import model.dto.poll.PollOption;
import model.dto.user.UserAccount;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetPollService extends BaseDao {

    private GetPollService(){
        super();
    }
    private static class LazyHolder{
        public static final GetPollService INSTANCE = new GetPollService();
    }
    public static GetPollService getInstance(){
        return GetPollService.LazyHolder.INSTANCE;
    }

    public List<Poll> getPollBeforeEnd(int timeNow){

        List<Poll> lsPoll = new ArrayList<>();

        String query = "SELECT * FROM poll INNER JOIN user ON poll.userId = user.id WHERE timeEnd >= ?";
        List<Object> params = Arrays.asList(new Object[]{timeNow});

        execute(query, params, rs ->{
            try {
                while (rs.next()) {
                    Poll poll = new Poll(rs);
                    List<PollOption> lsPollOption = getOptionsByPollId(poll.getId());
                    poll.setOptions(lsPollOption);
                    poll.setCreator(new UserAccount(rs));
                    lsPoll.add(poll);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        return lsPoll;
    }

    List<PollOption> getOptionsByPollId(int pollId){
        List<PollOption> lsPollOption = new ArrayList<>();
        String query = "SELECT * FROM poll_option WHERE pollId = ?";

        List<Object> params = Arrays.asList(new Object[]{pollId});

        execute(query, params, rs ->{
            try {
                while (rs.next()) {
                    lsPollOption.add(new PollOption(rs));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        return lsPollOption;
    }
}
