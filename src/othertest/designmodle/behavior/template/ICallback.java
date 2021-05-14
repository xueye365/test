package src.othertest.designmodle.behavior.template;

import java.sql.*;

/**
 * 回调函数
 * 通过 ICallback 定制 process() 函数，也就是说，框架因此具有了扩展的能力。
 * 同步回调指在函数返回之前执行回调函数；异步回调指的是在函数返回之后执行回调函数。
 * 同步回调看起来更像模板模式，异步回调看起来更像观察者模式。
 *
 */
public interface ICallback {
    void methodToCallback();
}

class BClass {
    public void process(ICallback callback) {
        //...
        callback.methodToCallback();
        //...
    }
}

class AClass {
    public static void main(String[] args) {
        BClass b = new BClass();
        b.process(new ICallback() { //回调对象
            @Override
            public void methodToCallback() {
                System.out.println("Call back me.");
            }
        });
    }
}

/**
 * JdbcTemplate、RedisTemplate、RestTemplate。
 * 尽管都叫作 xxxTemplate，但它们并非基于模板模式来实现的，而是基于回调来实现的，确切地说应该是同步回调
 */
class JdbcDemo {
    public User queryUser(long id) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "xzg", "xzg");

            //2.创建statement类对象，用来执行SQL语句
            stmt = conn.createStatement();

            //3.ResultSet类，用来存放获取的结果集
            String sql = "select * from user where id=" + id;
            ResultSet resultSet = stmt.executeQuery(sql);

            String eid = null, ename = null, price = null;

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setTelephone(resultSet.getString("telephone"));
                return user;
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO: log...
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO: log...
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // TODO: log...
                }
        }
        return null;
    }

}


//public class JdbcTemplateDemo {
//    private JdbcTemplate jdbcTemplate;
//
//    public User queryUser(long id) {
//        String sql = "select * from user where id="+id;
//        return jdbcTemplate.query(sql, new UserRowMapper()).get(0);
//    }
//
//    class UserRowMapper implements RowMapper<User> {
//        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//            User user = new User();
//            user.setId(rs.getLong("id"));
//            user.setName(rs.getString("name"));
//            user.setTelephone(rs.getString("telephone"));
//            return user;
//        }
//    }
//}

class User {
    Long id;
    String name;
    String telephone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
