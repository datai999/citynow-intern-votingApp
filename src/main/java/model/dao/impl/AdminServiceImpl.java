package model.dao.impl;

import model.dao.IAdminService;
import model.dao.service.admin.CreatePollService;

public class AdminServiceImpl implements IAdminService {
    @Override
    public boolean createPoll(int userId, String strDeadline, String tittle, String question, String[] options) {
        return CreatePollService.getInstance().createPoll(userId, strDeadline, tittle, question, options);
    }
}
