package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserAccount{

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
//        password = rs.getString("password");
        password = "";
        email = rs.getString("email");
        fullName = rs.getString("fullName");
    }

    public UserAccount(String username, String password, String email, String fullName){
        this.role = UserRole.CUSTOMER.value;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }

    public UserAccount(int id, int role){
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Object[] getArrObj(){
        return new Object[]{role, username, password, email, fullName};
    }
}
