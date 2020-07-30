package model.dto.poll;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PollOption {

    int id;
    int pollId;
    int count = 0;
    String content;


    @Override
    public String toString(){
        return String.format ("Option[%d,%d,%d,%s]",id,pollId,count,content);
    }

    public PollOption(ResultSet rs) throws SQLException {
        id = rs.getInt("poll_option.id");
        pollId = rs.getInt("pollId");
        count = rs.getInt("count");
        content = rs.getString("content");
    }

    public PollOption(int pollId, String content) {
        this.pollId = pollId;
        this.content = content;
    }

    public int getId() {
        return id;
    }


    public int getPollId() {
        return pollId;
    }

    public void setPollId(int id){
        this.pollId = id;
    }

    public int getCount() {
        return count;
    }

    public String getContent() {
        return content;
    }
}
