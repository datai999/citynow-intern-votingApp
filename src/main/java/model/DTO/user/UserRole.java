package model.dto.user;

public enum UserRole {

    ROOT(3),
    ADMIN(2),
    CUSTOMER(1),
    GUEST(0);

    public int value;

    UserRole(int i) {
        this.value = i;
    }

    public static UserRole fromInteger(int x) {
        switch(x) {
            default: return GUEST;
            case 1:
                return CUSTOMER;
            case 2:
                return ADMIN;
            case 3:
                return ROOT;
        }
    }
}
