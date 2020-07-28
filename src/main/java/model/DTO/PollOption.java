package model.dto;

public class PollOption {

    int id;
    int pollId;
    int count = 0;
    String option;


    @Override
    public String toString(){
        return String.format ("Option[%d,%d,%d,%s]",id,pollId,count,option);
    }

    public PollOption(int pollId, String option) {
        this.pollId = pollId;
        this.option = option;
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int id){
        this.pollId = id;
    }

    public String getOption() {
        return option;
    }
}
