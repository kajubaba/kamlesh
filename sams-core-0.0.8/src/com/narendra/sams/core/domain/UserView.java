package com.narendra.sams.core.domain;

public class UserView {
    private String firstName;
    private Long id;
    private String lastName;
    private String middleName;
    private String password;
    private String userName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.firstName);
        if (!(this.lastName == null || this.lastName.isEmpty())) {
            sb.append(" ").append(this.lastName);
        }
        return sb.toString();
    }
}
