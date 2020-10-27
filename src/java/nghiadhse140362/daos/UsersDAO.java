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
import javax.naming.NamingException;
import nghiadhse140362.dtos.UsersDTO;
import nghiadhse140362.utils.DBHelpers;

/**
 *
 * @author haseo
 */
public class UsersDAO implements Serializable {

    private Connection con;
    private PreparedStatement psm;
    private ResultSet rs;

    public UsersDAO() {
        this.con = null;
        this.psm = null;
        this.rs = null;
        this.loginUser = null;
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
    private UsersDTO loginUser;

    public UsersDTO getLoginUser() {
        return loginUser;
    }

    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select email,name,address,phone,role "
                        + "from Users "
                        + "where email=? and password=? "
                        + "and status=1";
                psm = con.prepareStatement(sql);
                psm.setString(1, username);
                psm.setString(2, password);
                rs = psm.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("email");
                    String name = rs.getNString("name");
                    String address = rs.getNString("address");
                    String phone = rs.getNString("phone");
                    Date createDate = rs.getDate("createDate");
                    int role = rs.getInt("role");
                    this.loginUser = new UsersDTO(email, name, address, phone, createDate, role);
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean createUser(String email,String password,String name,String address,String phone) throws SQLException, NamingException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "insert into Users (email,password,name,address,phone)"
                        + "values (?,?,?,?,?);";
                psm = con.prepareStatement(sql);
                psm.setString(1, email);
                psm.setString(2, password);
                psm.setNString(3, name);
                psm.setNString(4, address);
                psm.setString(5, phone);
                int result = psm.executeUpdate();
                if(result>0)return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public boolean checkEmailExisted(String email) throws NamingException, SQLException{
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select email from Users where email=?";
                psm = con.prepareStatement(sql);
                psm.setString(1, email);
                rs = psm.executeQuery();
                if(rs.next())return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }
    
    public boolean updatePassword(String email,String password) throws SQLException, NamingException{
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "update Users set password=? where email=?";
                psm = con.prepareStatement(sql);
                psm.setString(1, password);
                psm.setString(2, email);
                int result = psm.executeUpdate();
                if(result>0)return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
