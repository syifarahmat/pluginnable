package id.syifarahmat.webdesign.pluginone.service;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.*;

@Service
public class TestService {
    private final HikariDataSource dataSource;
    public TestService(HikariDataSource dataSource) {
        this.dataSource=dataSource;
    }
    public String getDateSQL() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select now() as datenow");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Test");
        while (resultSet.next()) {
            Date datenow = resultSet.getDate("datenow");
            System.out.println(datenow);
        }
        return "test";
    }
}
