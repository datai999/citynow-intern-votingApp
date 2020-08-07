package model.dao.service.root;

import model.dtO.user.UserAccount;
import model.dao.service.BaseDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUserService extends BaseDao {


    private GetUserService(){
        super();
    }
    private static class LazyHolder{
        public static final GetUserService INSTANCE = new GetUserService();
    }
    public static GetUserService getInstance(){
        return GetUserService.LazyHolder.INSTANCE;
    }



    public List<UserAccount> getAllUser(){

        List<UserAccount> lsUser = new ArrayList<>();

        String query = "SELECT * FROM user";

        execute(query, null, rs ->{
            try {
                while (rs.next()) {
                    lsUser.add(new UserAccount(rs));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        return lsUser;
    }


    public UserAccount getUserByUsername(String username){

        for(UserAccount user: getAllUser()){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
