package model.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Poll {

    int id;
    int userId;
    int timeCreate;
    int timeDeadline;
    String title;
    String question;

    PollOption option1;
    PollOption option2;
    PollOption option3;
    PollOption option4;

    @Override
    public String toString(){
        String str = String.format ("Poll[%d,%d,%d,%d,%s,%s]\n",id,userId,timeCreate,timeDeadline,title,question);
        str += option1.toString() + "\n";
        str += option2.toString() + "\n";
        str += option3.toString() + "\n";
        str += option4.toString();
        return str;
    }

    public Poll(int userId, int timeDeadline, String title, String question, String[] options) {
        this.userId = userId;
        this.timeCreate = (int) (System.currentTimeMillis()/1000);
        this.timeDeadline = timeDeadline;
        this.title = title;
        this.question = question;
        option1 = new PollOption(this.id, options[0]);
        option2 = new PollOption(this.id, options[1]);
        option3 = new PollOption(this.id, options[2]);
        option4 = new PollOption(this.id, options[3]);
    }

    public PollOption getOption(int index) {
        switch (index){
            default: return option1;
            case 1: return option2;
            case 2: return option3;
            case 3: return option4;
        }
    }

    public void setId(int id) {
        this.id = id;
        this.option1.setPollId(id);
        this.option2.setPollId(id);
        this.option3.setPollId(id);
        this.option4.setPollId(id);
    }

    public Object[] getArrObj(){
        return new Object[]{userId, timeCreate, timeDeadline, title, question};
    }
    public Object[] getArrOptionObj() {

        List<Object> lsObj = new ArrayList<>();
        for (int i=0; i<4; i++){
            lsObj.add(getOption(i).getPollId());
            lsObj.add(getOption(i).getOption());
        }
        return lsObj.toArray();
    }
}
