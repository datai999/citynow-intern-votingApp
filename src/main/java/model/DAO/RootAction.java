package model.DAO;

import database.MySQLConnection;
import model.DTO.UserAccount;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RootAction {

    List<UserAccount> lsUser;
    List<Integer> idAdmins = new ArrayList<>();


    private RootAction(){
        lsUser = new ArrayList<>();
        getAllUser(lsUser);
        getIdAdmins();
    }
    private static class LazyHolder{
        public static final RootAction INSTANCE = new RootAction();
    }
    public static RootAction getInstance(){
        return RootAction.LazyHolder.INSTANCE;
    }




    public List<UserAccount> getAllUser(){
        return lsUser;
    }

    public boolean updateRole(String[] lsId){

        List<UserAccount> changeAdmin = getUserChangeRole(lsId);
        if (changeAdmin.size() < 1) return true;

        boolean isSuccess = push2DB(changeAdmin);
        if (isSuccess){
            lsUser.clear();
            getAllUser(lsUser);
        }
        return isSuccess;
    }




    void getAllUser(List<UserAccount>  lsUser){

        String query = "SELECT * FROM user";

        MySQLConnection database = new MySQLConnection();
        database.execute(query, null, rs ->{
            try {
                while (rs.next()) {
                    lsUser.add(new UserAccount(rs));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    void getIdAdmins(){
        for (UserAccount user: lsUser){
            if (user.getRole() == UserAccount.ADMIN){
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
                changeAdmin.add(new UserAccount(currentId, UserAccount.CUSTOMER));
            }
        }

//        Customer -> Admin
        for (int currentId : newIdAdmins) {
            if (!idAdmins.contains(currentId)) {
                changeAdmin.add(new UserAccount(currentId, UserAccount.ADMIN));
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

        MySQLConnection database = new MySQLConnection();
        int count = database.execute(query,null);

        return count>0;
    }


}
