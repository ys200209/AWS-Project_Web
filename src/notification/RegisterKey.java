package notification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ldg.mybatis.repository.session.ShopingSessionRepositoryTest;

@WebServlet("/RegisterKey")
public class RegisterKey extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public static Connection con = null;
    public static Statement stmt = null;
    String sql = null;
    String newToken = null;
    String requestMethod = null;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("RegisterKey ������");
        
        newToken = request.getParameter("token");
        System.out.println("newToken = " + newToken);
        try{
        	ShopingSessionRepositoryTest test = new ShopingSessionRepositoryTest();
            System.out.println("DB���� ����");
            
            if( newToken != null ){
            	System.out.println("��ū�� ���� �Ǿ����ϴ�.");
            	test.insertNotificationUser(newToken);
            } else {
            	System.out.println("��ū���� ���� ���� �ʾҽ��ϴ�.");
            }
        }catch(Exception e){
            System.out.println("DB���� ����");
            e.printStackTrace();
        }
    }
 
}
