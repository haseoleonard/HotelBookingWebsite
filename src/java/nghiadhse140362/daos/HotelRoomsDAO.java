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
import nghiadhse140362.dtos.HotelRoomDTOExtends;
import nghiadhse140362.utils.DBHelpers;

/**
 *
 * @author haseo
 */
public class HotelRoomsDAO implements Serializable {

    private Connection con;
    private PreparedStatement psm;
    private ResultSet rs;

    public HotelRoomsDAO() {
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
    private List<HotelRoomDTOExtends> roomList;

    public List<HotelRoomDTOExtends> getRoomList() {
        return roomList;
    }

    public int getAllRoom(String areaName, String hotelName) throws SQLException, NamingException {
        int total = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                if (!areaName.trim().isEmpty() || !hotelName.trim().isEmpty()) {
                    String sql = "select hr.roomID,hr.hotelID,h.hotelName,hr.image,h.areaID "
                            + ",a.areaName,hr.categoryID,rc.categoryName,rc.price,hr.quantity "
                            + "from (((HotelRooms hr "
                            + "Inner Join Hotels h on hr.hotelID=h.hotelID) "
                            + "Inner Join Areas a on h.areaID=a.areaID) "
                            + "Inner Join RoomCategories rc on hr.categoryID=rc.categoryID) "
                            + "where a.areaName=? or h.hotelName=?";
                    psm = con.prepareStatement(sql);
                    psm.setNString(1, areaName);
                    psm.setNString(2, hotelName);
                } else {
                    String sql = "select hr.roomID,hr.hotelID,h.hotelName,hr.image,h.areaID "
                            + ",a.areaName,hr.categoryID,rc.categoryName,rc.price,hr.quantity "
                            + "from (((HotelRooms hr "
                            + "Inner Join Hotels h on hr.hotelID=h.hotelID) "
                            + "Inner Join Areas a on h.areaID=a.areaID) "
                            + "Inner Join RoomCategories rc on hr.categoryID=rc.categoryID)";
                    psm = con.prepareStatement(sql);
                }
                rs = psm.executeQuery();
                while (rs.next()) {
                    if (this.roomList == null) {
                        this.roomList = new ArrayList<>();
                    }
                    int roomID = rs.getInt("roomID");
                    int hotelID = rs.getInt("hotelID");
                    int areaID = rs.getInt("areaID");
                    int categoryID = rs.getInt("categoryID");
                    String hName = rs.getNString("hotelName");
                    String aName = rs.getNString("areaName");
                    String cName = rs.getNString("categoryName");
                    String image = rs.getString("image");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    this.roomList.add(new HotelRoomDTOExtends(hName, cName, areaID, aName, price, roomID, hotelID, categoryID, image, quantity));
                    total++;
                }
            }
        } finally {
            closeConnection();
        }
        return total;
    }
}
