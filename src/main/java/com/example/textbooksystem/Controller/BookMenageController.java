package com.example.textbooksystem.Controller;

/**
 * @author 叶鸣镝
 * <p>
 * description:
 */

import com.example.textbooksystem.Domain.Book;
import com.example.textbooksystem.util.ConnectionTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookMenageController {
    @FXML
    protected TableView<Book> BookTable;
    @FXML
    protected TableColumn<Book, Integer> Cbook_id;
    @FXML
    protected TableColumn<Book, String> Cbook_name;
    @FXML
    protected TableColumn<Book, String> Cpublish;
    @FXML
    protected TableColumn<Book, Integer> Cuser_id;
    @FXML
    private TextField Abook_id;
    @FXML
    private TextField Aname;
    @FXML
    private TextField Apublish;
    @FXML
    private TextField Auser_id;
    @FXML
    private Button AddButton;
    @FXML
    private Button CancelButton;

    @FXML
    private Button LinkDataBase;

    public void CancelOnAction(ActionEvent event) {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }

//    public void InitColumn(){
//        ObservableList<Book> bookList = FXCollections.observableArrayList(
//                new Book(0,"null","null",0)
//        );
//        // Add your book objects to the bookList
//        BookTable=new TableView<>(bookList);
//        Cbook_id = new TableColumn<>("教材编号"); // Initialize the TableColumn
//        Cbook_id.setCellValueFactory(new PropertyValueFactory<>("book_id"));
//        // Set the text for the TableColumn
//        Cbook_id.setText("教材编号");
//        Cbook_name=new TableColumn<>("教材名称");
//        Cbook_name.setCellValueFactory(new PropertyValueFactory<>("book_name"));
//        Cbook_name.setText("教材名称");
//        Cpublish= new TableColumn<>("出版社");
//        Cpublish.setCellValueFactory(new PropertyValueFactory<>("publish"));
//        Cpublish.setText("出版社");
//        Cuser_id=new TableColumn<>("管理员编号");
//        Cuser_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
//        Cuser_id.setText("管理员编号");
//    }


    public void AddOnAction(ActionEvent event) {
        // Get the input values
        int bookId = 0;
        int userId = 0;
        ConnectionTest connectionTest=new ConnectionTest();
        Connection connection = connectionTest.getConnection();
        PreparedStatement statement=null;
        // Check if the input fields are not empty
        if (!Abook_id.getText().isEmpty() && !Auser_id.getText().isEmpty()) {
            // Parse the input values
            bookId = Integer.parseInt(Abook_id.getText());
            userId = Integer.parseInt(Auser_id.getText());
        }
        // Get the input values for name and publish
        String name = Aname.getText();
        String publish = Apublish.getText();

        //连接数据库
        String insert="insert into book( book_id,book_name,publish,user_id ) values(?,?,?,?)";
        try {
            statement=connection.prepareStatement(insert);
            statement.setInt(1,bookId);
            statement.setString(2,name);
            statement.setString(3,publish);
            statement.setInt(4,userId);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }






        // Create a new Book object
        Book newBook = new Book(bookId, name, publish, userId);

        //一定要和Book类中的相对应
        Cbook_id.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        Cbook_name.setCellValueFactory(new PropertyValueFactory<>("book_name"));
        Cpublish.setCellValueFactory(new PropertyValueFactory<>("publish"));
        Cuser_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));

        // Add the new book to the table view
        BookTable.getItems().add(newBook);
        // 写完之后清除Add里面的文字，不要重复添加
        Abook_id.clear();
        Aname.clear();
        Apublish.clear();
        Auser_id.clear();
    }

    public void LinkOnAction(){
        ConnectionTest connectionTest=new ConnectionTest();
        Connection connection = connectionTest.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String sql="select * from book";
        try{
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                int bookId= resultSet.getInt(1);
                String bookName=resultSet.getString(2);
                String publish=resultSet.getString(3);
                int userId=resultSet.getInt(4);
                Book book=new Book(bookId,bookName,publish,userId);
                Cbook_id.setCellValueFactory(new PropertyValueFactory<>("book_id"));
                Cbook_name.setCellValueFactory(new PropertyValueFactory<>("book_name"));
                Cpublish.setCellValueFactory(new PropertyValueFactory<>("publish"));
                Cuser_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
                BookTable.getItems().add(book);
            }

        }catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }finally {
            try {
                statement.close();
                resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }
}

