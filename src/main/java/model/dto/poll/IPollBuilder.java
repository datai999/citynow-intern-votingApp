package model.dto.poll;

import model.dto.user.UserRole;

public interface IPollBuilder {

    IPollBuilder buildBase(int userId, int timeEnd, String title, String question, String[] options);
    IPollBuilder buildTimeStart(int timeStart);
    IPollBuilder buildViewRole(UserRole viewRole);
    IPollBuilder buildVoteRole(UserRole voteRole);
    IPollBuilder buildMinBallot(int minBallot);
    IPollBuilder buildMaxBallot(int maxBallot);
    IPollBuilder buildNumBallot(int numBallot);

    Poll build();
}
