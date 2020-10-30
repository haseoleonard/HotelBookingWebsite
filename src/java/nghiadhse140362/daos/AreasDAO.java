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
import nghiadhse140362.dtos.AreasDTO;
import nghiadhse140362.dtos.HotelsDTO;
import nghiadhse140362.utils.DBHelpers;

/**
 *
 * @author haseo
 */
public class AreasDAO implements Serializable{
    
    private Connection con;
    private PreparedStatement psm;
    private ResultSet rs;

    public AreasDAO() {
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
    private List<AreasDTO> areaList;

    public List<AreasDTO> getAreaList() {
        return areaList;
    }
    
    public int loadAreaList() throws SQLException, NamingException{
        int total = 0;
        try{
            con =DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select areaID,areaName from Areas";
                psm = con.prepareStatement(sql);
                rs = psm.executeQuery();
                while(rs.next()){
                    int areaID = rs.getInt("areaID");
                    String areaName = rs.getNString("areaName");
                    if(this.areaList==null)this.areaList=new ArrayList<>();
                    this.areaList.add(new AreasDTO(areaID, areaName));
                    total++;
                }
            }
        }finally{
            closeConnection();
        }
        return total;
    }
}
