package org.fangsoft.testcenter.db;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;


public class DriverManagerDataSource implements DataSource {
    private String user;
    private String password;
    private String url;
    private String driver;

    public DriverManagerDataSource(String driver, String user,
                                   String password, String url) {

        this.driver = driver;
        this.user = user;
        this.password = password;
        this.url = url;
        try {
            Class.forName(this.driver);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                this.url, this.user, this.password);
    }

    public Connection getConnection(String username, String password)
            throws SQLException {
        return DriverManager.getConnection(
                this.url, username, password);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
