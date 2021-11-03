package com.naichuan.seckill.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naichuan.seckill.pojo.User;
import com.naichuan.seckill.vo.RespBean;
import lombok.SneakyThrows;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户生成测试工具类
 * @author 张乃川
 * @date 2021/11/3 14:47
 */
public class UserUtil {

    private static void createUser(int count) throws Exception {
        List<User> users = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setId(1300000000L + i);
            user.setNickname("user" + i);
            user.setSalt("1a2b3c4d");
            user.setRegisterDate(new Date());
            user.setLoginCount(1);
            user.setPassword(MD5Util.inputPassToDBPass("123456", user.getSalt()));
            users.add(user);
        }
        System.out.println("Creating users...");
        // 插入数据库
        Connection conn = getConnection();
        String sql = "insert into t_user(login_count, nickname, register_date, salt, password, id) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            stmt.setInt(1, user.getLoginCount());
            stmt.setString(2, user.getNickname());
            stmt.setTimestamp(3, new Timestamp(user.getRegisterDate().getTime()));
            stmt.setString(4, user.getSalt());
            stmt.setString(5, user.getPassword());
            stmt.setLong(6, user.getId());
            stmt.addBatch();
        }
        stmt.executeBatch();
        stmt.clearParameters();
        conn.close();
        System.out.println("Generate userTickets...");
        // 登录，生成userTicket
        String urlString = "http://localhost:8111/login/doLogin";
        File file = new File("C:\\Users\\cmbsysadmin\\Desktop\\config.txt");
        if (file.exists()) {
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.seek(0);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            URL url = new URL(urlString);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setDoOutput(true);
            OutputStream out = urlConn.getOutputStream();
            String params = "mobile=" + user.getId() + "&password=" + MD5Util.inputPassToFromPass("123456");
            out.write(params.getBytes());
            out.flush();
            InputStream in = urlConn.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) >= 0) {
                bout.write(buf, 0, len);
            }
            in.close();
            bout.close();
            String response = new String(bout.toByteArray());
            ObjectMapper mapper = new ObjectMapper();
            RespBean respBean = mapper.readValue(response, RespBean.class);
            String userTicket = (String) respBean.getData();
            System.out.println("create userTicket: " + user.getId());
            String row = user.getId() + "," + userTicket;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            System.out.println("Write to file: " + user.getId());
        }
        raf.close();
        System.out.println("DONE!!!");
    }

    private static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/seckill?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    @SneakyThrows
    public static void main(String[] args) {
        createUser(5000);
    }
}
