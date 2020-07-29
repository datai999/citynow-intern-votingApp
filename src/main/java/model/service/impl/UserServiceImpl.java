package model.service.impl;

import model.dto.UserAccount;
import model.service.IUserService;
import model.service.dao.user.LoginDao;
import model.service.dao.user.RegisterDao;

public class UserServiceImpl implements IUserService {

    @Override
    public UserAccount login(String username, String password) {
        return LoginDao.getInstance().login(username, password);
    }

    @Override
    public boolean register(UserAccount user) {
        return RegisterDao.getInstance().register(user);
    }
}
