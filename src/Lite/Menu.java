package Lite;

import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

/**
 * @author Nowroz Islam on 12/1/2017
 * @project Lite
 */
public class Menu {
    static Button create,open;
    static HBox buttonBox;
    static void initiate(Stage primaryStage){
        create=new Button("Create");
        open = new Button("Open");
        open.setOnAction(e->open(primaryStage));
        create.setOnAction(e->create(primaryStage));
        buttonBox=new HBox(5,create,open);
    }
    static void open(Stage primaryStage){
        FileChooser chooser=new FileChooser();
        File file=chooser.showOpenDialog(primaryStage);
        if(file!=null){
            String path=file.getAbsolutePath();
            Connection.open(path);
        }

    }
    static void create(Stage primaryStage){
        String DBName=selectName();
        if(DBName!=null){
            DirectoryChooser chooser=new DirectoryChooser();
            File file=chooser.showDialog(primaryStage);
            if(file!=null){
                String path=file.getAbsolutePath();
                Connection.create(path,DBName);
            }
        }
    }
    static String selectName(){
        TextInputDialog dialog = new TextInputDialog("database");
        dialog.setTitle(null);
        dialog.setHeaderText(null);
        dialog.setContentText("Name without extension:");
        Optional<String> result = dialog.showAndWait();
        try{
            return result.get();
        }catch(Exception e){
            return null;
        }
    }

}
