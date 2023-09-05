package com.example.textbooksystem.Controller;

import com.example.textbooksystem.Main;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

/**
 * @author 叶鸣镝
 * <p>
 * description:
 */

public class RegisterController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField SexField;
    @FXML
    private TextField idField;
    @FXML
    private TextField identyField;
    @FXML
    private PasswordField firstPasswordField;
    @FXML
    private PasswordField lastPasswordField;
    @FXML
    private Button registerButton;
    @FXML
    private Button returnButton;
    @FXML
    private Label registerLabel;

    public void returnOnAction(ActionEvent event) throws IOException {
        Stage closeStage= (Stage) returnButton.getScene().getWindow();
        closeStage.close();
    }

    //有待测验
//    public boolean validId(){
//        ConnectionTest connectionTest=new ConnectionTest();
//        Connection connection = connectionTest.getConnection();
//        int id= Integer.parseInt(idField.getText());
//        String query="select count(1) from user where ?=id";
//        PreparedStatement statement=null;
//        ResultSet resultSet=null;
//        try {
//            statement=connection.prepareStatement(query);
//            statement.setInt(1,id);
//            while(resultSet.next()){
//                if(resultSet.getInt(1)==1){
//                    registerLabel.setText("账号已经注册");
//                    return false;
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            e.getCause();
//        }
//        return true;
//    }

    public void InsertMenage(){
        ConnectionTest connectionTest=new ConnectionTest();
        Connection connection = connectionTest.getConnection();
        int id= Integer.parseInt(idField.getText());
        int identy= Integer.parseInt(identyField.getText());
        String name=nameField.getText();
        String Sex=SexField.getText();
        String firstPassword=firstPasswordField.getText();
        String lastPassword=lastPasswordField.getText();
        String insert="insert into user (id,name,Sex,identy,password) values (?,?,?,?,?)";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(insert);
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.setString(3,Sex);
            statement.setInt(4,identy);
            System.out.println(firstPassword+lastPassword+(firstPassword.equals(lastPassword)));
            if(firstPassword.equals(lastPassword)){
                statement.setString(5,lastPassword);
                statement.executeUpdate();
                registerLabel.setText("注册成功");
            }else {
                registerLabel.setText("前后密码不一致");//有bug!!
            }
        }catch (SQLIntegrityConstraintViolationException exception){
            registerLabel.setText("账号已被注册");
            exception.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }


        }

    }




}
