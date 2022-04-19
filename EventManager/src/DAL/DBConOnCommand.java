package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class DBConOnCommand implements Command{

    DatabaseConnector databaseConnector;

    public DBConOnCommand(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Connection execute() throws SQLServerException {
        return databaseConnector.getConnection();
    }
}
