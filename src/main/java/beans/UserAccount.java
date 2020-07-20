package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserAccount {

    private int id;
    private int role;
    private String username;
    private String password;
    private String email;
    private String fullName;

    public UserAccount(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        role = rs.getInt("role");
        username = rs.getString("username");
        password = rs.getString("password");
        email = rs.getString("email");
        fullName = rs.getString("fullName");
    }
}
