package model.service.dao.root;

import model.service.IRootService;
import model.service.dao.BaseDao;
import model.dto.UserAccount;
import model.dto.UserRole;
import model.service.impl.RootServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class UpdateRoleDao extends BaseDao {


    IRootService rootService;
    List<UserAccount> lsUser;
    List<Integer> idAdmins = new ArrayList<>();


    private UpdateRoleDao(){
        super();
        rootService = new RootServiceImpl();
        lsUser = rootService.getAllUser();
        getIdAdmins();
    }
    private static class LazyHolder{
        public static final UpdateRoleDao INSTANCE = new UpdateRoleDao();
    }
    public static UpdateRoleDao getInstance(){
        return UpdateRoleDao.LazyHolder.INSTANCE;
    }



    public boolean updateRole(String[] lsId){

        List<UserAccount> changeAdmin = getUserChangeRole(lsId);

        if (changeAdmin.size() < 1) return false;

        boolean isSuccess = push2DB(changeAdmin);
        if (isSuccess){
            lsUser.clear();
            lsUser = new ArrayList<>();
            lsUser = rootService.getAllUser();
            idAdmins.clear();
            getIdAdmins();
        }
        return isSuccess;
    }



    void getIdAdmins(){
        for (UserAccount user: lsUser){
            if (user.getRole() == UserRole.ADMIN.value){
                idAdmins.add(user.getId());
            }
        }
    }

    List<UserAccount> getUserChangeRole(String[] lsId){

        List<Integer> newIdAdmins = new ArrayList<>();
        for (String str : lsId) newIdAdmins.add(Integer.parseInt(str));


        List<UserAccount> changeAdmin = new ArrayList<>();

//        Admin -> Customer
        for (int currentId : idAdmins) {
            if (!newIdAdmins.contains(currentId)) {
                changeAdmin.add(new UserAccount(currentId, UserRole.CUSTOMER.value));
            }
        }


//        Customer -> Admin
        for (int currentId : newIdAdmins) {
            if (!idAdmins.contains(currentId)) {
                changeAdmin.add(new UserAccount(currentId, UserRole.ADMIN.value));
            }
        }
        return changeAdmin;
    }

    boolean push2DB(List<UserAccount> changeAdmin){

        StringBuilder param = new StringBuilder();
        for (UserAccount user: changeAdmin){
            param.append(String.format("(%d,%d),", user.getId(), user.getRole()));
        }
        param.deleteCharAt(param.length()-1);

        String query = "INSERT INTO user (id,role) VALUES " + param + " ON DUPLICATE KEY UPDATE id = VALUES(id),role = VALUES(role)";

        int count = execute(query,null);
        return count>0;
    }


}