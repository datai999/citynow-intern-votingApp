package model.dto.vote;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Vote {

    int id;
    int pollOptionId;
    int userId;
    int timeCreate;

    @Override
    public String toString(){
        return String.format("Vote[%d,%d,%d,%d]",id,pollOptionId,userId,timeCreate);
    }

    public Vote(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        pollOptionId = rs.getInt("pollOptionId");
        userId = rs.getInt("userId");
        timeCreate = rs.getInt("timeCreate");

    }

    public Vote(int pollOptionId, int userId){
        this.pollOptionId = pollOptionId;
        this.userId = userId;
        this.timeCreate = (int) (System.currentTimeMillis()/1000);
    }

    public int getPollOptionId() {
        return pollOptionId;
    }

    public Object[] getArrObj(){
        return new Object[]{pollOptionId, userId, timeCreate};
    }
}
