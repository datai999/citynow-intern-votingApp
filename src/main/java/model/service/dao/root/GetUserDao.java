package model.service.dao.root;

import model.dto.UserAccount;
import model.service.dao.BaseDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUserDao extends BaseDao {


    private GetUserDao(){
        super();
    }
    private static class LazyHolder{
        public static final GetUserDao INSTANCE = new GetUserDao();
    }
    public static GetUserDao getInstance(){
        return GetUserDao.LazyHolder.INSTANCE;
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
