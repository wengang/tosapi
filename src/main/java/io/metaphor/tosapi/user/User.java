package io.metaphor.tosapi.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.List;

@Data
public class User {
    @Id
    private String id;

    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    private String username;
    private String password;
    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    private String email;
    private Date lastPasswordResetDate;
    private List<String> roles;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

