package model.dao.service.user;

import model.dao.service.BaseDao;
import model.dto.comment.CommentPoll;
import model.dto.poll.Poll;
import model.dto.user.UserAccount;

import java.sql.SQLException;
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

        lsPoll.forEach(Poll::initLsCmt);

        String query = "SELECT * FROM comment INNER JOIN user ON comment.userId = user.id WHERE pollId >= ?";
        List<Object> params = Arrays.asList(new Object[]{firstPollId});

        execute(query, params, rs ->{
            try {
                while (rs.next()) {
                    CommentPoll comment = new CommentPoll(rs);
                    comment.setCommentator(new UserAccount(rs));

                    for (Poll poll: lsPoll){
                        if (poll.getId() == comment.getPollId()) {
                            poll.addCmt(comment);
                            break;
                        }
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
