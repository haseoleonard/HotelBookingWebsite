/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadhse140362.dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author haseo
 */
public class UsersDTO implements Serializable{
    private String email;
    private String name;
    private String address;
    private String phone;
    private Date createDate;
    private int role;

    public UsersDTO() {
    }

    public UsersDTO(String email, String name, String address, String phone,Date createDate ,int role) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.createDate=createDate;
        this.role = role;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
      
}
