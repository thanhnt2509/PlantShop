/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author Legion
 */
public class AccountDAO {

    public static ArrayList<Account> getAccounts() throws Exception {
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID,email,password,fullname,phone,status,role "
                    + "from Accounts";
            Statement st = cn.createStatement();
            ResultSet table = st.executeQuery(sql);
            if (table != null) {
                while (table.next()) {
                    int accid = table.getInt("accID");
                    String email = table.getString("email");
                    String password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("status");
                    int role = table.getInt("role");
                    Account acc = new Account(accid, email, password, fullname, phone, status, role);
                    list.add(acc);
                }
            }
            cn.close();
        }
        return list;
    }

    public static Account getAccount(int accID) throws Exception {
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select [accID],[email],[password],[fullname],[phone],[status],[role]\n"
                    + "from Accounts\n"
                    + "where accID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, accID);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                String email = table.getString("email");
                String password = table.getString("password");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                acc = new Account(accID, email, password, fullname, phone, status, role);
            }
            cn.close();
        }
        return acc;
    }
    
    public static boolean checkEmailAccount(String email) throws Exception {
        boolean flag = false;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select *\n"
                    + "from Accounts\n"
                    + "where email=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                flag = true;
            }
            cn.close();
        }
        return flag;
    }

    public static Account getAccount(String email, String password) throws Exception {
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID,email,password,fullname,phone,status,role\n"
                    + "from Accounts\n"
                    + "where email=? COLLATE Latin1_General_CS_AS AND password=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int accID = table.getInt("accID");
                email = table.getString("email");
                password = table.getString("password");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                acc = new Account(accID, email, password, fullname, phone, status, role);
            }
            cn.close();
        }
        return acc;
    }

    public static Account getAccount(String token) throws Exception {
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID,email,password,fullname,phone,status,role\n"
                    + "from Accounts\n"
                    + "where token=? and status=1";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, token);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int accID = table.getInt("accID");
                String email = table.getString("email");
                String password = table.getString("password");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                acc = new Account(accID, email, password, fullname, phone, status, role);
            }
            cn.close();
        }
        return acc;
    }

    public static ArrayList<Account> getSearchedAccount(String information) throws Exception {
        ArrayList<Account> list = new ArrayList<>();
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID,email,password,fullname,phone,status,role\n"
                    + "from Accounts\n"
                    + "where email LIKE ? or fullname LIKE ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%" + information + "%");
            pst.setString(2, "%" + information + "%");
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int accid = table.getInt("accID");
                String email = table.getString("email");
                String password = table.getString("password");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                acc = new Account(accid, email, password, fullname, phone, status, role);
                list.add(acc);
            }
            cn.close();
        }
        return list;
    }

    public static boolean updateAccountStatus(String email, int status) throws Exception {
        Connection cn = DBUtils.makeConnection();
        boolean flag = false;
        if (cn != null) {
            String sql = "update Accounts\n"
                    + "set status=?\n"
                    + "where email=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setString(2, email);
            int table = pst.executeUpdate();
            flag = table == 1;
            cn.close();
        }
        return flag;
    }

    public static boolean updateAccount(String email, String newPassword,
            String newFullname, String newPhone) throws Exception {
        Connection cn = DBUtils.makeConnection();
        boolean flag = false;
        if (cn != null) {
            String sql = "update Accounts\n"
                    + "set fullname=?, phone=?, password=?\n"
                    + "where email=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, newFullname);
            pst.setString(2, newPhone);
            pst.setString(3, newPassword);
            pst.setString(4, email);
            int table = pst.executeUpdate();
            flag = table == 1;
            cn.close();
        }
        return flag;
    }

    public static boolean insertAccount(String newEmail, String newPassword,
            String newFullname, String newPhone, int newSatus, int newRole) throws Exception {
        Connection cn = DBUtils.makeConnection();
        boolean flag = false;
        if (cn != null) {
            String sql = "insert Accounts(email,password,fullname,phone,status,role)\n"
                    + "values(?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, newEmail);
            pst.setString(2, newPassword);
            pst.setString(3, newFullname);
            pst.setString(4, newPhone);
            pst.setInt(5, newSatus);
            pst.setInt(6, newRole);
            int table = pst.executeUpdate();
            flag = table == 1;
            cn.close();
        }
        return flag;
    }

    public static boolean updateToken(String token, String email) throws Exception {
        Connection cn = DBUtils.makeConnection();
        boolean flag = false;
        if (cn != null) {
            String sql = "update Accounts\n"
                    + "set token=?\n"
                    + "where email=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, token);
            pst.setString(2, email);
            flag = pst.executeUpdate() == 1;
            cn.close();
        }
        return flag;
    }

}
