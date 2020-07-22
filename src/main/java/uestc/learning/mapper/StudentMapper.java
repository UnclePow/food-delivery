package uestc.learning.mapper;

import uestc.learning.entity.Student;

public interface StudentMapper {
	public Student[] findManyStudentById(String id);
}
