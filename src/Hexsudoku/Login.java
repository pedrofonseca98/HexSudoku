package Hexsudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Login implements Initializable {

    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

    @FXML
    private Button register, login;

    private void Login() {

    }

    @FXML
    private void Register(ActionEvent event) throws FileNotFoundException, IOException {

        String username = user.getText();
        String password = pass.getText();
        if (username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo(s) vazio(s).", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }
        File o = new File("utilizadores");
        if (!o.exists()) {
            o.createNewFile();
        }
        FileReader f = new FileReader("utilizadores");
        BufferedReader br = new BufferedReader(f);
        String sCurrentLine;
        boolean flag = false;

        while ((sCurrentLine = br.readLine()) != null) {
            if (sCurrentLine.equals(username)) {
                flag = true;
                break;
            } else {
                sCurrentLine = br.readLine();
            }
        }
        if (!flag) {
            try {
                FileWriter fw = new FileWriter(o, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter writer = new PrintWriter(bw);
                writer.append(username + "\n");
                writer.append(password + "\n");
                writer.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Este utilizador já existe!", "ERRO", JOptionPane.WARNING_MESSAGE);
        }

    }

    @FXML
    private void Login(ActionEvent event) throws FileNotFoundException, IOException {
        String username = user.getText();
        String password = pass.getText();

        if (username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo(s) vazio(s).", "ERRO", JOptionPane.WARNING_MESSAGE);
            return;
        }

        File o = new File("utilizadores");
        if (!o.exists()) {
            o.createNewFile();
        }
        FileReader f = new FileReader("utilizadores");
        BufferedReader br = new BufferedReader(f);
        String sCurrentLine;
        boolean flag = false;

        while ((sCurrentLine = br.readLine()) != null) {
            if (sCurrentLine.equals(username)) {
                flag = true;
                sCurrentLine = br.readLine();
                if (sCurrentLine.equals(password)) {
                    File t = new File("Times");
                    if (!t.exists()) {
                        t.createNewFile();
                    }
                    FileWriter fw = new FileWriter(t, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter writer = new PrintWriter(bw);
                    writer.append("Tempo jogador " + username + "\n");
                    writer.close();
                    bw.close();
                    fw.close();
                    final Node source = (Node) event.getSource();
                    Stage s = (Stage) source.getScene().getWindow();
                    s.close();

                    //abrir nova janela
                    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("HexSudoku");
                    stage.setScene(scene);
                    stage.show();

                } else {
                    JOptionPane.showMessageDialog(null, "Password incorreta.", "ERRO", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }
        if (!flag) {
            JOptionPane.showMessageDialog(null, "Este utilizador não existe. Por favor registe-se.", "ERRO", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
