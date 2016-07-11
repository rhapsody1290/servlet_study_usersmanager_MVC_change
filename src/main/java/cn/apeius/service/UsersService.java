package cn.apeius.service;

import cn.apeius.domain.Users;
import cn.apeius.utils.SqlHelper;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Asus on 2016/6/11.
 */
public class UsersService {
    //验证用户的函数
    public boolean checkUser(Users user){
        boolean b = false;
        String sql = "select * from users where username = ? and passwd = ?";
        String parameters[] = {user.getUsername(),user.getPasswd()};
        ResultSet rs = SqlHelper.executeQuery(sql,parameters);
        try {
            if(rs.next()){
                b = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }
    //获得pageCount
    public int getPageCount(int pageSize){
        ResultSet rs = SqlHelper.executeQuery("select count(*) from users", null);
        int rowCount = 0;
        try {
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            SqlHelper.close(rs,SqlHelper.getPs(),SqlHelper.getCt());
        }
        return (rowCount - 1)/pageSize + 1;


    }
    //按照分页来获取用户列表
    public ArrayList<Users> getUsersByPage(int pageNow, int pageSize){
        ArrayList<Users> al = new ArrayList<Users>();

        ResultSet rs = SqlHelper.executeQuery("select * from users limit " +
                (pageNow - 1) * pageSize + "," + pageSize, null);
        try {
            while(rs.next()){
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setGrade(rs.getInt("grade"));
                user.setPasswd(rs.getString("passwd"));
                al.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            SqlHelper.close(rs,SqlHelper.getPs(),SqlHelper.getCt());
        }
        return al;

    }
    //删除用户
    public boolean deleteUser(String id){
        boolean b = true;
        String sql = "delete from users where id = ?";
        String parameters[] = {id};
        try {
            SqlHelper.executeUpdate(sql, parameters);
        }catch (Exception e){
            b = false;
        }
        return b;
    }
    //添加用户
    public boolean addUser(Users user){
        boolean b = true;
        String sql = "insert into users(username,email,grade,passwd) values(?,?,?,?)";
        String parameters[] = {user.getUsername(),user.getEmail(),String.valueOf(user.getGrade()),user.getPasswd()};
        try{
            SqlHelper.executeUpdate(sql,parameters);
        }catch (Exception e){
            b = false;
        }finally {
            SqlHelper.close(SqlHelper.getRs(),SqlHelper.getPs(),SqlHelper.getCt());
        }

        return b;
    }
    public static void main(String[] args){
        UsersService usersService = new UsersService();
        Users u = new Users();
        u.setUsername("qm");
        u.setEmail("369201191@qq.com");
        u.setGrade(1);
        u.setPasswd("qm");
        usersService.addUser(u);
    }

}
