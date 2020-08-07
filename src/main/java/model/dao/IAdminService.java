package model.dao;


import model.dto.poll.Poll;

public interface IAdminService {

    boolean createPoll(Poll poll);
}
