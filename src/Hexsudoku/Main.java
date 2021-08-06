package Hexsudoku;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Main implements Initializable {

    @FXML
    private GridPane table;
    @FXML
    private Button pause;
    @FXML
    private Label time, descricao, grupo, pausa;
    @FXML
    private ImageView logo;
    @FXML
    private int verify_enable = 0;

    Timer t;
    int seconds = 0;
    int x;
    String Stime;

    String[][] board;
    String[][] pre_board1 = new String[][]{
        {"0", "5", "3", "8", "6", "1", "4", "A", "2", "D", "C", "B", "9", "7", "E", "F"},
        {"6", "C", "E", "9", "7", "2", "8", "B", "0", "5", "1", "F", "4", "A", "D", "3"},
        {"D", "1", "F", "A", "3", "C", "9", "5", "6", "E", "7", "4", "0", "8", "2", "B"},
        {"2", "7", "4", "B", "E", "D", "F", "0", "A", "9", "8", "3", "6", "1", "5", "C"},
        {"E", "F", "7", "6", "8", "5", "A", "D", "4", "1", "3", "2", "B", "C", "0", "9"},
        {"A", "8", "D", "3", "0", "9", "C", "F", "5", "7", "B", "6", "2", "E", "4", "1"},
        {"1", "B", "0", "C", "2", "3", "6", "4", "D", "8", "9", "E", "A", "5", "F", "7"},
        {"5", "4", "9", "2", "B", "E", "1", "7", "F", "A", "0", "C", "D", "3", "6", "8"},
        {"8", "0", "5", "C", "A", "7", "2", "E", "3", "6", "F", "9", "C", "B", "1", "4"},
        {"3", "E", "6", "1", "5", "B", "D", "C", "8", "2", "4", "7", "F", "9", "A", "0"},
        {"9", "A", "C", "F", "1", "4", "0", "8", "E", "B", "5", "D", "7", "2", "3", "6"},
        {"7", "2", "B", "4", "F", "6", "3", "9", "C", "0", "A", "1", "5", "D", "8", "E"},
        {"C", "6", "A", "0", "4", "8", "7", "2", "9", "3", "E", "5", "1", "F", "B", "D"},
        {"F", "3", "8", "5", "9", "A", "B", "1", "7", "4", "D", "0", "E", "6", "C", "2"},
        {"B", "D", "2", "7", "C", "0", "E", "3", "1", "F", "6", "A", "8", "4", "9", "5"},
        {"4", "9", "1", "E", "D", "F", "5", "6", "B", "C", "2", "8", "3", "0", "7", "A"},};

    String[][] pre_board2 = new String[][]{
        {"8", "3", "B", "E", "4", "5", "9", "A", "F", "C", "D", "1", "6", "2", "0", "7"},
        {"5", "4", "1", "0", "2", "F", "C", "8", "A", "9", "7", "6", "B", "D", "3", "E"},
        {"2", "A", "9", "6", "E", "B", "D", "7", "0", "3", "4", "5", "1", "F", "8", "C"},
        {"D", "C", "F", "7", "1", "0", "6", "3", "B", "2", "E", "8", "4", "5", "A", "9"},
        {"F", "6", "C", "9", "3", "D", "8", "B", "E", "A", "2", "0", "7", "4", "1", "5"},
        {"E", "1", "8", "D", "6", "9", "4", "0", "7", "5", "B", "C", "3", "A", "F", "2"},
        {"0", "2", "A", "4", "F", "7", "5", "C", "3", "6", "1", "9", "E", "8", "B", "D"},
        {"7", "5", "3", "B", "A", "E", "1", "2", "D", "8", "F", "4", "9", "C", "6", "0"},
        {"4", "E", "D", "8", "B", "1", "A", "9", "6", "F", "5", "7", "2", "0", "C", "3"},
        {"3", "B", "0", "A", "7", "6", "F", "5", "2", "1", "C", "D", "8", "E", "9", "4"},
        {"6", "F", "2", "5", "C", "8", "3", "E", "4", "0", "9", "A", "D", "1", "7", "B"},
        {"1", "9", "7", "C", "0", "4", "2", "D", "8", "E", "3", "B", "A", "6", "5", "F"},
        {"A", "7", "6", "1", "D", "3", "B", "F", "5", "4", "0", "2", "C", "9", "E", "8"},
        {"9", "D", "4", "F", "5", "A", "E", "1", "C", "7", "8", "3", "0", "B", "2", "6"},
        {"C", "8", "E", "3", "9", "2", "0", "4", "1", "B", "6", "F", "5", "7", "D", "A"},
        {"B", "0", "5", "2", "8", "C", "7", "6", "9", "D", "A", "E", "F", "3", "4", "1"},};

    String[][] pre_board3 = new String[][]{
        {"7", "D", "0", "C", "6", "B", "A", "8", "4", "9", "3", "2", "5", "1", "F", "E"},
        {"9", "5", "A", "8", "F", "E", "C", "2", "1", "B", "7", "6", "3", "4", "D", "0"},
        {"B", "F", "3", "6", "4", "1", "7", "0", "D", "E", "5", "C", "A", "8", "9", "2"},
        {"4", "E", "1", "2", "5", "9", "3", "D", "F", "8", "A", "0", "B", "6", "C", "7"},
        {"A", "8", "5", "0", "3", "4", "9", "7", "2", "6", "B", "1", "F", "C", "E", "D"},
        {"6", "7", "4", "3", "A", "D", "2", "C", "E", "F", "0", "5", "9", "B", "8", "1"},
        {"1", "C", "2", "F", "E", "6", "5", "B", "9", "D", "4", "8", "7", "3", "0", "A"},
        {"E", "9", "B", "D", "1", "8", "0", "F", "A", "7", "C", "3", "2", "5", "6", "4"},
        {"D", "4", "9", "A", "2", "0", "6", "3", "8", "1", "E", "B", "C", "F", "7", "5"},
        {"8", "0", "F", "7", "C", "5", "4", "1", "6", "A", "2", "9", "E", "D", "3", "B"},
        {"3", "6", "E", "1", "9", "F", "B", "A", "5", "C", "D", "7", "4", "0", "2", "8"},
        {"5", "2", "C", "B", "8", "7", "D", "E", "0", "3", "F", "4", "1", "9", "A", "6"},
        {"2", "1", "D", "9", "0", "3", "F", "4", "7", "5", "8", "E", "6", "A", "B", "C"},
        {"0", "B", "6", "E", "7", "A", "8", "5", "C", "4", "9", "F", "D", "2", "1", "3"},
        {"C", "3", "7", "4", "D", "2", "1", "9", "B", "0", "6", "A", "8", "E", "5", "F"},
        {"F", "A", "8", "5", "B", "C", "E", "6", "3", "2", "1", "D", "0", "7", "4", "9"},};

    String[][] pre_board4 = new String[][]{
        {"4", "1", "2", "A", "7", "6", "D", "3", "F", "0", "B", "5", "E", "8", "9", "C"},
        {"B", "8", "3", "D", "5", "F", "C", "E", "7", "1", "6", "9", "A", "0", "2", "4"},
        {"7", "F", "5", "E", "0", "4", "9", "A", "C", "3", "2", "8", "D", "6", "1", "B"},
        {"0", "9", "C", "6", "B", "2", "8", "1", "D", "4", "E", "A", "5", "F", "3", "7"},
        {"A", "6", "1", "F", "C", "E", "3", "5", "4", "9", "D", "7", "0", "B", "8", "2"},
        {"3", "7", "8", "C", "2", "D", "6", "9", "0", "A", "5", "B", "4", "1", "E", "F"},
        {"D", "4", "0", "5", "8", "1", "F", "B", "2", "6", "C", "E", "9", "7", "A", "3"},
        {"E", "B", "9", "2", "4", "A", "0", "7", "3", "8", "1", "F", "6", "C", "D", "5"},
        {"1", "3", "F", "7", "E", "9", "5", "8", "B", "D", "0", "4", "2", "A", "C", "6"},
        {"2", "C", "B", "9", "A", "7", "4", "0", "5", "E", "8", "6", "3", "D", "F", "1"},
        {"6", "D", "E", "4", "1", "3", "B", "F", "A", "2", "7", "C", "8", "5", "0", "9"},
        {"8", "5", "A", "0", "D", "C", "2", "6", "9", "F", "3", "1", "B", "4", "7", "E"},
        {"C", "E", "D", "B", "9", "0", "A", "4", "6", "7", "F", "2", "1", "3", "5", "8"},
        {"5", "A", "4", "1", "3", "B", "7", "2", "8", "C", "9", "0", "F", "E", "6", "D"},
        {"9", "0", "6", "3", "F", "8", "E", "C", "1", "5", "4", "D", "7", "2", "B", "A"},
        {"F", "2", "7", "8", "6", "5", "1", "D", "E", "B", "A", "3", "C", "9", "4", "0"},};

    String[][] pre_board5 = new String[][]{
        {"5", "4", "1", "0", "2", "F", "C", "8", "A", "9", "7", "6", "B", "D", "3", "E"},
        {"8", "3", "B", "E", "4", "5", "9", "A", "F", "C", "D", "1", "6", "2", "0", "7"},
        {"2", "A", "9", "6", "E", "B", "D", "7", "0", "3", "4", "5", "1", "F", "8", "C"},
        {"D", "C", "F", "7", "1", "0", "6", "3", "B", "2", "E", "8", "4", "5", "A", "9"},
        {"F", "6", "C", "9", "3", "D", "8", "B", "E", "A", "2", "0", "7", "4", "1", "5"},
        {"0", "2", "A", "4", "F", "7", "5", "C", "3", "6", "1", "9", "E", "8", "B", "D"},
        {"E", "1", "8", "D", "6", "9", "4", "0", "7", "5", "B", "C", "3", "A", "F", "2"},
        {"7", "5", "3", "B", "A", "E", "1", "2", "D", "8", "F", "4", "9", "C", "6", "0"},
        {"4", "E", "D", "8", "B", "1", "A", "9", "6", "F", "5", "7", "2", "0", "C", "3"},
        {"3", "B", "0", "A", "7", "6", "F", "5", "2", "1", "C", "D", "8", "E", "9", "4"},
        {"6", "F", "2", "5", "C", "8", "3", "E", "4", "0", "9", "A", "D", "1", "7", "B"},
        {"1", "9", "7", "C", "0", "4", "2", "D", "8", "E", "3", "B", "A", "6", "5", "F"},
        {"A", "7", "6", "1", "D", "3", "B", "F", "5", "4", "0", "2", "C", "9", "E", "8"},
        {"9", "D", "4", "F", "5", "A", "E", "1", "C", "7", "8", "3", "0", "B", "2", "6"},
        {"B", "0", "5", "2", "8", "C", "7", "6", "9", "D", "A", "E", "F", "3", "4", "1"},
        {"C", "8", "E", "3", "9", "2", "0", "4", "1", "B", "6", "F", "5", "7", "D", "A"},};

    public void start() {

        if (seconds != 0) {
            seconds = 0;
            t.restart();
        } else {
            t = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent ae) {
                    seconds++;
                    Platform.runLater(() -> {
                        int hours = seconds / 3600;
                        int minutes = (seconds % 3600) / 60;
                        int sec = seconds % 60;

                        String timeString = String.format("%02d:%02d:%02d", hours, minutes, sec);
                        time.setText("Tempo: " + timeString);
                    });
                }
            });
            t.start();
        }
    }

    public void clickLogout(ActionEvent event) throws IOException {
        final Node source = (Node) event.getSource();
        Stage s = (Stage) source.getScene().getWindow();
        s.close();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void clickPause() {
        if (verify_enable == 1) {
            if (pause.getText().equals("Pause")) {
                t.stop();
                table.setVisible(false);
                pause.setText("Resume");
                pausa.setVisible(true);

                return;
            }
            if (pause.getText().equals("Resume")) {
                t.start();

                pausa.setVisible(false);
                table.setVisible(true);
                pause.setText("Pause");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Deverá primeiro começar um jogo.", "ERRO", JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    public void verificar() throws IOException {
        if (verify_enable == 1) {
            int complete = 0;

            int linha;
            int coluna;

            for (int row = 0; row <= 15; row++) {
                for (int col = 0; col <= 15; col++) {
                    TextField result = null;
                    ObservableList<Node> childrens = table.getChildren();
                    for (Node node : childrens) {
                        if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                            result = (TextField) node;
                            break;
                        }
                    }
                    TextField text = result;
                    for (coluna = 0; coluna <= 15; coluna++) {
                        if (coluna == col) {
                        } else if (text.getText().equals(board[row][coluna]) || text.getText().isEmpty()) {

                            complete = 1;
                            break;
                        }
                    }
                    for (linha = 0; linha <= 15; linha++) {
                        if (linha == row) {
                            
                        } else if (text.getText().equals(board[linha][col]) || text.getText().isEmpty()) {

                            complete = 1;
                            break;
                        }
                    }
                }
            }

            if (complete == 1) {
                t.stop();
                JOptionPane.showMessageDialog(null, "Existem campos errados ou por preencher!", "ERRO", JOptionPane.WARNING_MESSAGE);
                t.start();
                return;
            }

            table.setDisable(true);
            t.stop();

            Calculotempo();

          fileWriter();
        } else {

            JOptionPane.showMessageDialog(null, "Para verificar deve começar um jogo! ", "Informação", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void Calculotempo() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int sec = seconds % 60;

        Stime = String.format("%02d:%02d:%02d", hours, minutes, sec);

        JOptionPane.showMessageDialog(null, "Parabéns, acabou o nível em " + Stime + " !", "Informação", JOptionPane.PLAIN_MESSAGE);

    }

    public void board_maker(int d) {

        table.getChildren().clear();

        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                Random rand = new Random();
                int n = rand.nextInt(256);
                TextField text = new TextField();
                text.setPrefSize(100, 100);
                if (n >= d) {
                    
                    if ((row == 4 || row == 5 || row == 6 || row == 7 || row == 12 || row == 13 || row == 14 || row == 15)) {
                        text.setText("");
                        text.setStyle("-fx-background-color:#cce6ff;-fx-text-fill:#CC6600;-fx-font-weight: bold;-fx-border-color:#808080;-fx-opacity:1.0");
                    } else if ((row == 0 || row == 1 || row == 2 || row == 3 || row == 8 || row == 9 || row == 10 || row == 11)) {
                        text.setText("");
                        text.setStyle("-fx-background-color:#cce6ff;-fx-text-fill:CC6600;-fx-font-weight: bold;-fx-border-color:#808080;-fx-opacity:1.0");
                    } else {

                        text.setText("");
                        text.setStyle("-fx-text-fill:CC6600;-fx-font-weight: bold;-fx-border-color:#808080;-fx-opacity:1.0");
                    }
                } else {
                    
                    if ((row == 4 || row == 5 || row == 6 || row == 7 || row == 12 || row == 13 || row == 14 || row == 15)) {
                        text.setText(board[row][col]);
                        text.setDisable(true);
                        text.setStyle("-fx-background-color:#cce6ff;-fx-text-fill:#4D4D4D;-fx-border-color:#808080;-fx-opacity:1.0;-fx-font-weight:bold;");
                    } else if ((row == 0 || row == 1 || row == 2 || row == 3 || row == 8 || row == 9 || row == 10 || row == 11)) {
                        text.setText(board[row][col]);
                        text.setDisable(true);
                        text.setStyle("-fx-background-color:#cce6ff;-fx-text-fill:#4D4D4D;-fx-border-color:#808080;-fx-opacity:1.0;-fx-font-weight:bold;");

                    } else {
                        text.setText(board[row][col]);
                        text.setDisable(true);
                        text.setStyle("-fx-text-fill:#4D4D4D;-fx-border-color:#808080;-fx-opacity:1.0;-fx-font-weight:bold;");
                    }
                }

                text.setEditable(true);
                table.add(text, col, row);
            }
        }

        pause.setVisible(true);
        time.setVisible(true);
        table.setVisible(true);
        start();
    }

    public void fileWriter() throws IOException {
        File t = new File("Times");
        FileWriter fw = new FileWriter(t, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter writer = new PrintWriter(bw);

        if (x == 100) {
            writer.append("Normal:");
        }

        if (x == 75) {
            writer.append("Difícil: ");
        }

        if (x == 50) {
            writer.append("Extremo: ");
        }

        writer.append(Stime + "\n");
        writer.close();
        bw.close();
        fw.close();
    }

    public void board_choose() {

        Random n = new Random();
        System.out.println(n.nextInt(5));
        if (n.nextInt(5) == 0) {
            board = pre_board1;
        }
        if (n.nextInt(5) == 1) {
            board = pre_board2;

        }
        if (n.nextInt(5) == 2) {
            board = pre_board3;

        }

        if (n.nextInt(5) == 3) {
            board = pre_board4;

        }
        if (n.nextInt(5) == 4) {
            board = pre_board4;

        }

    }

    public void HandleButtonNormal(ActionEvent event) {
        grupo.setVisible(false);
        descricao.setVisible(false);
        logo.setVisible(false);
        pausa.setVisible(false);

        verify_enable = 1;

        table.setVisible(false);
        table.setDisable(false);
        board_choose();
        x = 100;
        board_maker(x);
    }

    public void HandleButtonDificil(ActionEvent event) {
        grupo.setVisible(false);
        descricao.setVisible(false);
        logo.setVisible(false);
        pausa.setVisible(false);

        verify_enable = 1;

        table.setVisible(true);
        table.setDisable(false);
        x = 75;

        board_choose();
        board_maker(x);
    }

    public void HandleButtonExtremo(ActionEvent event) {
        grupo.setVisible(false);
        descricao.setVisible(false);
        logo.setVisible(false);
        pausa.setVisible(false);

        verify_enable = 1;

        table.setVisible(true);
        table.setDisable(false);
        x = 50;
        board_choose();
        board_maker(x);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pausa.setVisible(false);

    }
}
