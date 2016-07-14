package cn.apeius.service;

import cn.apeius.domain.Users;
import cn.apeius.utils.SqlHelper;
import sun.java2d.opengl.WGLSurfaceData;

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
        ArrayList al = SqlHelper.executeQuery2(sql,parameters);
        if(al.size() > 0){
            b = true;
        }
        return b;
    }
    //获得pageCount
    public int getPageCount(int pageSize){
        ArrayList al = SqlHelper.executeQuery2("select count(*) from users", null);
        Object[] objs = (Object[]) al.get(0);
        int rowCount = Integer.parseInt(objs[0].toString());
        return (rowCount - 1)/pageSize + 1;
    }
    //按照分页来获取用户列表
    public ArrayList<Users> getUsersByPage(int pageNow, int pageSize){
        ArrayList<Users> al = new ArrayList<Users>();

        ArrayList rs = SqlHelper.executeQuery2("select * from users limit " +
                (pageNow - 1) * pageSize + "," + pageSize, null);

        for(int i = 0; i < rs.size(); i++){

            Object[] objs = (Object[]) rs.get(i);
            Users user = new Users();

            user.setId(Integer.parseInt(objs[0].toString()));
            user.setUsername(objs[1].toString());
            user.setEmail(objs[2].toString());
            user.setGrade(Integer.parseInt(objs[3].toString()));
            user.setPasswd(objs[4].toString());

            al.add(user);
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
        UsersService u = new UsersService();
        System.out.println(u.getUsersByPage(1,2));
    }
}
