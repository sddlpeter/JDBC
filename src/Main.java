import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/DemoTransaction";
        String username = "root";
        String password = "neu05317X";
        Connection conn = DriverManager.getConnection(url, username, password);

        System.out.println("here");

        String name = "'test'";
        String psd = "'' or 1 = 1;";

        String sql = "select * from tb_user where name = ? and password = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, psd);

        ResultSet set = pstmt.executeQuery();
        while (set.next()) {
            int id = set.getInt("id");
            String nm = set.getString("name");
            String pd = set.getString("Password");

            System.out.println(id + " " + nm + " " + pd);
        }

        pstmt.close();
        conn.close();
    }
}