/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Order;
import dto.OrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import mylib.DBUtils;

/**
 *
 * @author Legion
 */
public class OrderDAO {

    public static ArrayList<Order> getOrders() throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select OrderID,OrdDate,shipdate,status,AccID\n"
                    + "from Orders";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int orderID = table.getInt("OrderID");
                    String ordDate = table.getString("OrdDate");
                    String shipdate = table.getString("shipdate");
                    int status = table.getInt("status");
                    int accID = table.getInt("accID");
                    Order ord = new Order(orderID, ordDate, shipdate, status, accID);
                    list.add(ord);
                }
            }
            cn.close();
        }
        return list;
    }

    public static ArrayList<Order> getOrders(String email) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select OrderID,OrdDate,shipdate,Orders.status,Orders.AccID\n"
                    + "from Orders join Accounts on Orders.AccID=Accounts.accID\n"
                    + "where Accounts.email=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int orderID = table.getInt("OrderID");
                    String ordDate = table.getString("OrdDate");
                    String shipdate = table.getString("shipdate");
                    int status = table.getInt("status");
                    int accID = table.getInt("accID");
                    Order ord = new Order(orderID, ordDate, shipdate, status, accID);
                    list.add(ord);
                }
            }
            cn.close();
        }
        return list;
    }

    public static ArrayList<Order> getSearchedOrders(Date information) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select OrderID,OrdDate,shipdate,status,AccID\n"
                    + "from Orders\n"
                    + "where OrdDate=? or shipdate=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setDate(1, information);
            pst.setDate(2, information);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int orderID = table.getInt("OrderID");
                    String ordDate = table.getString("OrdDate");
                    String shipdate = table.getString("shipdate");
                    int status = table.getInt("status");
                    int accID = table.getInt("accID");
                    Order ord = new Order(orderID, ordDate, shipdate, status, accID);
                    list.add(ord);
                }
            }
            cn.close();
        }
        return list;
    }

    public static ArrayList<Order> getSearchedOrdersByID(String accid) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select OrderID,OrdDate,shipdate,status,AccID\n"
                    + "from Orders\n"
                    + "where AccID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, accid);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int orderID = table.getInt("OrderID");
                    String ordDate = table.getString("OrdDate");
                    String shipdate = table.getString("shipdate");
                    int status = table.getInt("status");
                    int accID = table.getInt("accID");
                    Order ord = new Order(orderID, ordDate, shipdate, status, accID);
                    list.add(ord);
                }
            }
            cn.close();
        }
        return list;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) {
        Connection cn = null;
        ArrayList<OrderDetail> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select DetailId,OrderID,PID,PName,price,imgPath,quantity\n"
                        + "from OrderDetails, Plants\n"
                        + "where OrderID=? and OrderDetails.FID=Plants.PID";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int detailID = rs.getInt("DetailId");
                        int PlantID = rs.getInt("PID");
                        String PlanName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        int quantity = rs.getInt("quantity");
                        OrderDetail orderDetail = new OrderDetail(detailID, orderID,
                                PlantID, PlanName, price, imgPath, quantity);
                        list.add(orderDetail);
                    }
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean completeOrder(int orderID) throws Exception {
        Connection cn = DBUtils.makeConnection();
        boolean flag = false;
        if (cn != null) {
            String sql = "update Orders\n"
                    + "set status=2, shipdate=?\n"
                    + "where OrderID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            String shipDate = String.valueOf(new Date(System.currentTimeMillis()));
            pst.setString(1, shipDate);
            pst.setInt(2, orderID);
            int table = pst.executeUpdate();
            flag = table == 1;
            cn.close();
        }
        return flag;
    }

    public static boolean reOrder(int orderID) throws Exception {
        Connection cn = null;
        boolean result = false;
        cn = DBUtils.makeConnection();
        if (cn != null) {
            int orderid = 0;
            cn.setAutoCommit(false);
            //first query add new Orders row with old data
            String sql = "insert into Orders(OrdDate,status,AccID)\n"
                    + "select ?,1,AccID\n"
                    + "from Orders\n"
                    + "where OrderID=?";
            Date d = new Date(System.currentTimeMillis());
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setDate(1, d);
            pst.setInt(2, orderID);
            pst.executeUpdate();
            //second query get new created OrderID
            sql = "select top 1 OrderID\n"
                    + "from Orders\n"
                    + "order by OrderID DESC";
            pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs != null && rs.next()) {
                orderid = rs.getInt("OrderID");
            }
            //third query add new OrderDetails with OrderID above
            sql = "insert into OrderDetails(OrderID,FID, quantity)\n"
                    + "select ?,FID, quantity\n"
                    + "from OrderDetails\n"
                    + "where OrderID=?";
            pst = cn.prepareStatement(sql);
            pst.setInt(1, orderid);
            pst.setInt(2, orderID);
            pst.executeUpdate();
            cn.commit();
            cn.setAutoCommit(true);
            cn.close();
            return true;
        }
        return result;
    }

    public static boolean cancelOrder(int orderID) throws Exception {
        Connection cn = DBUtils.makeConnection();
        boolean flag = false;
        if (cn != null) {
            String sql = "update Orders\n"
                    + "set status=3\n"
                    + "where OrderID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, orderID);
            flag = pst.executeUpdate() == 1;
            cn.close();
        }
        return flag;
    }

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                String sql = "select accID\n"
                        + "from Accounts\n"
                        + "where Accounts.email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }

                Date d = new Date(System.currentTimeMillis());
                sql = "insert Orders(OrdDate,status,AccID)\n"
                        + "values (?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, String.valueOf(d));
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();

                sql = "select top 1 OrderID\n"
                        + "from Orders\n"
                        + "order by OrderID DESC";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("OrderID");
                }
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert OrderDetails\n"
                            + "values(?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                cn.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Order> getOrder(String email, String dateFrom, String dateTo) {
        Connection cn = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID,OrdDate,shipDate,Orders.status,Orders.AccID\n"
                        + "from Orders join Accounts on Orders.AccID=Accounts.accID\n"
                        + "where Accounts.email= ? AND (Orders.OrdDate between ? and ?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                Date ordDate1 = Date.valueOf(dateFrom);
                Date ordDate2 = Date.valueOf(dateTo);
                pst.setString(1, email);
                pst.setDate(2, ordDate1);
                pst.setDate(3, ordDate2);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderID = rs.getInt("OrderID");
                        String orderDate = rs.getString("OrdDate");
                        String orderShipDate = rs.getString("shipDate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("AccID");
                        Order ord = new Order(orderID, orderDate, orderShipDate, status, accID);
                        list.add(ord);
                    }
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static ArrayList<Order> getOrderByStatus(int status) throws Exception{
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select OrderID,OrdDate,shipdate,status,AccID\n"
                    + "from Orders where status=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, status);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int orderID = table.getInt("OrderID");
                    String ordDate = table.getString("OrdDate");
                    String shipdate = table.getString("shipdate");
                    int accID = table.getInt("accID");
                    Order ord = new Order(orderID, ordDate, shipdate, status, accID);
                    list.add(ord);
                }
            }
            cn.close();
        }
        return list;
    }
    
}
