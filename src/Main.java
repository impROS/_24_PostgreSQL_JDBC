import java.sql.*;

public class Main {
    public static void main(String[] args) {
        ex2();
    }

    public static void ex2() {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/test_java";
        String username = "postgres";
        String password = "1234";

        //try-with-resources
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Parametreli SQL sorgusu
            String sqlQuery = "SELECT * FROM users WHERE name = ?";

            // PreparedStatement oluşturma
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                // Parametreyi set etme
                preparedStatement.setString(1, "test");

                // Sorguyu çalıştırma
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Sonuçları işleme
                    while (resultSet.next()) {
                        int passwordField = resultSet.getInt("password");
                        String nameField = resultSet.getString("name");
                        System.out.println("password: " + passwordField + ", Name: " + nameField);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ex1() {
// PostgreSQL veritabanı bağlantısı için gerekli bilgiler
        String url = "jdbc:postgresql://localhost:5432/test_java";
        String username = "postgres";
        String password = "1234";

        // JDBC nesneleri
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // JDBC sürücüsünü yükleme
            Class.forName("org.postgresql.Driver");

            // Veritabanına bağlanma
            connection = DriverManager.getConnection(url, username, password);

            String sqlQuery = "SELECT * FROM users";

            // SQL sorgusunu çalıştırma
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            // Sonuçları işleme
            while (resultSet.next()) {
                int passwordField = resultSet.getInt("password");
                String nameField = resultSet.getString("name");
                System.out.println("password: " + passwordField + ", Name: " + nameField);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Kapatma işlemleri
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//end_ex1
}

