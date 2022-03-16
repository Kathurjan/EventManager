package DAL;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class DatabaseConnector {
    private SQLServerDataSource dataSource;

    public DatabaseConnector() {
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName("Our name");
        dataSource.setUser("CSe21B_15");
        dataSource.setPassword("CSe21B_15");
        dataSource.setPortNumber(1433);
        dataSource.setServerName("10.176.111.31");
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
}
