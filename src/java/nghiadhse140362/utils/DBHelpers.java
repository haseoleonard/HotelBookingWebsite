/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadhse140362.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author haseo
 */
public abstract class DBHelpers{
    public static Connection makeConnection() throws NamingException, SQLException{
        Context curContext = new InitialContext();
        Context tomcatContext = (Context) curContext.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatContext.lookup("");
        return ds.getConnection(Constants.DB_USERNAME, Constants.DB_PASSWORD);
    }
}