package model.dao.service.root;

import model.dao.IRootService;
import model.dao.service.BaseDao;
import model.dtO.user.UserAccount;
import model.dtO.user.UserRole;
import model.dao.impl.RootServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class UpdateRoleService extends BaseDao {


    IRootService rootService;
    List<UserAccount> lsUser;
    List<Integer> idAdmins = new ArrayList<>();


    private UpdateRoleService(){
        super();
        rootService = new RootServiceImpl();
        lsUser = rootService.getAllUser();
        getIdAdmins();
    }
    private static class LazyHolder{
        public static final UpdateRoleService INSTANCE = new UpdateRoleService();
    }
    public static UpdateRoleService getInstance(){
        return UpdateRoleService.LazyHolder.INSTANCE;
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

        if (lsId != null)
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