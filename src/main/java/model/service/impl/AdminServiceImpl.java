package model.service.impl;

import model.service.IAdminService;
import model.service.dao.admin.CreateQuestionDao;

public class AdminServiceImpl implements IAdminService {
    @Override
    public boolean createQuestion(int userId, String strDeadline, String tittle, String question, String[] options) {
        return CreateQuestionDao.getInstance().createQuestion(userId, strDeadline, tittle, question, options);
    }
}
