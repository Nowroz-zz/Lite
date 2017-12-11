package Lite;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.sql.*;

/**
 * @author Nowroz Islam on 12/2/2017
 * @project Lite
 */
public class SchemaTab {
    static Tab schemaTab;
    static ObservableList<String> data = FXCollections.observableArrayList();

    static void initiate() {
        schemaTab = new Tab("Structure");
        AnchorPane pane = new AnchorPane();
        pane.setMinWidth(780);
        Button refresh = new Button("Refresh");
        AnchorPane.setRightAnchor(refresh, 2.0);
        AnchorPane.setBottomAnchor(refresh, 2.0);
        if(Connection.conn!=null){
            try {
                Statement statement = Connection.conn.createStatement();
                DatabaseMetaData md = Connection.conn.getMetaData();
                ResultSet rs = md.getTables(null, null, "%", null);
                while (rs.next()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    String table = rs.getString(3);
                    ResultSet schema = statement.executeQuery("select * from " + table);
                    ResultSetMetaData metaData = schema.getMetaData();
                    stringBuilder.append(table);
                    stringBuilder.append(" ( ");
                    for (int i = 1; i <= metaData.getColumnCount() - 1; i++) {
                        stringBuilder.append(metaData.getColumnName(i));
                        stringBuilder.append(" ");
                        stringBuilder.append(metaData.getColumnTypeName(i));
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(metaData.getColumnName(metaData.getColumnCount()));
                    stringBuilder.append(" ");
                    stringBuilder.append(metaData.getColumnTypeName(metaData.getColumnCount()));
                    stringBuilder.append(" )");
                    data.add(stringBuilder.toString());
                }
            }catch (SQLException e){

            }
        }
        refresh.setOnAction(e->{
            data.clear();
            initiate();
        });
        ListView<String> structure = new ListView<>(data);
        structure.setPrefWidth(710);
        pane.getChildren().addAll(structure, refresh);
        schemaTab.setContent(pane);
    }
}
