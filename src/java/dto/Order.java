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
public class Order implements Serializable{
    private int orderID;
    private String orderDate;
    private String shipDate;
    private int status;
    private int accID;
    
    public Order(){
        
    }

    public Order(int orderID, String orderDate, String shipDate, int status, int accID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
        this.accID = accID;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public int getStatus() {
        return status;
    }

    public int getAccID() {
        return accID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }
    
}
