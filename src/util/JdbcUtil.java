package util;


import entity.Student;
import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JdbcUtil {

    //获取日志目标记录器，通过指定的名字获得记录器，记录器负责控制日志信息
    public static final Logger log = Logger.getLogger(JdbcUtil.class);

    private static Connection con =null;
    private static PreparedStatement ps =null;
    private static ResultSet rs = null;

    private static String url;
    private static String user;
    private static String password;

    private static Scanner sc = new Scanner(System.in);

    static {//静态代码块在类加载的时候就被执行，且执行一次，刚好驱动也只用注册一次
        try {
            //1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            log.info("注册驱动成功");
        } catch (ClassNotFoundException e) {
            log.error("注册驱动出错", e);
        }
    }

    public static boolean getConnection(){
        //log.info("现在正在进行连接");
        //2、获取连接
        boolean flag = false;
        ResourceBundle bundle = ResourceBundle.getBundle("util\\MyJdbc");
        //获取资源绑定器，绑定属性文件
        try {
            url=bundle.getString("url");
            user=bundle.getString("user");
            password=bundle.getString("passWord");
            con = DriverManager.getConnection(url, user, password);
            log.info("获取连接成功");
            flag = true;
        } catch (SQLException e) {
            log.error("获取连接失败", e);
        }
        return flag;
    }


    public static <T>  boolean insert(String sql, T t){
        log.info("当前准备执行向数据库插入的操作");
        try {
            ps = con.prepareStatement(sql);
            //sql = insert into Student (sno, sname, ssex, sage, sdept) values (?, ?, ?, ?, ?);
            //将sql以空格分开，利用反射确定类名后序需要添加的属性的值
            String[] arr= sql.split(" ");
            Class clazz=Class.forName("entity."+arr[2]);
            Field[] fields = clazz.getDeclaredFields();
            String methodName = null;//获取get方法名
            Object obj=null;//获取t中属性的值
            Method method=null;//获取get属性的方法
            int i=1;
            for (Field field : fields) {
                methodName = "get"+field.getName().toUpperCase().charAt(0)+field.getName().substring(1);
                method=clazz.getMethod(methodName);
                obj=method.invoke(t);
                ps.setObject(i, obj);
                i++;
            }
            int count = ps.executeUpdate();
            if(count > 0){
                log.info("插入新数据成功");
                return true;
            }
            else{
                log.info("插入数据失败");
                return false;
            }
        } catch (SQLException e) {
            log.error("PS出错",e);
        } catch (ClassNotFoundException e) {
            log.error("没有找到这个类",e);
        } catch (NoSuchMethodException e) {
            log.error("没有找到需要的方法", e);
        } catch (InvocationTargetException e) {
            log.error("俺也不知道这个是什么异常", e);
        } catch (IllegalAccessException e) {
            log.error("没有访问权限", e);
        }
        return false;
    }

    //根据表名获取表的主键
    public static String getPrimaryKey(Field[] fields){
        for (Field field : fields) {
            if(field.getName().indexOf("no")!=-1)   return field.getName();
        }
        return null;
    }


    public static boolean login(String sql, Object obj){
        log.info("当前正在执行查找工作");
        //String sql = "select * from Student where sno = ? and sname = ?";
        String[] arr= sql.split(" ");
        Class clazz= null;
        try {
            //反射创建代查表的实体类
            clazz = Class.forName("entity."+arr[3]);
            Method getNo, getName;
            //get+S+no;
            getNo=clazz.getMethod("get"+arr[5].toUpperCase().charAt(0)+arr[5].toLowerCase().substring(1));
            //get+S+name;
            getName=clazz.getMethod("get"+arr[9].toUpperCase().charAt(0)+arr[9].toLowerCase().substring(1));
            //调用obj对象的getSno方法
            Object no=getNo.invoke(obj);
            Object name=getName.invoke(obj);
            ps = con.prepareStatement(sql);
            ps.setObject(1, no);
            ps.setObject(2, name);
            rs = ps.executeQuery();
            if(rs.next()) {
                log.info("查找成功");
                ResultSetMetaData columns = rs.getMetaData();
                for(int i=1; i<=columns.getColumnCount(); i++){
                    log.info(rs.getObject(i));
                }
                return true;
            }
            else{
                log.info("查找失败");
                return false;
            }
        } catch (ClassNotFoundException e) {
            log.error("没有找到这个类",e);
        } catch (NoSuchMethodException e) {
            log.error("没有找到需要的方法", e);
        } catch (IllegalAccessException e) {
            log.error("没有访问权限", e);
        } catch (InvocationTargetException e) {
            log.error("俺也不知道这个是什么异常", e);
        } catch (SQLException e) {
            log.error("PS出错",e);
        }
        return false;
    }

    public static boolean closeConnection(){
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("关闭资源出错", e);
            }
        }
        if(ps!=null) {
            try {
                ps.close();
            } catch (SQLException e) {
                log.error("关闭资源出错", e);
            }
        }
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("关闭资源出错", e);
            }
        }
        log.info("关闭资源成功");

        return true;
    }
}
