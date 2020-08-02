package model.dto.comment;

import model.dto.poll.Poll;
import model.dto.user.UserAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Comment {

//    Same database
    int id;
    int pollId;
    int userId;

    public int getTimeCreate() {
        return timeCreate;
    }

    public String getContent() {
        return content;
    }

    int timeCreate;
    int replyCommentId;
    String content;

//    Extend database
    UserAccount commentByUser;
    Poll commentOnPoll;

    @Override
    public String toString(){
        return String.format("Comment[%d,%d,%d,%d,%d,%s]",id,pollId,userId,timeCreate,replyCommentId,content);
    }

    public UserAccount getCommentByUser() {
        return commentByUser;
    }

    public void setCommentByUser(UserAccount commentByUser) {
        this.commentByUser = commentByUser;
    }

    public Comment(ResultSet rs) throws SQLException {
        this.id = rs.getInt("comment.id");
        this.pollId = rs.getInt("comment.pollId");
        this.userId = rs.getInt("comment.userId");
        this.timeCreate = rs.getInt("comment.timeCreate");
        this.replyCommentId = rs.getInt("replyCommentId");
        this.content = rs.getString("comment.content");
    }

    public Comment(int pollId, int userId, int replyCommentId, String content){
        this.pollId = pollId;
        this.userId = userId;
        this.timeCreate =  (int) (System.currentTimeMillis()/1000);
        this.replyCommentId = replyCommentId;
        this.content = content;
    }

    public Object[] getArrObj(){
        return new Object[]{pollId,userId,timeCreate,replyCommentId,content};
    }

}
