package model.dao;

import model.dtO.user.UserAccount;

import java.util.List;

public interface IRootService{

    List<UserAccount> getAllUser();

    boolean updateRole(String[] lsId);

    UserAccount getUserByUsername(String username);
}
