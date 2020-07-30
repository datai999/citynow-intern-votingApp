package model.dao;


public interface IAdminService {

    boolean createPoll(int userId, String strDeadline, String tittle, String question, String[] options);
}
