/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg3;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;


public class MinesweeperGUI extends Application {
    
    private GridPane grid;
    private Button[][] buttons;
    private Minefield game;
    private Stage stage;
   
    
    @Override
    public void init() throws Exception {
        super.init();
        this.game = new Minefield(15, 10);
        this.game.populate(30);
        
    }
    
    
    
    private void refresh(int row, int column) {
        MineTile tile = game.getMineTile(row, column);
        Label l = new Label("You Win!");
        
        StackPane s = new StackPane(l);
        
        Scene sc = new Scene(s);
        
        
        if (game.areAllMinesFound()) {
            stage.setTitle("Congratulations!");
            stage.setScene(sc);
            stage.show();
        }
        
        if (tile.isMined()) {
            buttons[row][column].setText("*");
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    buttons[i][j].setDisable(true);
                    if (game.getMineTile(i,j).isMined()) {
                        buttons[i][j].setText("*");
                    }
                }
            }
            // game over message
        }

        else if (tile.getMinedNeighbours() > 0) {
            buttons[row][column].setText(""+ tile.getMinedNeighbours() +"");
        }
        
        else if (tile.getMinedNeighbours() == 0) {
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    boolean isRevealed = game.getMineTile(i, j).isRevealed();
                    int neighbours = game.getMineTile(i, j).getMinedNeighbours();
                    final int x = i, y = j;
                    if (isRevealed) {
                        buttons[x][y].setText("" + neighbours + "");
                    }
                }
            }
        }
    }
    
    private void flag(int row, int column) {
        game.mark(row, column);
        buttons[row][column].setText("?");
    }
    
    private void unflag(int row, int column) {
        game.getMineTile(row, column).toggleMarked();
        buttons[row][column].setText("");
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        this.stage = primaryStage;
        grid = new GridPane();
        buttons = new Button[15][10];

        grid.setAlignment(Pos.CENTER);

        
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                final int x = i;
                final int y = j;
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(29, 29);
                buttons[x][y].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    
                    public void handle(MouseEvent event) {
                        MouseButton button = event.getButton();
                        if (button == MouseButton.PRIMARY) {
                            game.step(x, y);
                            refresh(x, y);
                        }
                        else if (button == MouseButton.SECONDARY) {
                            if (buttons[x][y].getText() != "?") {
                                flag(x, y);
                            }
                            else if (buttons[x][y].getText() == "?") {
                                unflag(x, y);
                            }
                        }
                    }
                });
                grid.add(buttons[i][j], i, j);
            }               
        }
        
        
        Scene scene = new Scene(grid, 750, 750);
        
        primaryStage.setTitle("-Minesweeper-");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


}
