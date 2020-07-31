package model.dao.service.user;

import model.dto.comment.Comment;

public class CommentService {
    private CommentService(){};
    private static class LazyHolder{
        public static final CommentService INSTANCE = new CommentService();
    }
    public static CommentService getInstance(){
        return CommentService.LazyHolder.INSTANCE;
    }

    public boolean comment(Comment comment){
        return false;
    }
}
