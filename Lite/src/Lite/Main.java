package Lite;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Nowroz Islam on 11/30/2017
 * @project Lite
 */
public class Main extends Application{
    public void start(Stage primaryStage){
        initiate();
        sender(primaryStage);
        AnchorPane root=new AnchorPane();
        VBox vBox=new VBox(10,Menu.buttonBox,_TabPane.tabPane);
        root.getChildren().addAll(vBox);
        Scene scene=new Scene(root);
        primaryStage.setTitle("Lite");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    void sender(Stage primaryStage){
        Menu.initiate(primaryStage);
    }
    void initiate(){
        _TabPane.initiate();
    }
}
