import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BrandTest {
    public void testSelectAll() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("C:\\Users\\Admin\\IdeaProjects\\JDBC\\src\\druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        String sql = "select * from tb_brand";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        Brand brand = null;
        List<Brand> brands = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String brandName = rs.getString("brand_name");
            String companyName = rs.getString("company_name");
            int ordered = rs.getInt("ordered");
            String description = rs.getString("description");
            int status = rs.getInt("status");

            brand = new Brand();
            brand.setId(id);
            brand.setBrandName(brandName);
            brand.setCompanyName(companyName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);

            brands.add(brand);
        }

        for (Brand b : brands) {
            System.out.println(b);
        }

        rs.close();
        pstmt.close();
        conn.close();
    }

    public void testAdd() throws Exception {
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1;
        String description = "可绕地球一圈";
        int status = 1;

        Properties prop = new Properties();
        prop.load(new FileInputStream("C:\\Users\\Admin\\IdeaProjects\\JDBC\\src\\druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        Connection conn = dataSource.getConnection();

        String sql = "insert into tb_brand(brand_name, company_name, ordered, description, status) values (?,?,?,?,?);";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, brandName);
        pstmt.setString(2, companyName);
        pstmt.setInt(3, ordered);
        pstmt.setString(4, description);
        pstmt.setInt(5, status);

        int count = pstmt.executeUpdate();

        System.out.println(count > 0);

        pstmt.close();
        conn.close();

    }

    public void testUpdate() throws Exception {
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1000;
        String description = "可绕地球十圈";
        int status = 1;
        int id = 4;

        Properties prop = new Properties();
        prop.load(new FileInputStream("C:\\Users\\Admin\\IdeaProjects\\JDBC\\src\\druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        String sql = "update tb_brand \n" +
                " set brand_name = ?,\n" +
                " company_name = ?,\n" +
                " ordered = ?,\n" +
                " description = ?,\n" +
                " status = ?\n" +
                " where id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, brandName);
        pstmt.setString(2, companyName);
        pstmt.setInt(3, ordered);
        pstmt.setString(4, description);
        pstmt.setInt(5, status);
        pstmt.setInt(6, id);

        int count = pstmt.executeUpdate();

        System.out.println(count > 0);

        pstmt.close();
        conn.close();
    }

    public void testDeleteById() throws Exception {
        int id = 4;
        Properties prop = new Properties();
        prop.load(new FileInputStream("C:\\Users\\Admin\\IdeaProjects\\JDBC\\src\\druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        String sql = "delete from tb_brand where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);


        int count = pstmt.executeUpdate();
        System.out.println(count > 0);

        pstmt.close();
        conn.close();
    }

    public static void main(String[] args) throws Exception {
        BrandTest bt = new BrandTest();
        bt.testDeleteById();
    }
}
