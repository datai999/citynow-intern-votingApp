package model.dao.service.user;

import model.dao.service.BaseDao;
import model.dto.comment.CommentPoll;
import model.dto.poll.Poll;
import model.dto.user.UserAccount;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetCommentService extends BaseDao {
    private GetCommentService(){};
    private static class LazyHolder{
        public static final GetCommentService INSTANCE = new GetCommentService();
    }
    public static GetCommentService getInstance(){
        return GetCommentService.LazyHolder.INSTANCE;
    }

    public void getCommentByPollId(List<Poll> lsPoll){

        int firstPollId = lsPoll.get(0).getId();



        String query = "SELECT * FROM comment INNER JOIN user ON comment.userId = user.id WHERE pollId >= ?";
        List<Object> params = Arrays.asList(new Object[]{firstPollId});

        execute(query, params, rs ->{
            try {
                int index = 0;
                Poll currentPoll = lsPoll.get(index);
                List<CommentPoll> lsComment = new ArrayList<>();
                while (rs.next()) {
                    CommentPoll comment = new CommentPoll(rs);
                    comment.setCommentator(new UserAccount(rs));

                    while (comment.getPollId() != currentPoll.getId() && index < lsPoll.size()){
                        currentPoll = lsPoll.get(++index);
                    }
                    currentPoll.addCmt(comment);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
