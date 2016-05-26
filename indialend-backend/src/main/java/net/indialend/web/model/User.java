package net.indialend.web.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "APP_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer user_id;

    @NotEmpty
    @Column(name = "PHONE", unique = true, nullable = false)
    private String phone;

    @NotEmpty
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotEmpty
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "GENDER", nullable = false)
    private String gender;

    @NotEmpty
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "LONGITUTE")
    private double longitute;

    @Column(name = "LATITUTE")
    private double latitude;

    @Column(name = "SERVICE")
    private boolean service;

    @Column(name = "ACTIVE" )
    private boolean active = true;

    @Column(name = "GCM_TOKEN")
    private String gcmToken;

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
    }

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<UserLocations> userLocations;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isService() {
        return service;
    }

    public void setService(boolean service) {
        this.service = service;
    }

    public List<UserLocations> getUserLocations() {
        return userLocations;
    }

    public void setUserLocations(List<UserLocations> userLocations) {
        this.userLocations = userLocations;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        if (phone == null) {
            if (other.phone != null) {
                return false;
            }
        } else if (!phone.equals(other.phone)) {
            return false;
        }
        return true;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "User{ phone=" + phone + ", email=" + email + ", name=" + name + ", gender=" + gender + ", longitute=" + longitute + ", latitude=" + latitude + '}';
    }

}
