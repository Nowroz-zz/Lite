package Lite;

import javafx.scene.control.TabPane;

/**
 * @author Nowroz Islam on 12/1/2017
 * @project Lite
 */
public class _TabPane{
    static TabPane tabPane;
    static void initiate(){
        QueryTab.initiate();
        SchemaTab.initiate();
        tabPane=new TabPane();
        tabPane.getTabs().addAll(SchemaTab.schemaTab,QueryTab.queryTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }
}
