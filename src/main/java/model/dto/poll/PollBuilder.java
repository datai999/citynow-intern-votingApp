package model.dto.poll;

import model.dto.user.UserRole;

public class PollBuilder implements IPollBuilder{

    int userId;
    int timeStart;
    int timeEnd;
    UserRole viewRole;
    UserRole voteRole;
    int minBallot;
    int maxBallot;
    int numBallot;
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
    public IPollBuilder buildVoteRole(UserRole voteRole) {
        this.voteRole = voteRole;
        return this;
    }

    @Override
    public IPollBuilder buildMinBallot(int minBallot) {
        this.minBallot = minBallot;
        return this;
    }

    @Override
    public IPollBuilder buildMaxBallot(int maxBallot) {
        this.maxBallot = maxBallot;
        return this;
    }

    @Override
    public IPollBuilder buildNumBallot(int numBallot) {
        this.numBallot = numBallot;
        return this;
    }

    @Override
    public Poll build() {
        return new Poll(userId, timeStart, timeEnd, viewRole, voteRole, minBallot, maxBallot, numBallot, title, question, options);
    }
}
