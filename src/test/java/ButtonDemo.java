
import java.util.Date;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class ButtonDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
    	Date d = new Date();
    	System.out.println(d);
    /*    primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
      //  Hbox hbox = new Hbox();
       final  StackPane root = new StackPane();
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	root.getChildren().remove(0);
                System.out.println("Hello World!");
            }
        });
        
        
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();*/
    }
}
