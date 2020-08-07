package model.dto.comment;


import model.dto.user.UserAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentPoll {

//    Same database
    int id;
    int pollId;
    int userId;

    int timeCreate;
    int replyCommentId;
    String content;

//    Extend database
    UserAccount commentator;

    @Override
    public String toString(){
        return String.format("Comment[%d,%d,%d,%d,%d,%s]",id,pollId,userId,timeCreate,replyCommentId,content);
    }


    public CommentPoll(ResultSet rs) throws SQLException {
        this.id = rs.getInt("comment.id");
        this.pollId = rs.getInt("comment.pollId");
        this.userId = rs.getInt("comment.userId");
        this.timeCreate = rs.getInt("comment.timeCreate");
        this.replyCommentId = rs.getInt("replyCommentId");
        this.content = rs.getString("comment.content");
    }

    public int getPollId() {
        return pollId;
    }

    public int getTimeCreate() {
        return timeCreate;
    }

    public String getContent() {
        return content;
    }

    public UserAccount getCommentator() {
        return commentator;
    }

    public void setCommentator(UserAccount commentator) {
        this.commentator = commentator;
    }

    public CommentPoll(int pollId, int userId, int replyCommentId, String content){
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
