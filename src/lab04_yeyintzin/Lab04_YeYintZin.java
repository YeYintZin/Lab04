package lab04_yeyintzin;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Lab04_YeYintZin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Business Expenses");
        GridPane root = new GridPane();
        
        TextField days = new TextField();
        Label daysLabel = new Label("Number of days on the trip:");
        root.add(days, 1, 0);
        root.add(daysLabel, 0, 0);

        TextField airfare = new TextField();
        Label airfareLabel = new Label("Amount of airfare:");
        root.add(airfare, 1, 1);
        root.add(airfareLabel, 0, 1);

        TextField carRental = new TextField();
        Label carRentalLabel = new Label("Car Rental fees:");
        root.add(carRental, 1, 2);
        root.add(carRentalLabel, 0, 2);

        TextField miles = new TextField();
        Label milesLabel = new Label("Miles Driven:");
        root.add(miles, 1, 3);
        root.add(milesLabel, 0, 3);   

        TextField parking = new TextField();
        Label parkingLabel = new Label("Parking Fees:");
        root.add(parking, 1, 4);
        root.add(parkingLabel, 0, 4);

        TextField taxi = new TextField();
        Label taxiLabel = new Label("Taxi Fees:");
        root.add(taxi, 1, 5);
        root.add(taxiLabel, 0, 5);   

        TextField registration = new TextField();
        Label registrationLabel = new Label("Conference or Seminar fees:");
        root.add(registration, 1, 6);
        root.add(registrationLabel, 0, 6);

        TextField lodging = new TextField();
        Label lodgingLabel = new Label("Lodging Charges/Night :");
        root.add(lodging, 1, 7);
        root.add(lodgingLabel, 0, 7);
        
        EventHandler gray = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (!carRental.getText().isEmpty()) {
                    miles.setDisable(true);
                } else {
                    miles.setDisable(false);
                }
            
                if (!miles.getText().isEmpty()) {
                    carRental.setDisable(true);
                } else {
                    carRental.setDisable(false);
                }
            }
        };
        
        Label expenses = new Label("");
        Label allowableExpenses = new Label("");
        Label excess = new Label("");                   
        Button button = new Button("Calculate");
        button.setOnAction(e -> {
            double num;
            double totalAllowed;
            if (!carRental.getText().isEmpty()) {
                num = Integer.parseInt(carRental.getText());
                totalAllowed = 0;
            } else {
                num = Integer.parseInt(miles.getText()) * 0.27;
                totalAllowed = num;
            }
            
            double totalExpenses = 
                Integer.parseInt(airfare.getText()) +
                num +
                Integer.parseInt(parking.getText()) +
                Integer.parseInt(taxi.getText()) +
                Integer.parseInt(registration.getText()) +
                Integer.parseInt(lodging.getText());
                expenses.setText(
                "The total expense is " +
                totalExpenses +
                "$"
            );
            
            totalAllowed += 162 * Integer.parseInt(days.getText());
            allowableExpenses.setText(
                    "The total allowable expense is "
                    + totalAllowed 
                    + "$"
            );
            
            if (totalAllowed < totalExpenses) {
                excess.setText(
                    "Amount due: " +
                    (totalExpenses - totalAllowed) +
                    "$"
                );
            } else {
                excess.setText(
                    "Amount saved: " +
                    (totalAllowed - totalExpenses) +
                    "$"
                );
            }
        });
        root.add(button, 0, 8);
        root.add(expenses, 0, 9);
        root.add(allowableExpenses, 0, 10);
        root.add(excess, 0, 11);
        
        carRental.setOnKeyReleased(gray);
        miles.setOnKeyReleased(gray);
        
        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add("demo2.css");
        stage.setScene(scene);
        stage.show();
    }
    
}
