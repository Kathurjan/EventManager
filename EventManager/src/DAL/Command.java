package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public interface Command {
    Connection execute() throws SQLServerException;
}
