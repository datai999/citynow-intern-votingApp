package model.dto;

public class PollBuilder implements IPollBuilder{

    int userId;
    int timeStart;
    int timeEnd;
    UserRole viewRole;
    int minVote;
    int maxVote;
    String title;
    String question;
    String[] options;

    @Override
    public IPollBuilder buildBase(int userId, int timeEnd, String title, String question, String[] options) {
        this.userId = userId;
        this.timeEnd = timeEnd;
        this.title = title;
        this.question = question;
        this.options = options;
        return this;
    }

    @Override
    public IPollBuilder buildTimeStart(int timeStart) {
        this.timeStart = timeStart;
        return this;
    }

    @Override
    public IPollBuilder buildViewRole(UserRole viewRole) {
        this.viewRole = viewRole;
        return this;
    }

    @Override
    public IPollBuilder buildMinVote(int minVote) {
        this.minVote = minVote;
        return this;
    }

    @Override
    public IPollBuilder buildMaxVote(int maxVote) {
        this.maxVote = maxVote;
        return this;
    }

    @Override
    public Poll build() {
        return new Poll(userId, timeStart, timeEnd, viewRole, minVote, maxVote, title, question, options);
    }
}
