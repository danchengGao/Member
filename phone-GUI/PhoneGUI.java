import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

// imports added by the solution
import javafx.scene.layout.TilePane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Pos;

public class PhoneGUI extends Application
{
    // the label to display number when a button is pressed
    private Label myLabel = new Label("");
 
    /**
    * The start method is the main entry point for every JavaFX application. 
    * It is called after the init() method has returned and after 
    * the system is ready for the application to begin running.
    *
    * @param  stage the primary stage for this application.
    */
    @Override
    public void start(Stage stage)
    {     
        // create a new tile pane, with hgap 10 and vgap 10
        //TilePane tiles = new TilePane(10,10);
        TilePane tiles = new TilePane();
        //tiles.setPadding(new Insets(10, 10, 10, 10));
        tiles.setId("tiles");
        // set the preferred number of buttons in a row to be 3
        //tiles.setPrefColumns(3);
    
        // add 13 buttons into the pane
        for (int i = 1; i <= 13; i++) {                     
            // create a Button
            Button myButton = new Button("");
            myButton.setMinSize(100, 100);
            // set alignment of text on the button to be in the center
            //myButton.setTextAlignment(TextAlignment.CENTER);
        
            // construct text displayed on the button
            String numberText = "";
            String letterText = "";         
                    
            if (i == 1) {
                // text for the first button
                numberText = String.valueOf(i);                
                // mouse click action: display value on label
                myButton.setOnAction(
                    (ActionEvent event) -> {
                        String newText = myLabel.getText();
                        newText = newText + Integer.toString(1);
                        myLabel.setText(newText);
                    }                
                );
            }            
            else if (i <= 9) {
                // text for buttons 2-9
                switch (i) {
                    case 2:
                        letterText = "ABC";
                        break;
                    case 3: 
                        letterText = "DEF";
                        break;
                    case 4:
                        letterText = "GHI";
                        break;
                    case 5:
                        letterText = "JKL";
                        break;
                    case 6:
                        letterText = "MNO";
                        break;
                    case 7:
                        letterText = "PQRS";
                        break;
                    case 8:
                        letterText = "TUV";
                        break;
                    case 9:
                        letterText = "WXYZ";
                        break;
                }
                // "\n" line breaker: to make sure that the value and the letter are on different lines                
                //buttonText = buttonText + "\n" + String.valueOf(i);
                numberText = String.valueOf(i); 
                // mouse click action: display value on label
                Integer value = new Integer(i);
                myButton.setOnAction(
                    (event) -> {
                        String newText = myLabel.getText();                        
                        newText = newText + Integer.toString(value);
                        myLabel.setText(newText);
                    }
                );
            }
            else if (i == 10) {
                // text for button 10
                numberText = "*";
                // mouse hover action: reverse the text on the label
                myButton.setOnMouseEntered(
                    (MouseEvent event) -> {
                        // StringBuilder class: unlike String whose character order can not be altered directly, StringBuilder is a muatble sequence of characters
                        // this means that characters can be reordered without creating a new StringBuilder instance 
                        StringBuilder s = new StringBuilder(myLabel.getText());
                        // reverse the character sequence and convert to String
                        String newText = s.reverse().toString();                        
                        myLabel.setText(newText);
                    }
                );
            }
            else if (i == 11) {
                // text for button 11
                numberText = "0";
                // mouse click action: display value on label
                myButton.setOnAction(
                    (ActionEvent event) -> {
                        String newText = myLabel.getText();
                        newText = newText + Integer.toString(0);
                        myLabel.setText(newText);
                    }
                );
            }
            else if (i == 12) {
                // text for button 12
                numberText = "#";
                // mouse click action: clear the text on label
                myButton.setOnAction(
                    (ActionEvent event) -> {
                        myLabel.setText("");
                    }
                );
            }
            else if (i == 13) {
                myButton.setId("dialButton");
            }
            
            //myButton.setText(buttonText);
            Label numberLabel = new Label("");            
            numberLabel.setText(numberText);

            VBox buttonBox = new VBox(numberLabel);
            buttonBox.setId("buttonBox");
            
            if(!letterText.equals("")) {
                Label letterLabel = new Label(""); 
                letterLabel.setText(letterText);
                letterLabel.getStyleClass().add("letterLabel");
                buttonBox.getChildren().addAll(letterLabel);
            }
            
            myButton.setGraphic(buttonBox);
            
            tiles.getChildren().add(myButton);
        }     
        
        tiles.setMinWidth(20 * 4 + 3 * tiles.getTileWidth());
        
        // HBox makes sure that the widths of its children do not grow wider than their preferred width
        // tile pane preferred width = prefColumns(i.e. the no. of buttons in a row) * tile width(i.e. width of a single button) 
        // by making the tile pane a child of an HBox, it is guaranteed that there will not be more than 3 buttons in a row even when resizing the window
        // Check the official documentations for HBox and TilePane to learn the behaviours of these two kinds of panes
        // HBox: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/HBox.html
        // TilePane: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/TilePane.html        
        HBox hbox = new HBox(tiles);
        // to make sure that the tile pane is aligned in the center of the screen horizontally
        hbox.setAlignment(Pos.CENTER);
        
        // the label is aligned above the hbox that is holding the tile pane
        VBox vbox = new VBox(myLabel, hbox);
        // to make sure that the label and the HBox is aligned in the center of the screen vertically
        vbox.setAlignment(Pos.CENTER);
        
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(vbox);
        scene.getStylesheets().add("style.css");
        
        stage.setTitle("PhoneGUI example");
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();
    }
}
