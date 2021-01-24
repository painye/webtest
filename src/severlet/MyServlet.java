package severlet;

import entity.Student;
import service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MyServlet extends HttpServlet {
    private Student stu;
    private StudentService ss = new StudentService();



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String sname = request.getParameter("sname");
        String sno = request.getParameter("sno");
        String sdept = request.getParameter("sdept");
        String sage = request.getParameter("sage");
        String ssex = request.getParameter("ssex");
        //将从前端接收到的数据进行适当的类型转换，并将数据封装在对象里
        try {
            Class clazz = Class.forName("entity.Student");
            Object obj=clazz.newInstance();
            stu = (Student) obj;
            Integer age=Integer.valueOf(sage);
            stu.setSno(sno);
            stu.setSname(sname);
            stu.setSsex(ssex);
            stu.setSdept(sdept);
            stu.setSage(age);
            System.out.println(stu);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        boolean flag = ss.rejister(stu);

        //服务器返回一个resopnse，做出一个弹窗表明注册成功
        response.getWriter().write("<script>\n" +
                "    alert(\"注册成功"+sname+"\")\n" +
                "</script>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
