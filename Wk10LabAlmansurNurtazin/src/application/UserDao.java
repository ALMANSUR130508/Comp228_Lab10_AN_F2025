package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    public int saveUser(User user) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = Database.getDBConnection();

            String query =
                "INSERT INTO employment_application " +
                "(full_name, current_address, contact_number, email, " +
                "education, gender, available_date, desired_position, " +
                "desired_salary, authorized_work, relatives_working, explanation) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            statement = connection.prepareStatement(query);

            statement.setString(1, user.getFullName());
            statement.setString(2, user.getCurrentAddress());
            statement.setString(3, user.getContactNumber());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getEducation());
            statement.setString(6, user.getGender());
            statement.setString(7, user.getAvailableDate());
            statement.setString(8, user.getDesiredPosition());
            statement.setString(9, user.getDesiredSalary());
            statement.setString(10, user.getAuthorizedWork());
            statement.setString(11, user.getRelativesWorking());
            statement.setString(12, user.getExplanation());

            return statement.executeUpdate();

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }
}