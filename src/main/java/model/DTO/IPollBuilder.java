package model.dto;

public interface IPollBuilder {

    IPollBuilder buildBase(int userId, int timeEnd, String title, String question, String[] options);
    IPollBuilder buildTimeStart(int timeStart);
    IPollBuilder buildViewRole(UserRole viewRole);
    IPollBuilder buildMinVote(int minVote);
    IPollBuilder buildMaxVote(int maxVote);

    Poll build();
}
