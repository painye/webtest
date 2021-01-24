package dao;

import entity.Student;
import util.JdbcUtil;

/**
 * 该类实现了对jdbc底层的调用，数据库和数据之间的交换修改
 */
public class StudentDao {

    /**
     *该方法实现了往数据库中的student表中插入新的数据
     * @param
     * @return
     */
    /*
       public static void main (String[] args ){
        boolean flag = false;
        Student stu=new Student();
        stu.setSno("201215160");
        stu.setSname("张军怡2");
        stu.setSsex("女");
        stu.setSdept("SD");
        System.out.println(stu);
        String sql = "insert into Student values (?, ?, ?, ?, ?)";
        JdbcUtil.getConnection();
        JdbcUtil.insert(sql, stu);
        JdbcUtil.closeConnection();


    }

     */
    public boolean insert(Student stu){
        System.out.println(stu);
        String sql = "insert into Student values (?, ?, ?, ?, ?)";
        JdbcUtil.getConnection();
        boolean flag = JdbcUtil.insert(sql, stu);
        JdbcUtil.closeConnection();
        return flag;
    }
}
