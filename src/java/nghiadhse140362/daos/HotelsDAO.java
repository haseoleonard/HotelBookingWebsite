/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadhse140362.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nghiadhse140362.dtos.HotelsDTO;
import nghiadhse140362.utils.DBHelpers;

/**
 *
 * @author haseo
 */
public class HotelsDAO implements Serializable{
    
    private Connection con;
    private PreparedStatement psm;
    private ResultSet rs;

    public HotelsDAO() {
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
    private List<HotelsDTO> hotelList;

    public List<HotelsDTO> getHotelList() {
        return hotelList;
    }
    
    public int loadHotelList() throws SQLException, NamingException{
        int total = 0;
        try{
            con =DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select hotelID,hotelName from Hotels";
                psm = con.prepareStatement(sql);
                rs = psm.executeQuery();
                while(rs.next()){
                    int hotelID = rs.getInt("hotelID");
                    String hotelName = rs.getNString("hotelName");
                    if(this.hotelList==null)this.hotelList=new ArrayList<>();
                    this.hotelList.add(new HotelsDTO(hotelID, hotelName));
                    total++;
                }
            }
        }finally{
            closeConnection();
        }
        return total;
    }
}
