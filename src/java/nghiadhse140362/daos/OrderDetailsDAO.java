/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadhse140362.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import nghiadhse140362.utils.DBHelpers;

/**
 *
 * @author haseo
 */
public class OrderDetailsDAO implements Serializable{
    
    private Connection con;
    private PreparedStatement psm;
    private ResultSet rs;

    public OrderDetailsDAO() {
        this.con = null;
        this.psm = null;
        this.rs = null;
    }

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (psm != null) {
            psm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    private Map<Integer,Integer> rentedList;

    public Map<Integer, Integer> getRentedList() {
        return rentedList;
    }
    
    public int loadRentedList(Date checkinDate,Date checkOutDate) throws SQLException, NamingException{
        int total = 0;
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select roomID,quantity "
                        + "from OrderDetails "
                        + "where (checkinDate between ? and ?)or(checkoutDate between ? and ?)";
                psm = con.prepareStatement(sql);
                psm.setDate(1, checkinDate);
                psm.setDate(2, checkOutDate);
                psm.setDate(3, checkinDate);
                psm.setDate(4, checkOutDate);
                rs= psm.executeQuery();
                while(rs.next()){
                    if(this.rentedList==null)this.rentedList=new HashMap<>();
                    int roomID = rs.getInt("roomID");
                    int quantity = rs.getInt("quantity");
                    if(this.rentedList.containsKey(roomID)){
                        int prvTotalQuantity = this.rentedList.get(roomID);
                        this.rentedList.put(roomID, prvTotalQuantity+quantity);
                    }else{
                        this.rentedList.put(roomID, quantity);
                    }
                    total++;
                }
            }
        }finally{
            closeConnection();
        }
        return total;
    }
}
