package model.service.dao.user;

import model.service.dao.BaseDao;
import model.service.IUserService;
import model.dto.UserAccount;

import java.util.Arrays;
import java.util.List;

public class RegisterDao extends BaseDao {

    private RegisterDao(){}
    private static class LazyHolder{
        public static final RegisterDao INSTANCE = new RegisterDao();
    }

    public static RegisterDao getInstance(){
        return RegisterDao.LazyHolder.INSTANCE;
    }


    public boolean register(UserAccount user){

        String query = "INSERT user (role, username, password, email, fullName) values (?, ?, ?, ?, ?)";

        List<Object> params = Arrays.asList(user.getArrObj());

        int count = execute(query, params);

        return count>0;
    }

}
