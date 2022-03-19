package ldg.mybatis.repository.session;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

import ldg.mybatis.model.User;
import ldg.mybatis.model.Vw_order;
import ldg.mybatis.model.Items;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

public class ShopingSessionRepository {
	private final String namespace = "ldg.mybatis.repository.mapper.MyBatisMapper";
	
	private SqlSessionFactory getSqlSessionFactory() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public long insertUser(User user) { // 회원가입을 구현한 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			String statement = namespace + ".insertUser";
			int result = sqlSession.insert(statement, user);
			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return -1;
	}

	public int insertPoint(String userID) { // 회원가입시 각 회원의 포인트를 0으로 초기화 시키는 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			String statement = namespace + ".insertPoint";
			int result = sqlSession.insert(statement, userID);
			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return -1;
	}
	
	public String selectUserLogin(User user) { // 로그인을 구현한 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectOne(namespace + ".selectUserLogin", user);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return null;
	}

	public long insertItems(Items items) { // 상품목록 추가를 구현한 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			String statement = namespace + ".insertItems";
			int result = sqlSession.insert(statement, items);
			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return -1;
	}
	
	public int deleteItems(Items items) { // 상품 삭제를 구현한 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			String statement = namespace + ".deleteItems";
			int result = sqlSession.delete(statement, items);
			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return -1;
	}
	
	public void updateUserID(Map<String, Object> map) { // 아이디 변경을 구현한 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			long result = sqlSession.update(namespace + ".updateUserID", map);
			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	public void updateUserPassword(Map<String, Object> map) { // 비밀번호 변경을 구현한 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			long result = sqlSession.update(namespace + ".updateUserPassword", map);
			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	public List<Items> shopingList(Items items) { // 상품 목록 게시를 위한 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".shopingList", items);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return null;
	}
	
	public List<Items> itemView(Items items) { // 구매하기 버튼을 누른 뒤 상품에 대한 정보를 나타내주는 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".itemView", items);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return null;
	}
	
	public long deleteUser(User user) { // 회원 탈퇴를 구현한 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			int result = sqlSession.delete(namespace + ".deleteUser", user);
			System.out.println("(회원탈퇴) result = " + result);
			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return -1;
	}
	
	public void buyAction(Map<String, Object> map) { // 구매확인 페이지에서 최종적으로 구매버튼을 누르면 실행되는 구매 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			long result = sqlSession.insert(namespace + ".buyAction", map);
			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	public List<Vw_order> shopingLog(Vw_order vw_order) { // 회원들의 모든 주문내역을 확인할 수 있도록 구현한 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".shopingLog", vw_order);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return null;
	}
	
	public List<User> selectUserList() { // 관리자 계정에서 '회원정보'를 누르면 보여지는 모든 회원들의 정보를 보여주도록 구현한 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".selectUserList");
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return null;
	}
	
	public List<Object> sumOrderPrice(List<User> userList) { // 각 회원마다 구매한 금액의 합계를 나타내주는 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			List<Object> list = new ArrayList<>();
			for(int i = 0; i < userList.size(); i++) {
				Object obj = sqlSession.selectOne(namespace + ".sumOrderPrice", userList.get(i).getUserID());
				if(obj == null) { list.add(0); } // 구매내역이 하나도 없다면 0을 넣도록 구현
				else { list.add(obj); } // 구매의 합이 존재한다면 합을 삽입
			}
			return list;
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return null;
	}
	
	public String maxSumPrice() { // 구매액이 가장 높은 회원의 아이디를 나타내주는 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectOne(namespace + ".maxSumPrice");
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return null;
	}
	
	public List<Vw_order> myBuyList(String userID) { // 회원 계정의 '내 정보'페이지에서 나의 구매 목록을 나타내주는 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".myBuyList", userID);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return null;
	}
	
	public int updateItems(Items items) { // 상품 정보 수정을 구현한 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			int result = sqlSession.update(namespace + ".updateItems", items);
			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
			return result;
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return -1;
	}
	
	public int Point(String userID) { // 각 회원마다 구매한 금액의 합계를 나타내주는 메서드
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int userPoint = 0;
		try {
			Object obj = sqlSession.selectOne(namespace + ".sumOrderPrice", userID);
			if(obj == null) { throw new Exception(); } // 구매내역이 하나도 없다면 익셉션 생성
			int sumPrice = Integer.parseInt(obj.toString());
			userPoint = (int) (sumPrice * 0.05);
			Map<String, Object> map = new HashMap<>();
			map.put("userID", userID);
			map.put("sumPrice", sumPrice);
			map.put("userPoint", userPoint);
			
			int result = sqlSession.update(namespace + ".point", map);
			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return userPoint;
	}
	
	public List<Object> pushNotification() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<Object> result = new ArrayList<>();
		try {
			result = sqlSession.selectList(namespace + ".pushNotification");
			if (!result.isEmpty()) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
			return result;
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	public int insertNotificationUser(String newToken) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			int result = sqlSession.insert(namespace + ".insertNotificationUser", newToken);
			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
			return result;
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return -1;
	}
	
	public String pushNotificationItem(int itemID) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectOne(namespace + ".pushNotificationItem", itemID);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return null;
	}
	
}
