package model.dto.poll;

import model.dto.user.UserRole;

public interface IPollBuilder {

    IPollBuilder buildBase(int userId, int timeEnd, String title, String question, String[] options);
    IPollBuilder buildTimeStart(int timeStart);
    IPollBuilder buildViewRole(UserRole viewRole);
    IPollBuilder buildVoteRole(UserRole voteRole);
    IPollBuilder buildMinVote(int minVote);
    IPollBuilder buildMaxVote(int maxVote);

    Poll build();
}
