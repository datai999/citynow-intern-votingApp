package model.dao;


import model.dao.impl.AdminDao;

public interface AdminService {

    static AdminService getInstance(){
        return AdminDao.getInstance();
    };

    boolean createQuestion(int userId, String strDeadline, String tittle, String question, String[] options);
}
