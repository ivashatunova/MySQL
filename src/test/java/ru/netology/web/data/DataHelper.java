package ru.netology.web.data;

import lombok.Value;

import java.sql.DriverManager;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }


    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getLastVerificationCode() {
        var countSQL = "select code from auth_codes order by created desc limit 1;";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
                var countStmt = conn.createStatement();
        ) {
            try (var rs = countStmt.executeQuery(countSQL)) {
                if (rs.next()) {
                    var count = rs.getString(1);
                    return new VerificationCode(count);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new VerificationCode("0");
    }

    public static void cleanUP() {
        var countSQL1 = "delete from auth_codes;";
        var countSQL2 = "delete from cards;";
        var countSQL3 = "delete from users;";
        try {
            var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
            var statement = connection.createStatement();
            statement.executeUpdate(countSQL1);
            statement.executeUpdate(countSQL2);
            statement.executeUpdate(countSQL3);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
