package model.dto;

public enum UserRole {

    ROOT(3),
    ADMIN(2),
    CUSTOMER(1);

    public int value;

    UserRole(int i) {
        this.value = i;
    }
}
