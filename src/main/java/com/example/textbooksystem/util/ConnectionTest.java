package com.example.textbooksystem.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author 叶鸣镝
 * <p>
 * description:
 */

public class ConnectionTest {
    public Connection Link;
    public Connection getConnection(){
        try {
            Class.forName("com.example.textbooksystem.util.ConnectionTest");
            Link=DriverManager.getConnection("jdbc:mysql://localhost:3306/software", "root", "42391523");
        }catch (Exception e){
            e.printStackTrace();
        }
        return Link;
    }

}
