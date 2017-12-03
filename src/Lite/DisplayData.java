package Lite;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author Nowroz Islam on 12/3/2017
 * @project Lite
 */
public class DisplayData {
    static ObservableList<String> dataList= FXCollections.observableArrayList();
    public static void display(ResultSet resultSet,String tableName){
        StringBuilder row=new StringBuilder();
        try{
            dataList.clear();
            ResultSetMetaData metaData=resultSet.getMetaData();
            for(int i=1;i<=metaData.getColumnCount();i++){
                row.append(metaData.getColumnName(i));
                row.append(" | ");
            }
            dataList.add(row.toString());
            while(resultSet.next()){
                row.setLength(0);
                for(int i=1;i<=metaData.getColumnCount();i++){
                    row.append(resultSet.getString(i));
                    row.append(" | ");
                }
                dataList.add(row.toString());
            }

        }catch (SQLException e){

        }
        ListView<String> displayData=new ListView<>(dataList);
        displayData.setPrefWidth(710);
        AnchorPane pane=new AnchorPane(displayData);
        Scene scene=new Scene(pane);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("Data List");
        stage.show();

    }
}
