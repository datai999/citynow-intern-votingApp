package model.DAO;

import database.MySQLConnection;
import model.DTO.UserAccount;

import java.util.Arrays;
import java.util.List;

public class RegisterAction {

    private RegisterAction(){}
    private static class LazyHolder{
        public static final RegisterAction INSTANCE = new RegisterAction();
    }

    public static RegisterAction getInstance(){
        return RegisterAction.LazyHolder.INSTANCE;
    }


    public boolean register(UserAccount user){

        String query = "INSERT user (role, username, password, email, fullName) values (?, ?, ?, ?, ?)";

        List<Object> params = Arrays.asList(user.getArrObj());

        MySQLConnection database = new MySQLConnection();
        int count = database.execute(query, params);

        return count>0;
    }

}
