package service;

import dao.StudentDao;
import entity.Student;

/**
 * 用来为Student表进行服务
 */
public class StudentService {
    private StudentDao stuDao=new StudentDao();

    public boolean rejister(Student stu){

        return  stuDao.insert(stu);
    }
}
