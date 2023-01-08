/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Legion
 */
public class Account implements Serializable{
    private int accID;
    private String email;
    private String password;
    private String fullname;
    private String phone;
    private int status;
    private int role;
    
    public Account(){
        
    }

    public Account(int accID, String email, String password, String fullname, String phone, int status, int role) {
        this.accID = accID;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    public int getAccID() {
        return accID;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public int getStatus() {
        return status;
    }

    public int getRole() {
        return role;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
