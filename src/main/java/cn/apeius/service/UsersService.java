package cn.apeius.service;

import cn.apeius.domain.Users;
import cn.apeius.utils.SqlHelper;

import java.sql.*;

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
}
