package se348.cmis.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {
  @Id
  @GeneratedValue
  private Integer userId;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String phone;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private UserType type;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserType getType() {
    return type;
  }

  public void setType(UserType type) {
    this.type = type;
  }

  public enum UserType {
    BANK,
    CUSTOMER
  }

}
