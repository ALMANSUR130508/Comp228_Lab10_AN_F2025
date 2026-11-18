package application;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        UserDao userDao = new UserDao();

        GridPane grid = new GridPane();

        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label company = new Label("★ John Company");
        company.setStyle("-fx-font-size:18px; -fx-font-weight:bold;");

        Label title = new Label("Employment Application");
        title.setStyle("-fx-font-size:28px; -fx-font-weight:bold;");

        grid.add(company, 0, 0);
        grid.add(title, 1, 0);

        TextField fullName = new TextField();
        TextField address = new TextField();
        TextField contact = new TextField();
        TextField email = new TextField();
        TextField education = new TextField();
        TextField position = new TextField();
        TextField salary = new TextField();

        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        RadioButton other = new RadioButton("Other");

        ToggleGroup genderGroup = new ToggleGroup();

        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);
        other.setToggleGroup(genderGroup);

        VBox genderBox = new VBox(5);
        genderBox.getChildren().addAll(male, female, other);

        DatePicker availableDate = new DatePicker();

        RadioButton workYes = new RadioButton("Yes");
        RadioButton workNo = new RadioButton("No");

        ToggleGroup workGroup = new ToggleGroup();

        workYes.setToggleGroup(workGroup);
        workNo.setToggleGroup(workGroup);

        HBox workBox = new HBox(10);
        workBox.getChildren().addAll(workYes, workNo);

        RadioButton relativeYes = new RadioButton("Yes");
        RadioButton relativeNo = new RadioButton("No");

        ToggleGroup relativeGroup = new ToggleGroup();

        relativeYes.setToggleGroup(relativeGroup);
        relativeNo.setToggleGroup(relativeGroup);

        HBox relativeBox = new HBox(10);
        relativeBox.getChildren().addAll(relativeYes, relativeNo);

        Button signatureButton = new Button("Submit");

        

        grid.add(new Label("Full Name"), 0, 1);
        grid.add(fullName, 1, 1);

        grid.add(new Label("Current Address"), 0, 2);
        grid.add(address, 1, 2);

        grid.add(new Label("Contact Number"), 0, 3);
        grid.add(contact, 1, 3);

        grid.add(new Label("Email"), 0, 4);
        grid.add(email, 1, 4);

        grid.add(new Label("Education"), 0, 5);
        grid.add(education, 1, 5);

        grid.add(new Label("Gender"), 0, 6);
        grid.add(genderBox, 1, 6);

        grid.add(new Label("Available Date"), 0, 7);
        grid.add(availableDate, 1, 7);

        grid.add(new Label("Desired Position"), 0, 8);
        grid.add(position, 1, 8);

        grid.add(new Label("Desired Salary"), 0, 9);
        grid.add(salary, 1, 9);

        grid.add(new Label("Legally Authorized To Work"), 0, 10);
        grid.add(workBox, 1, 10);

        grid.add(new Label("Do You Have Relatives Working Here"), 0, 11);
        grid.add(relativeBox, 1, 11);

        grid.add(new Label("Signature"), 0, 12);
        grid.add(signatureButton, 1, 12);

        signatureButton.setOnAction(e -> {
        	if (fullName.getText().trim().isEmpty() ||
        		    address.getText().trim().isEmpty() ||
        		    contact.getText().trim().isEmpty() ||
        		    email.getText().trim().isEmpty() ||
        		    education.getText().trim().isEmpty() ||
        		    position.getText().trim().isEmpty() ||
        		    salary.getText().trim().isEmpty() ||
        		    availableDate.getValue() == null ||
        		    genderGroup.getSelectedToggle() == null ||
        		    workGroup.getSelectedToggle() == null ||
        		    relativeGroup.getSelectedToggle() == null) {

        		    Alert alert =
        		            new Alert(Alert.AlertType.ERROR);

        		    alert.setTitle("Error");
        		    alert.setHeaderText(null);
        		    alert.setContentText("Please complete all fields.");

        		    alert.showAndWait();

        		    return;
        		}
            User user = new User();

            String genderValue = "";

            if (male.isSelected()) {
                genderValue = "Male";
            } else if (female.isSelected()) {
                genderValue = "Female";
            } else if (other.isSelected()) {
                genderValue = "Other";
            }

            String workValue =
                    workYes.isSelected() ? "Yes" : "No";

            String relativeValue =
                    relativeYes.isSelected() ? "Yes" : "No";

            user.setFullName(fullName.getText());
            user.setCurrentAddress(address.getText());
            user.setContactNumber(contact.getText());
            user.setEmail(email.getText());
            user.setEducation(education.getText());

            user.setGender(genderValue);

            if (availableDate.getValue() != null) {

                user.setAvailableDate(
                        availableDate.getValue().toString());
            }

            user.setDesiredPosition(position.getText());
            user.setDesiredSalary(salary.getText());

            user.setAuthorizedWork(workValue);
            user.setRelativesWorking(relativeValue);

            user.setExplanation("Signed");

            try {

                int result = userDao.saveUser(user);

                if (result > 0) {

                    Alert alert =
                            new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Application Saved");

                    alert.showAndWait();
                }

            } catch (SQLException ex) {

                Alert alert =
                        new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Database Error");

                alert.showAndWait();

                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(grid, 850, 650);

        primaryStage.setTitle("Employment Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}