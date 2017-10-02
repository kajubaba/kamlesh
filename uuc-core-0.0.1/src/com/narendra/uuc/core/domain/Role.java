package com.narendra.uuc.core.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Role {
    private Boolean active;
    private Application application;
    private User createdBy;
    private Date createdDate;
    private String description;
    private Long id;
    private User modifiedBy;
    private Date modifiedDate;
    private String name;
    private Set<Privilege> privileges;
    private Set<User> users;

    public enum RoleListSortOn {
        ROLE_NAME,
        ROLE_STATUS
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Privilege> getPrivileges() {
        return this.privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public User getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Application getApplication() {
        return this.application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<User> getActiveUsers() {
        List<User> aciveUsers = new ArrayList();
        if (this.users != null) {
            for (User user : this.users) {
                if (user.getActive() != null && user.getActive().booleanValue()) {
                    aciveUsers.add(user);
                }
            }
        }
        return aciveUsers;
    }

    public void copyEditableProperties(Role role) {
        this.name = role.name;
        this.active = role.active;
        this.description = role.description;
    }

    public int hashCode() {
        return (this.id == null ? 0 : this.id.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Role other = (Role) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
            return true;
        } else if (this.id.equals(other.id)) {
            return true;
        } else {
            return false;
        }
    }
}
