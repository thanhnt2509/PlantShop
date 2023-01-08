/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author Legion
 */
public class CategoryDAO {

    public static ArrayList<Category> getCategories() throws Exception {
        ArrayList<Category> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select CateID,CateName\n"
                    + "from Categories";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int cateID = table.getInt("CateID");
                    String cateName = table.getString("CateName");
                    Category cate = new Category(cateID, cateName);
                    list.add(cate);
                }
            }
            cn.close();
        }
        return list;
    }

    public static ArrayList<Category> getSearchedCategories(String information) throws Exception {
        ArrayList<Category> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select CateID,CateName\n"
                    + "from Categories\n"
                    + "where CateName LIKE ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%" + information + "%");
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int cateID = table.getInt("CateID");
                    String cateName = table.getString("CateName");
                    Category cate = new Category(cateID, cateName);
                    list.add(cate);
                }
            }
            cn.close();
        }
        return list;
    }
    
    public static Category getCategory(String cateid) throws Exception{
        Category cate = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null){
            String sql = "select CateName from Categories where CateID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, cateid);
            ResultSet rs = pst.executeQuery();
            if (rs != null && rs.next()){
                cate = new Category(Integer.parseInt(cateid), rs.getString("CateName"));
            }
            cn.close();
        }
        return cate;
    }
    
    public static boolean updateCateName(String catename, String cateid) throws Exception{
        boolean flag = false;
        Connection cn = DBUtils.makeConnection();
        if (cn != null){
            String sql = "update Categories set CateName=? where CateID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, catename);
            pst.setString(2, cateid);
            flag = pst.executeUpdate() == 1;
            cn.close();
        }
        return flag;
    }

    public static boolean addNewCategory(String catename) throws Exception {
        boolean flag = false;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "insert Categories(CateName)\n"
                    + "values (?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, catename);
            flag = pst.executeUpdate() == 1;
            cn.close();
        }
        return flag;
    }
    
    public static boolean checkCateName(String catename) throws Exception{
        boolean flag = false;
        Connection cn = DBUtils.makeConnection();
        if (cn != null){
            String sql = "select * from Categories where CateName=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, catename);
            ResultSet rs = pst.executeQuery();
            if (rs != null && rs.next()){
                flag = true;
            }
            cn.close();
        }
        return flag;
    }
}
