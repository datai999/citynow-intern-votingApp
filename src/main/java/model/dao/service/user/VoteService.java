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

    public boolean vote(Vote vote){

        String queryInsert = "INSERT INTO vote (pollOptionId, userId, timeCreate) VALUES (?, ?, ?)";
        List<Object> paramsInsert = Arrays.asList(vote.getArrObj());
        int countInsert = execute(queryInsert, paramsInsert);

        String query = "SELECT * FROM vote WHERE pollOptionId = ?";;
        List<Object> params = Arrays.asList(new Object[]{vote.getPollOptionId()});
        AtomicInteger count = new AtomicInteger();
        execute(query, params, rs -> {
            try {
                if (rs.next()) {
                    count.getAndIncrement();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        String queryUpdate = "UPDATE poll_option SET count = ? WHERE id = ? ";
        List<Object> paramsUpdate = Arrays.asList(new Object[]{count.get(),vote.getPollOptionId()});
        int countUpdate = execute(queryUpdate, paramsUpdate);

        return countInsert>0 && countUpdate>0;
    }
}
