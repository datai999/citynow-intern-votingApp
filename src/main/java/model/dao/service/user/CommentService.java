package model.dao.service.user;

import cache.impl.PollCacheImpl;
import model.dao.service.BaseDao;
import model.dtO.comment.CommentPoll;

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



    public boolean comment(CommentPoll comment){

        String queryInsert = "INSERT INTO comment (pollId, userId, timeCreate,replyCommentId, content) VALUES (?, ?, ?, ?, ?)";
        List<Object> paramsInsert = Arrays.asList(comment.getArrObj());
        int count = execute(queryInsert, paramsInsert);

        if (count >0 ) PollCacheImpl.getInstance().pushComment(comment);

        return count > 0;
    }
}
