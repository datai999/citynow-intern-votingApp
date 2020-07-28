package model.dao;

import model.dto.UserAccount;

import java.util.List;

public interface RootService {

    List<UserAccount> getAllUser();
}
