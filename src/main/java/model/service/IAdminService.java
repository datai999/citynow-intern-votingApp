package model.service;


public interface IAdminService {

    boolean createQuestion(int userId, String strDeadline, String tittle, String question, String[] options);
}
