package model.dao.impl;

import model.dao.IAdminService;
import model.dao.service.admin.CreatePollService;
import model.dto.poll.Poll;

public class AdminServiceImpl implements IAdminService {
    @Override
    public boolean createPoll(Poll poll) {
        return CreatePollService.getInstance().createPoll(poll);
    }
}
