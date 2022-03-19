package notification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ldg.mybatis.repository.session.ShopingSessionRepositoryTest;

@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ShopingSessionRepositoryTest test = new ShopingSessionRepositoryTest();
    	List<Object> tokens = test.pushNotification();

        final String apiKey = "AAAAIP9bFPk:APA91bFdt9nobOpfnDRxnV04bXgJBX0CjJ85VGGDOkWt-ayFiNYsSgkIaRZ8ZkGtX031ZK_i4eOB9ADoivmrEvTojwfWojSKkvddsQoNhlf8eEgmjIM-rDYGLjn6dcteUxiJGNhlUFeS";
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);
        conn.setDoOutput(true);
        
        String userID = request.getParameter("userID");
        String itemName = request.getParameter("itemName");
        if (userID != null) {
        	userID = URLDecoder.decode(userID, "UTF-8");
        }
        if (itemName != null) {
        	itemName = URLDecoder.decode(itemName, "UTF-8");
        }

        String input = null;
        if (itemName != null) { // itemID값을 가져왔다면 즉, 상품을 구매했다면
        	for (Object token : tokens) {
        		input = "{\"notification\" : {\"title\" : \"상품주문\", \"body\" : \"'"+userID+"' 회원이 '"+itemName+"' 상품을 주문했습니다.\"}, \"to\":\"" + token + "\" }";
        		System.out.println("(buy) json = " + input);
        	}
        } else { // itemID값이 없다면 즉, 회원가입만 했다면
        	for (Object token : tokens) {
        		input = "{\"notification\" : {\"title\" : \"회원가입\", \"body\" : \"'"+userID+"' 회원이 가입했습니다.\"}, \"to\":\"" + token + "\" }";
        		System.out.println("(join) json = " + input);
        	}
        }

        OutputStream os = conn.getOutputStream();
        
        os.write(input.getBytes("UTF-8"));
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + input);
        System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response1 = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
        	response1.append(inputLine);
        }
        in.close();
        // print result
        System.out.println("response = "+ response1.toString());
        PrintWriter script = response.getWriter();
        if (itemName != null) {
        	script.println("<script>");
        	script.println("location.href = 'shoping.jsp'");
    		script.println("</script>");
        } else {
        	script.println("<script>");
    		script.println("location.href = 'login.jsp'");
    		script.println("</script>");
        }
    }
}
