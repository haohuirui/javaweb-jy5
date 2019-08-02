package testdemo;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class TestDemo1 {
    @Test
    public void test1() throws SQLException {
        ComboPooledDataSource co = new ComboPooledDataSource();
        Connection connection = co.getConnection();
        String sql = "select * from users";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(2));
        }
    }
    @Test
    public void test2(){
        String a = "/list.do";
        String[] split = a.split("[/ .]");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }
    @Test
    public void test3(){
        StringBuffer a =new StringBuffer("A");
        StringBuffer b =new StringBuffer("B");
        test4(a,b);
        System.out.println(a+","+b);
    }
    public void test4(StringBuffer x,StringBuffer y){
        x.append(y);
        x.append("C");
        y = x;
        System.out.println(x+","+y);
    }
    @Test
    public void test5(){
        Random random = new Random();
        for (int j = 0; j < 5; j++) {
            int i = random.nextInt(47);
            System.out.println(i);
        }
    }
}
