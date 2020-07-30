package model.dao.impl;

import model.dao.IRootService;
import model.dao.service.root.GetUserService;
import model.dao.service.root.UpdateRoleService;
import model.dto.user.UserAccount;

import java.util.List;

public class RootServiceImpl implements IRootService {

    @Override
    public List<UserAccount> getAllUser() {
        return GetUserService.getInstance().getAllUser();
    }

    @Override
    public boolean updateRole(String[] lsId) {
        return UpdateRoleService.getInstance().updateRole(lsId);
    }

    @Override
    public UserAccount getUserByUsername(String username) {
        return GetUserService.getInstance().getUserByUsername(username);
    }
}
