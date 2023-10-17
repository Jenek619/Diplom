package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String pass = System.getProperty("db.password");

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    @SneakyThrows
    public static DataHelper.paymentStatus getPaymentStatus() {
        var codeSQL = "SELECT status FROM payment_entity";
        var conn = getConn();
        var code = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new DataHelper.paymentStatus(code);
    }

    @SneakyThrows
    public static DataHelper.creditStatus getCreditStatus() {
        var codeSQL = "SELECT status FROM credit_request_entity";
        var conn = getConn();
        var code = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new DataHelper.creditStatus(code);
    }

    @SneakyThrows
    public static void clearBD() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }

}