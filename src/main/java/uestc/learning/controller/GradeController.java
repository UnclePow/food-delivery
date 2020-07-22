package uestc.learning.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uestc.learning.entity.Grade;
import uestc.learning.mapper.GradeMapper;
import uestc.learning.utils.D;

public class GradeController {
	ObjectMapper objectMapper = new ObjectMapper();
	SqlSession sqlSession = D.getConn();
	GradeMapper gradeMapper = sqlSession.getMapper(GradeMapper.class);
	
	
	public void list(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		List<Grade> grades = gradeMapper.listGrades();
		resp.getWriter().write(objectMapper.writeValueAsString(grades));
	}
}
