/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Plant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author Legion
 */
public class PlantDAO {

    public static ArrayList<Plant> getPlants(String keyword, String searchby) throws Exception {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        cn = DBUtils.makeConnection();
        if (cn != null && searchby != null) {
            String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID', CateName\n"
                    + "from Plants join Categories on Plants.CateID=Categories.CateID\n";
            if (searchby.equalsIgnoreCase("byname")) {
                sql = sql + "where Plants.PName LIKE ?";
            } else {
                sql = sql + "where CateName LIKE ?";
            }
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("PID");
                    String name = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgpath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt("CateID");
                    String catename = rs.getString("CateName");
                    Plant plant = new Plant(id, name, price, imgpath, 
                            description, status, cateid, catename);
                    list.add(plant);
                }
            }
            cn.close();
        }
        return list;
    }

    public static Plant getPlant(int pid) throws Exception {
        Plant p = null;
        //Todo
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n"
                    + "from Plants, Categories\n"
                    + "where Plants.CateID=Categories.CateID and PID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, pid);
            ResultSet rs = pst.executeQuery();
            if (rs != null && rs.next()) {
                pid = rs.getInt("PID");
                String name = rs.getString("PName");
                int price = rs.getInt("price");
                String imgpath = rs.getString("imgPath");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateid = rs.getInt("cateid");
                String catename = rs.getString("CateName");
                p = new Plant(pid, name, price, imgpath, description, status, cateid, catename);
            }
            cn.close();
        }
        return p;
    }

    public static ArrayList<Plant> getSearchPlant(String info) throws Exception {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID', CateName\n"
                    + "from Plants join Categories on Plants.CateID=Categories.CateID\n"
                    + "where PName LIKE ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%" + info + "%");
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("PID");
                    String name = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgpath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt("CateID");
                    String catename = rs.getString("CateName");
                    Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                    list.add(plant);
                }
            }
        }
        return list;
    }

    public static boolean updatePlantStatus(int pid, int status) throws Exception {
        Connection cn = DBUtils.makeConnection();
        boolean flag = false;
        if (cn != null) {
            String sql = "update Plants\n"
                    + "set status=?\n"
                    + "where PID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setInt(2, pid);
            try {
                flag = pst.executeUpdate() == 1;
            } catch (SQLException e) {
                return false;
            }
            cn.close();
        }
        return flag;
    }

    public static boolean addNewPlant(String name, String price, String imgpath,
            String description, String cateid) throws Exception {
        boolean flag = false;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "insert into [dbo].[Plants]([PName],[price],[imgPath],[description],[status],[CateID])\n"
                    + "values(?,?,?,?,1,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, price);
            pst.setString(3, imgpath);
            pst.setString(4, description);
            pst.setString(5, cateid);
            try {
                flag = pst.executeUpdate() == 1;
            } catch (SQLException e) {
                return false;
            }
            cn.close();
        }
        return flag;
    }

    public static boolean checkPlantName(String name) throws Exception {
        boolean flag = false;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select * from Plants where PName=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs != null && rs.next()) {
                flag = true;
            }
            cn.close();
        }
        return flag;
    }

    public static boolean updatePlant(String pid, String pname, String price, String imgpath,
            String description, String cateid) throws Exception {
        boolean flag = false;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update Plants\n"
                    + "set [PName]=?,[price]=?,[imgPath]=?,[description]=?,[CateID]=?\n"
                    + "where PID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, pname);
            pst.setString(2, price);
            pst.setString(3, imgpath);
            pst.setString(4, description);
            pst.setString(5, cateid);
            pst.setString(6, pid);
            try{
                flag = pst.executeUpdate() == 1;
            }catch(SQLException e){
                return false;
            }
            cn.close();
        }
        return flag;
    }
    
    public static ArrayList<String> getImgPath() throws Exception{
        ArrayList<String> imgList = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null){
            String sql = "select distinct [imgPath] from [dbo].[Plants]";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs != null && rs.next()){
                imgList.add(rs.getString("imgPath"));
            }
            cn.close();
        }
        return imgList;
    }

}
