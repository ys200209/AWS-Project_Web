package ldg.mybatis.repository.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ldg.mybatis.model.Items;
import ldg.mybatis.model.User;
import ldg.mybatis.model.Vw_order;

public class ShopingSessionRepositoryTest {
	
	private final ShopingSessionRepository shopingSessionRepository = new ShopingSessionRepository();

	// 회원가입을 위한 메서드
	public long InsertUser(String userID, String userPassword, String userName, String userGender, String userEmail) {	
		User user = new User();
		user.setUserID(userID);
		user.setUserPassword(userPassword);
		user.setUserName(userName);
		user.setUserGender(userGender);
		user.setUserEmail(userEmail);
		return shopingSessionRepository.insertUser(user);
	}
	
	public int insertPoint(String userID) {
		return shopingSessionRepository.insertPoint(userID);
	}
	
	// 로그인을 위한 메서드
	public String SelectUserLogin(String userID, String userPassword) {
		User user = new User();
		user.setUserID(userID);
		user.setUserPassword(userPassword);
		return shopingSessionRepository.selectUserLogin(user);
	}

	// 상품 추가를 위한 메서드
	public long insertItems(String itemName, String itemPrice, String itemContent) {
		Items items = new Items();
		items.setItemName(itemName);
		items.setItemPrice(Integer.parseInt(itemPrice));
		items.setItemContent(itemContent);
		
		return shopingSessionRepository.insertItems(items);
	}
	// 상품 삭제를 위한 메서드
	public int deleteItems(int itemID) {
		Items items = new Items();
		items.setItemID(itemID);
		return shopingSessionRepository.deleteItems(items);
	}
	
	// 아이디 변경을 위한 메서드
	public void updateUserID(String userID, String userUpdateID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userID", userID);
		map.put("userUpdateID", userUpdateID);
		shopingSessionRepository.updateUserID(map);
	}
	
	// 비밀번호 변경을 위한 메서드
	public void updateUserPassword(String userID, String userUpdatePassword, String userPasswordCheck) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!userUpdatePassword.equals(userPasswordCheck)) { // 비밀번호 확인 불일치시 예외 생성
			throw new NullPointerException();
		}
		map.put("userID", userID);
		map.put("userUpdatePassword", userUpdatePassword);
		shopingSessionRepository.updateUserPassword(map);
	}

	// 주문하기 페이지에서 상품 목록을 게시하기 위한 메서드
	public List<Items> shopingList(String searchItem) {
		Items items = new Items();
		items.setItemName(searchItem);
		return shopingSessionRepository.shopingList(items);
	}
	
	// 구매하기 버튼을 눌렀을때 상품에 대한 상세정보를 보여주기 위한 메서드
	public List<Items> itemView(int itemID) {
		Items items = new Items();
		items.setItemID(itemID);
		
		return shopingSessionRepository.itemView(items);
	}
	
	// 회원 탈퇴기능을 위한 메서드
	public long deleteUser(String userID) {
		User user = new User();
		user.setUserID(userID);
		
		return shopingSessionRepository.deleteUser(user);
	}
	
	// 구매를 확정하는 버튼을 클릭했을때 실제로 구매가 진행되는 기능을 구현한 메서드
	public String buyAction(String userID, int itemID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userID", userID);
		map.put("itemID", itemID);
		shopingSessionRepository.buyAction(map);
		return shopingSessionRepository.pushNotificationItem(itemID);
	}
	
	// 모든 회원들의 주문내역을 보여주기 위한 메서드
	public List<Vw_order> shopingLog() {
		Vw_order vw_order = new Vw_order();
		return shopingSessionRepository.shopingLog(vw_order);
	}
	
	// 관리자계정의 '회원정보' 페이지에서 가입된 모든 회원들의 정보를 보여주기 위한 메서드
	public List<User> selectUserList() {
		return shopingSessionRepository.selectUserList();
	}
	
	// 관리자계정의 '회원정보' 페이지에서 각각 회원들이 구매한 상품의 총액을 보여주는 메서드
	public List<Object> sumOrderPrice(List<User> userList) {
		return shopingSessionRepository.sumOrderPrice(userList);
	}
	
	// 관리자계정의 '회원정보' 페이지에서 가장 구매총액이 높은 회원의 아이디를 보여주는 메서드
	public String maxSumPrice() {
		return shopingSessionRepository.maxSumPrice();
	}
	
	// 회원계정의 '내정보' 페이지에서 내가 구매한 상품들의 목록을 한눈에 확인할 수 있는 메서드
	public List<Vw_order> myBuyList(String userID) {
		return shopingSessionRepository.myBuyList(userID);
	}
	
	//  상품에 대한 정보를 수정하기 위한 메서드
	public int updateItems(int itemID, String itemName, int itemPrice, String itemContent) {
		Items items = new Items();
		items.setItemID(itemID);
		items.setItemName(itemName);
		items.setItemPrice(itemPrice);
		items.setItemContent(itemContent);
		return shopingSessionRepository.updateItems(items);
	}
	
	// 회원의 포인트를 확인할 수 있는 메서드
	public int Point(String userID) {
		return shopingSessionRepository.Point(userID);
	}
	
	public List<Object> pushNotification() {
		return shopingSessionRepository.pushNotification();
	}
	
	public int insertNotificationUser(String newToken) {
		return shopingSessionRepository.insertNotificationUser(newToken);
	}
	
	public String pushNotificationItem(int itemID) {
		return shopingSessionRepository.pushNotificationItem(itemID);
	}
	
	
}
