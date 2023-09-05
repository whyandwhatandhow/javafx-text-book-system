package com.example.textbooksystem.Controller;

import com.example.textbooksystem.Domain.Book;
import com.example.textbooksystem.util.ConnectionTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

/**
 * @author 叶鸣镝
 * <p>
 * description:
 */

public class IndexController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label LoginMessageLabel;
    private int username;
    private String password;

    public void CancelButtonOnAction(ActionEvent actionEvent){
        Stage stage= (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void LoginButtonOnAction(ActionEvent actionEvent){
        if(usernameField.getText().isBlank()==false&&passwordField.getText().isBlank()==false){
            validateLogin();
        }else {
            LoginMessageLabel.setText("请输入用户名密码");
        }
    }

    public void validateLogin(){
        username= Integer.parseInt(usernameField.getText());
        password=passwordField.getText();
        ConnectionTest connectionTest=new ConnectionTest();
        Connection connection = connectionTest.getConnection();
        PreparedStatement statement =null;
        ResultSet resultSet=null;
        String sql="select count(1) from user where id = ? and password = ?";
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1, username);
            statement.setString(2,password);
            resultSet=statement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()){
                if (resultSet.getInt(1)==1) {
                    LoginMessageLabel.setText("登陆成功");
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    CreateTableView();
                }
                else
                    LoginMessageLabel.setText("用户名密码错误");

                }
        }catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void CreateNewAccount() throws IOException {
        Stage stage=new Stage();
        Parent load = FXMLLoader.load(getClass().getResource("RegisterMenage.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(load,597,714));
        stage.show();
        CancelButtonOnAction(new ActionEvent());
    }

//    public void LinkDataBase(){
//        ConnectionTest connectionTest=new ConnectionTest();
//        BookMenageController bookMenageController=new BookMenageController();
//        Connection connection = connectionTest.getConnection();
//        PreparedStatement statement=null;
//        ResultSet resultSet=null;
//        String sql="select * from book ";
//        bookMenageController.InitColumn();
//        try {
//            statement=connection.prepareStatement(sql);
//            resultSet=statement.executeQuery();
//            int bookId=0;
//            String name=null;
//            String publish=null;
//            int userId=0;
//            while (resultSet.next()){
//                bookId=resultSet.getInt(1);
//                name=resultSet.getString(2);
//                publish=resultSet.getString(3);
//                userId=resultSet.getInt(4);
//                Book newBook = new Book(bookId, name, publish, userId);
//                //一定要和Book类中的相对应
//                bookMenageController.Cbook_id.setCellValueFactory(new PropertyValueFactory<>("book_id"));
//                bookMenageController.Cbook_name.setCellValueFactory(new PropertyValueFactory<>("book_name"));
//                bookMenageController.Cpublish.setCellValueFactory(new PropertyValueFactory<>("publish"));
//                bookMenageController.Cuser_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
//
//                // Add the new book to the table view
//                bookMenageController.BookTable.getItems().add(newBook);
//
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//            e.getCause();
//        }
//    }

    public void CreateTableView(){
        Stage stage=new Stage();
        try {
            Parent root=FXMLLoader.load(getClass().getResource("BookMenage.fxml"));
            stage.setScene(new Scene(root,1013,700));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            CancelButtonOnAction(new ActionEvent());
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
