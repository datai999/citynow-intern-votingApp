package model.service.impl;

import model.service.IRootService;
import model.service.dao.root.GetUserDao;
import model.service.dao.root.UpdateRoleDao;
import model.dto.UserAccount;

import java.util.List;

public class RootServiceImpl implements IRootService {

    @Override
    public List<UserAccount> getAllUser() {
        return GetUserDao.getInstance().getAllUser();
    }

    @Override
    public boolean updateRole(String[] lsId) {
        return UpdateRoleDao.getInstance().updateRole(lsId);
    }

    @Override
    public UserAccount getUserByUsername(String username) {
        return GetUserDao.getInstance().getUserByUsername(username);
    }
}
