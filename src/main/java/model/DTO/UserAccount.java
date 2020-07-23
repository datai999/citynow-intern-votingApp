package model.DTO;

import setting.IConst;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserAccount {

    private int id;
    private int role;
    private String username;
    private String password;
    private String email;
    private String fullName;

    @Override
    public String toString(){
        return String.format ("UserAccount[%d,%d,%s,%s,%s,%s]",id,role,username,password,email,fullName);
    }

    public UserAccount(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        role = rs.getInt("role");
        username = rs.getString("username");
        password = rs.getString("password");
        password = "";
        email = rs.getString("email");
        fullName = rs.getString("fullName");
    }

    public UserAccount(String username, String password, String email, String fullName){
        this.role = IConst.UserRole.CUSTOMER;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object[] getArrObj(){
        return new Object[]{role, username, password, email, fullName};
    }
}
