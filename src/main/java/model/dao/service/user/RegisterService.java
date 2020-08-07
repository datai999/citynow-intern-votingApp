package model.dao.service.user;

import database.CloudinaryConnection;
import model.dao.service.BaseDao;
import model.dto.user.UserAccount;

import java.util.Arrays;
import java.util.List;

public class RegisterService extends BaseDao {

    private RegisterService(){}
    private static class LazyHolder{
        public static final RegisterService INSTANCE = new RegisterService();
    }
    public static RegisterService getInstance(){
        return RegisterService.LazyHolder.INSTANCE;
    }


    public boolean register(UserAccount user){

        CloudinaryConnection cloud = new CloudinaryConnection();
        String urlAvatar = cloud.upload(user.getUrlAvatar());
        if (urlAvatar == null) return false;
        user.setUrlAvatar(urlAvatar);

        String query = "INSERT user (role, username, password, email, fullName, urlAvatar) values (?, ?, ?, ?, ?, ?)";

        List<Object> params = Arrays.asList(user.getArrObj());

        int count = execute(query, params);

        return count>0;
    }

}
