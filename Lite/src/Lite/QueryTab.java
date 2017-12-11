package Lite;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Nowroz Islam on 12/1/2017
 * @project Lite
 */
public class QueryTab {
    static Tab queryTab;
    static TextArea statementField;
    static Button run;
    static void initiate(){
        queryTab=new Tab("Execute SQL");
        AnchorPane pane=new AnchorPane();
        statementField=new TextArea("Write your query here");
        run=new Button("Run");
        AnchorPane.setBottomAnchor(run,2.0);
        AnchorPane.setRightAnchor(run,2.0);
        pane.getChildren().addAll(statementField,run);
        pane.setMinWidth(780);
        queryTab.setContent(pane);
        run.setDefaultButton(true);
        run.setOnAction(e->{
            try{
                if(Connection.conn!=null){
                    Statement statement=Connection.conn.createStatement();
                    String query=statementField.getText();
                    statement.execute(query);
                    String [] check=query.split(" ");
                    if (check[0].equals("select") || check[0].equals("SELECT")){
                        ResultSet resultSet=statement.getResultSet();
                        DisplayData.display(resultSet,check[2]);
                    }
                    statement.close();
                }
            }catch(SQLException ex){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Oops!");
                alert.setHeaderText(null);
                alert.setContentText("Can't execute the query: "+ex);
                alert.showAndWait();
            }
        });
    }
}
