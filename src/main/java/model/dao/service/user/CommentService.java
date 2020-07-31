package model.dao.service.user;

import model.dao.service.BaseDao;
import model.dto.comment.Comment;

import java.util.Arrays;
import java.util.List;

public class CommentService extends BaseDao {
    private CommentService(){};
    private static class LazyHolder{
        public static final CommentService INSTANCE = new CommentService();
    }
    public static CommentService getInstance(){
        return CommentService.LazyHolder.INSTANCE;
    }



    public boolean comment(Comment comment){

        String queryInsert = "INSERT INTO comment (pollId, userId, timeCreate, content) VALUES (?, ?, ?, ?)";
        List<Object> paramsInsert = Arrays.asList(comment.getArrObj());
        int count = execute(queryInsert, paramsInsert);

        return count > 0;
    }
}
