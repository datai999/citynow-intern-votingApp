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

    public List<Poll> getPollBeforeEnd(int day){

        List<Poll> lsPoll = new ArrayList<>();

        int timeNow = (int) (System.currentTimeMillis()/1000);

        String query = "SELECT * FROM poll ";
                query += "INNER JOIN user ON poll.userId = user.id ";
                query += "INNER JOIN poll_option ON poll.id = poll_option.pollId ";
                query += "WHERE timeStart>= ? OR timeEnd >= ?";
        List<Object> params = Arrays.asList(new Object[]{timeNow, timeNow - day*24*60*60});

        execute(query, params, rs ->{
            try {
                while (rs.next()) {
                    Poll poll = new Poll(rs);
                    poll.setCreator(new UserAccount(rs));

                    List<PollOption> lsPollOption = new ArrayList<>();
                    lsPollOption.add(new PollOption(rs));

                    for (int i=0; i<3; i++){
                        rs.next();
                        lsPollOption.add(new PollOption(rs));
                    }
                    poll.setOptions(lsPollOption);

                    lsPoll.add(poll);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        return lsPoll;
    }
}
