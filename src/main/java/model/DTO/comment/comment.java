package model.dto.comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Comment {

    int id;
    int pollId;
    int userId;
    int timeCreate;
    int replyCommentId;
    String content;

    @Override
    public String toString(){
        return String.format("Comment[%d,%d,%d,%d,%s]",id,pollId,userId,timeCreate,replyCommentId,content);
    }

    public Comment(ResultSet rs) throws SQLException {
        this.id = rs.getInt("comment.id");
        this.pollId = rs.getInt("comment.pollId");
        this.userId = rs.getInt("comment.userId");
        this.timeCreate = rs.getInt("comment.timeCreate");
        this.replyCommentId = rs.getInt("replyCommentId");
        this.content = rs.getString("comment.content");
    }

    public Comment(int pollId, int userId, int timeCreate, int replyCommentId, String content){
        this.pollId = pollId;
        this.userId = userId;
        this.timeCreate = timeCreate;
        this.replyCommentId = replyCommentId;
        this.content = content;
    }

    public Object[] getArrObj(){
        return new Object[]{id,pollId,userId,timeCreate,replyCommentId,content};
    }
}
