package service;

import dao.StudentDao;
import entity.Student;

/**
 * 用来为Student表进行服务
 */
public class StudentService {
    private StudentDao stuDao=new StudentDao();

    public String rejister(Student stu){
        boolean flag = stuDao.insert(stu);
        //服务器返回一个resopnse，做出一个弹窗表明注册成功
        if(flag){
                return "注册成功"+stu.getSname();
        }else {
            return "注册失败" + stu.getSname();
        }
    }

    public String login(Student stu){
        boolean flag=stuDao.login(stu);
        if(flag){
            return "登录成功"+stu.getSname();
        }else{
            return "登录失败"+stu.getSname();
        }
    }

    protected boolean select(Student stu){
        return true;
    }
}
