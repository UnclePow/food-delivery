package uestc.learning;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhenzi.sms.ZhenziSmsClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import uestc.learning.mapper.FoodMapper;
import uestc.learning.mapper.FormMapper;
import uestc.learning.mapper.ShopMapper;
import uestc.learning.mapper.StudentMapper;
import uestc.learning.mapper.UserMapper;
import uestc.learning.utils.D;
import uestc.learning.web.ApiResult;
import uestc.learning.entity.Food;
import uestc.learning.entity.Shop;
import uestc.learning.entity.Student;
import uestc.learning.entity.User;;

public class UserTest {
	@Test
	public void testJackson() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ApiResult result = new ApiResult();
		result.code = 200;
		result.msg = "success";
		String rs = objectMapper.writeValueAsString(result);
		System.out.println(rs);
		
		ApiResult src = objectMapper.readValue(rs, ApiResult.class);
		System.out.println(src);
	}
	
	@Test
	public void testSelectFromUser() throws IOException {
		SqlSession sqlSession = D.getConn();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserById("123");
		System.out.print(user);
				
	}
	
	@Test
	public void testSelectFromStudent() throws IOException {
		SqlSession session = D.getConn();
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Student[] students = studentMapper.findManyStudentById("2017010101001");
		if(students.length == 0)
			return;
		System.out.print(students[students.length-1]);		
	}
	
	@Test
	public void addUser() throws IOException {
		SqlSession session = D.getConn();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		userMapper.addUser("2018091606001", "1234567", "12345678912", "sabi");
		session.commit();
		session.close();
	}
	
	@Test
	public void testKeyword() throws IOException {
		SqlSession session = D.getConn();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		List<User> users = userMapper.getUsersByKeyword(0, 10, "");
		System.out.println(users);
		session.commit();
		session.close();
	}
	
	@Test
	public void testKeyword_shop() throws IOException {
		SqlSession session = D.getConn();
		ShopMapper shopMapper = session.getMapper(ShopMapper.class);
		List<Shop> shops = shopMapper.getShopsByKeyword(0, 10, "");
		System.out.println(shops);
//		int count = shopMapper.getShopByKeywordCount("");
//		System.out.println(count);
		session.commit();
		session.close();
	}
	@Test
	public void testList_food() throws IOException {
		SqlSession session = D.getConn();
		FoodMapper foodMapper = session.getMapper(FoodMapper.class);
		List<Food> foods = foodMapper.getFoodsByResid(1);
		System.out.println(foods);
//		int count = shopMapper.getShopByKeywordCount("");
//		System.out.println(count);
		session.commit();
		session.close();
	}
	
	@Test
	public void test_addForm() throws IOException {
		SqlSession session = D.getConn();
		FormMapper formMapper = session.getMapper(FormMapper.class);
		int[] foodids = {1,2,3};
		//formMapper.addForm(foodids, "2018091606008");
//		int count = shopMapper.getShopByKeywordCount("");
//		System.out.println(count);
		session.commit();
		session.close();
	}
	
	@Test
	public void test_form() throws IOException {
		SqlSession session = D.getConn();
		FormMapper formMapper = session.getMapper(FormMapper.class);
		//List<int[]> allFoodids = formMapper.listFoodids();
		//System.out.println(allFoodids);
		session.commit();
		session.close();
	}
	
	@Test
	public void test_getResid() throws IOException {
		SqlSession session = D.getConn();
		FoodMapper foodMapper = session.getMapper(FoodMapper.class);
		int resid = foodMapper.getResidByFoodid(12);
		System.out.println(resid);
		session.commit();
		session.close();
	}
	
	@Test
	public void test_sms() throws Exception {
		ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "105913", "79aed4a7-fce6-4a47-af73-7e3fcb878f2f");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("number", "18679478078");
		params.put("templateId", "337");
		String[] templateParams = new String[2];
		templateParams[0] = "3421";
		templateParams[1] = "5分钟";
		params.put("templateParams", templateParams);
		String result = client.send(params);
		
		System.out.print(result);
	}
	
	public class Info{
		public String name;
		public String age;
		public String address;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		@Override
		public String toString() {
			return "Info [name=" + name + ", age=" + age + ", address=" + address + "]";
		}
	}
	
	@Test
	public void test_json() throws Exception {
		String string = "{\"rsCode\": \"zhangsan\", \"msg\": \"message\"}";
		String objectStr="{\"name\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}";
		String arrayStr="[{\"name\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}]";
		//1、使用JSONObject
		JSONObject jsonObject=JSONObject.fromObject(objectStr);
		Object object = jsonObject.get("name");
		Info info = (Info)JSONObject.toBean(jsonObject, Info.class);
		//2、使用JSONArray
		JSONArray jsonArray=JSONArray.fromObject(arrayStr);
		//获得jsonArray的第一个元素
		Object o=jsonArray.get(0);
		JSONObject jsonObject2=JSONObject.fromObject(o);
		Student stu2=(Student)JSONObject.toBean(jsonObject2, Student.class);
		System.out.println("stu:"+info);
		System.out.println("stu2:"+stu2);
		}
	}


