package model.dto.vote;

import model.dto.user.UserAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Vvote {

//
    int id;
    int pollId;
    int pollOptionId;
    int userId;
    int timeCreate;

//    Extend database
    UserAccount creator;


    @Override
    public String toString(){
        return String.format("Vote[%d,%d,%d,%d,%d]",id,pollId,pollOptionId,userId,timeCreate);
    }

    public Vvote(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        pollId = rs.getInt("pollId");
        pollOptionId = rs.getInt("pollOptionId");
        userId = rs.getInt("userId");
        timeCreate = rs.getInt("timeCreate");

    }

    public Vvote(int pollId , int pollOptionId, int userId){
        this.pollId = pollId;
        this.pollOptionId = pollOptionId;
        this.userId = userId;
        this.timeCreate = (int) (System.currentTimeMillis()/1000);
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public int getPollOptionId() {
        return pollOptionId;
    }

    public UserAccount getCreator() {
        return creator;
    }

    public void setCreator(UserAccount creator) {
        this.creator = creator;
    }

    public Object[] getArrObj(){
        return new Object[]{pollOptionId, pollId, userId, timeCreate};
    }

}
