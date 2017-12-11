package Lite;

import javafx.scene.control.Alert;

import java.sql.*;

/**
 * @author Nowroz Islam on 12/1/2017
 * @project Lite
 */
public class Connection {
    static java.sql.Connection conn;
    static void open(String path){
        try{
            close();
            conn= DriverManager.getConnection("jdbc:sqlite:"+path);
        }catch(SQLException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oops!");
            alert.setHeaderText(null);
            alert.setContentText("Couldn't open the database: "+e);
            alert.showAndWait();
        }

    }
    static void create(String path,String DBName){
        try{
            close();
            path=path+"\\"+DBName+".db";
            conn= DriverManager.getConnection("jdbc:sqlite:"+path);
        }catch(SQLException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oops!");
            alert.setHeaderText(null);
            alert.setContentText("Couldn't create the database: "+e);
            alert.showAndWait();
        }
    }
    static void close(){
        try{
            if(conn!=null){
                conn.close();
            }
        }catch(SQLException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oops!");
            alert.setHeaderText(null);
            alert.setContentText("Couldn't close the database: "+e);
            alert.showAndWait();
        }
    }

}
