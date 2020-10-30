/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadhse140362.listeners;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import nghiadhse140362.utils.Constants;
import nghiadhse140362.utils.MailHelpers;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Web application lifecycle listener.
 *
 * @author haseo
 */
public class ServletContextListener implements javax.servlet.ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(ServletContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ServletContext context = sce.getServletContext();
        //
        PropertyConfigurator.configure(context.getRealPath("/WEB-INF/log4j.properties"));
        //
        Map<String, String> resourceMap = new HashMap<>();
        resourceMap.put("", Constants.HOME_PAGE);
        resourceMap.put("login", "LoginController");
        resourceMap.put("create", "CreateAccountController");
        resourceMap.put("logout", "LogoutController");
        resourceMap.put("search", "SearchRoomController");
        resourceMap.put("sendemail", "SendResetPasswordEmailController");
        resourceMap.put("checkcode", "CheckResetCodeController");
        context.setAttribute("RESOURCE_MAP", resourceMap);
        //
        String hostEmail = context.getInitParameter("HOST_EMAIL");
        String emailPass = context.getInitParameter("HOST_EMAIL_PASSWORD");
        try {
            if (hostEmail == null || hostEmail.trim().isEmpty()) {
                throw new Exception("HOST EMAIL MUST NOT BE EMPTY");
            }
            MailHelpers.setMAIL_USER(hostEmail);
            if(emailPass == null||emailPass.trim().isEmpty()){
                throw new Exception("PASSWORD FOR EMAIL CANNOT BE EMPTY");
            }
            MailHelpers.setMAIL_PASSWORD(emailPass);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
