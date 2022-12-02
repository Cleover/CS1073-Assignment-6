
/**
 A JavaFX GUI application to guess passwords, 
 counting incorrect guesses.
 @author Cleo Valerie Creighton 3727046
*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;

public class PasswordGuesser extends Application {

    private String password;

    private TextField guess;

    private Text status;

    private Text pos[] = new Text[5];
    private int guesses[] = new int[5];

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Password Guesser");

        Label guessLabel = new Label("Guess a 5-letter password!");
        Label noticeLabel = new Label("(All Capital Letters))");

        guess = new TextField();
        guess.setPrefWidth(180);
        guess.setOnAction(this::guess);

        Button guessButton = new Button("Guess!");
        guessButton.setOnAction(this::guess);

        Button resetButton = new Button("New Password");
        resetButton.setOnAction(this::reset);

        status = new Text("---------------------");
        pos[0] = new Text("Pos 0: 0 wrong guesses");
        pos[1] = new Text("Pos 1: 0 wrong guesses");
        pos[2] = new Text("Pos 2: 0 wrong guesses");
        pos[3] = new Text("Pos 3: 0 wrong guesses");
        pos[4] = new Text("Pos 4: 0 wrong guesses");

        FlowPane pane = new FlowPane(guessLabel, noticeLabel, guess, guessButton, resetButton, status, pos[0], pos[1],
                pos[2], pos[3], pos[4]);

        pane.setAlignment(Pos.CENTER);
        pane.setHgap(30);
        pane.setVgap(10);

        Scene scene = new Scene(pane, 250, 375);
        primaryStage.setScene(scene);
        primaryStage.show();

        generatePassword();
    }

    public void generatePassword() {
        String newPassword = "";

        for (int i = 0; i < 5; i++) {
            int choosenChar = (int) Math.floor(Math.random() * 26) + 65;
            newPassword += (char) choosenChar;
        }

        password = newPassword;
    }

    public void guess(ActionEvent event) {
        String guessedPassword = guess.getText();
        String ansOutput = "";
        boolean failed = false;

        int checkCount = guessedPassword.length();
        if (checkCount > 5)
            checkCount = 5;
        else if (checkCount < 1)
            failed = true;

        for (int i = 0; i < checkCount; i++) {
            int guessedCheck = guessedPassword.charAt(i);
            int passwordCheck = password.charAt(i);

            if (guessedCheck == passwordCheck) {
                ansOutput += "✓";
            } else {
                if (guessedCheck > passwordCheck)
                    ansOutput += "v";
                else
                    ansOutput += "^";
                guesses[i] += 1;
                pos[i].setText("Pos " + i + ": " + guesses[i] + " wrong guesses");
                failed = true;
            }
        }

        if (failed)
            status.setText(ansOutput + "\tGuess Again!");
        else
            status.setText("Congratulations! You guessed it");

    }

    public void reset(ActionEvent event) {
        generatePassword();
        guesses = new int[5];
        guess.setText("");
        status.setText("---------------------");
        pos[0].setText("Pos 0: 0 wrong guesses");
        pos[1].setText("Pos 1: 0 wrong guesses");
        pos[2].setText("Pos 2: 0 wrong guesses");
        pos[3].setText("Pos 3: 0 wrong guesses");
        pos[4].setText("Pos 4: 0 wrong guesses");
    }

} // end PasswordGuesser,
