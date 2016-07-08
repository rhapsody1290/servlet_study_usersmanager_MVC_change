package cn.apeius.service;

import cn.apeius.domain.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Asus on 2016/6/11.
 */
public class UsersService {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    //验证用户的函数
    public boolean checkUser(Users user){
        boolean b = false;
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获得连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_users_manager","root","root");
            //3.创建prepareStatement
            ps = connection.prepareStatement("select * from users where username = ? and passwd = ?");
            ps.setObject(1,user.getUsername());
            ps.setObject(2,user.getPasswd());
            //4.执行操作
            rs = ps.executeQuery();
            if(rs.next()){
                b = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源
            if(rs != null){   // 关闭记录集
                try{
                    rs.close();
                }catch(Exception e){
                    e.printStackTrace() ;
                }
            }
            if(ps != null){   // 关闭声明
                try{
                    ps.close() ;
                }catch(Exception e){
                    e.printStackTrace() ;
                }
            }
            if(connection != null){  // 关闭连接对象
                try{
                    connection.close() ;
                }catch(Exception e){
                    e.printStackTrace() ;
                }
            }
        }
        return b;
    }
}
