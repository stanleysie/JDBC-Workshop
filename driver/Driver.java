/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import controller.TableController;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Master;

/**
 *
 * @author Stanley Sie
 */
public class Driver extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Master master = new Master();
        TableController controller = new TableController(master, primaryStage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
