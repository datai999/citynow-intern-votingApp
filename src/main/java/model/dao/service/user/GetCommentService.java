package model.dao.service.user;

import model.dao.service.BaseDao;
import model.dto.comment.Comment;

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

    public List<Comment> getCommentByPollId(int pollId){

        List<Comment> lsComment = new ArrayList<>();

        String query = "SELECT * FROM comment WHERE pollId = ?";
        List<Object> params = Arrays.asList(new Object[]{pollId});

        execute(query, params, rs ->{
            try {
                while (rs.next()) {
                    lsComment.add(new Comment(rs));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        return lsComment;
    }
}
