package model.dto.poll;

import model.dao.service.user.CommentService;
import model.dto.comment.CommentPoll;
import model.dto.user.UserAccount;
import model.dto.user.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Poll {

    int id;
    int userId;
    int timeCreate;
    int timeStart;
    int timeEnd;
    UserRole viewRole;
    UserRole voteRole;
    int minBallot;
    int maxBallot;
    int numBallot;
    String title;
    String question;


//    Extend database
    UserAccount creator;
    int votedId = 0;
    List<CommentPoll> lsCmt;

    PollOption option1;
    PollOption option2;
    PollOption option3;
    PollOption option4;

    @Override
    public String toString(){
        String str = String.format ("Poll[%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%s,%s]",id,userId,timeCreate,timeStart,timeEnd,viewRole.value,voteRole.value,minBallot,maxBallot,numBallot,title,question);
        String str1 = option1!=null?option1.toString():"Option 1 is null";
        String str2 = option2!=null?option2.toString():"Option 2 is null";
        String str3 = option3!=null?option3.toString():"Option 3 is null";
        String str4 = option4!=null?option4.toString():"Option 4 is null";
        return str + "\n" + str1 + "\n" + str2 + "\n" + str3 + "\n" + str4;
    }

    public Poll(ResultSet rs) throws SQLException {
        id = rs.getInt("poll.id");
        userId = rs.getInt("poll.userId");
        timeCreate = rs.getInt("poll.timeCreate");
        timeStart = rs.getInt("timeStart");
        timeEnd = rs.getInt("timeEnd");
        viewRole = UserRole.fromInteger(rs.getInt("viewRole"));
        voteRole = UserRole.fromInteger(rs.getInt("voteRole"));
        minBallot = rs.getInt("minBallot");
        maxBallot = rs.getInt("maxBallot");
        numBallot = rs.getInt("numBallot");
        title = rs.getString("title");
        question = rs.getString("question");
    }

    public Poll(int userId, int timeStart, int timeEnd, UserRole viewRole, UserRole voteRole, int minBallot, int maxBallot, int numBallot, String title, String question, String[] options) {
        this.userId = userId;
        this.timeCreate = (int) (System.currentTimeMillis()/1000);
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.viewRole = viewRole;
        this.voteRole = voteRole;
        this.minBallot = 0;
        this.maxBallot = 9999;
        this.numBallot = 0;
        this.title = title;
        this.question = question;
        if (options == null){
            options = new String[]{null, null, null, null};
        }
        option1 = new PollOption(this.id, options[0]);
        option2 = new PollOption(this.id, options[1]);
        option3 = new PollOption(this.id, options[2]);
        option4 = new PollOption(this.id, options[3]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.option1.setPollId(id);
        this.option2.setPollId(id);
        this.option3.setPollId(id);
        this.option4.setPollId(id);
    }

    public int getUserId() {
        return userId;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public int getTimeEnd() {
        return timeEnd;
    }

    public UserRole getViewRole() {
        return viewRole;
    }

    public UserRole getVoteRole() {
        return voteRole;
    }

    public int getNumBallot() {
        return numBallot;
    }

    public void setNumBallot(int numBallot) {
        this.numBallot = numBallot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public PollOption getOption(int index) {
        switch (index){
            default: return option1;
            case 1: return option2;
            case 2: return option3;
            case 3: return option4;
        }
    }


    public void setOptions(List<PollOption> lsOption) {
        option1 = lsOption.get(0);
        option2 = lsOption.get(1);
        option3 = lsOption.get(2);
        option4 = lsOption.get(3);
    }

    public UserAccount getCreator() {
        return creator;
    }

    public void setCreator(UserAccount creator) {
        this.creator = creator;
    }

    public int getVotedId() {
        return votedId;
    }

    public void setVotedId(int votedId) {
        this.votedId = votedId;
    }

    public List<CommentPoll> getLsCmt() {
        return lsCmt;
    }

    public void addCmt(CommentPoll cmt) {
        if (lsCmt == null) initLsCmt();
        this.lsCmt.add(cmt);
    }

    public Object[] getArrObj(){
        return new Object[]{userId, timeCreate, timeStart, timeEnd, viewRole.value, voteRole.value, minBallot, maxBallot, numBallot, title, question};
    }
    public Object[] getArrOptionObj() {

        List<Object> lsObj = new ArrayList<>();
        for (int i=0; i<4; i++){
            lsObj.add(getOption(i).getPollId());
            lsObj.add(getOption(i).getContent());
        }
        return lsObj.toArray();
    }

    public void initLsCmt(){
        if (lsCmt == null)
            lsCmt = new ArrayList<>();
        lsCmt.clear();
    }
}
