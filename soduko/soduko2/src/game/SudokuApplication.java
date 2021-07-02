package game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class SudokuApplication extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        VBox vBox = new VBox();
        HBox hBox = new HBox(5);
        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(9);
        tilePane.setPrefRows(9);
        final int SIZE = 60; //ändrat från 70->60
        hBox.setPrefHeight(40);
        Sudoku mySudoku = new Sudoku();



        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                TextField TF = new OneNumberTextField();
                TF.setPrefSize(SIZE, SIZE);
                TF.setAlignment(Pos.CENTER);
                TF.setId(i + "," + k);
                if (i < 3 && (k < 3 || k >= 6) || i >= 3 && i < 6 && k >= 3
                        && k < 6 || i >= 6 && (k < 3 || k >= 6)) {
                    TF.setStyle("-fx-control-inner-background: green;" +
                            "-fx-font-size: 22;");
                    TF.setFont(Font.font("Verdana", 24));
                } else {
                    TF.setStyle("-fx-control-inner-background: yellow;" +
                            "-fx-font-size: 22;");
                    TF.setFont(Font.font("Verdana", 24));
                }
                tilePane.getChildren().add(TF);
            }
        }


        MenuBar menuBar = new MenuBar();
        final Menu file = new Menu("File");
        final MenuItem new_game = new Menu("new game");
        final MenuItem help = new Menu("Help");
        Button solve = new Button("solve");
        Button Check = new Button("Check");
        Button upload = new Button("upload");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");

        file.getItems().addAll(new_game, help);
        menuBar.getMenus().addAll(file);
        solve.setMinSize(100, hBox.getPrefHeight());
        Check.setMinSize(100, hBox.getPrefHeight());
        upload.setMinSize(100, hBox.getPrefHeight());
        hBox.getChildren().addAll(solve, Check, upload);
        vBox.getChildren().addAll(menuBar, tilePane, hBox);


        help.setOnAction(event -> {
            alert.setHeaderText("Do You Need Help !!");
            alert.setContentText("If you having troubles with this game,\n you should contact the creator immediately,\n and remember that life is not always easy !!");
            alert.show();
        });

        solve.setDefaultButton(true);
        solve.setOnAction(event -> {
            mySudoku.clear();
            for (Node node : tilePane.getChildren()) {
                if (node instanceof TextField) {
                    int row = Character.getNumericValue(node.getId().charAt(0));
                    int col = Character.getNumericValue(node.getId().charAt(2));
                    if (!((TextField) node).getText().isEmpty()) {
                        int value = Integer.parseInt(((TextField) node).getText().trim());
                        mySudoku.setValue(row, col, value);
                        mySudoku.addValues(new Tuple(row,col,value));
                    }
                }
            }
            if (mySudoku.solve()) {
                alert.setContentText("I have a great message for you!: U have one !!");
                alert.setHeaderText(null);

            } else {
                alert.setContentText("there is no solution for this Sudoku try again !!");
                alert.setHeaderText(null);
            }
            alert.show();

            for (Node node : tilePane.getChildren()) {
                if (node instanceof TextField) {
                    int row = Character.getNumericValue(node.getId().charAt(0));
                    int col = Character.getNumericValue(node.getId().charAt(2));
                    if (mySudoku.getValue(row, col) == 0) {
                        ((TextField) node).setText("");
                    } else {
                        ((TextField) node).setText(mySudoku.getValue(row, col) + "");

                    }
                }
            }
        });


        Check.setOnAction(event -> {
            mySudoku.clear();
            for (Node node : tilePane.getChildren()) {
                if (node instanceof TextField) {
                    int row = Character.getNumericValue(node.getId().charAt(0));
                    int col = Character.getNumericValue(node.getId().charAt(2));
                    if (!((TextField) node).getText().isEmpty()) {
                        int value = Integer.parseInt(((TextField) node).getText().trim());
                        mySudoku.setValue(row, col, value);
                        mySudoku.addValues(new Tuple(row,col,value));
                    }
                }
            }
            if (mySudoku.solve()) {
                alert.setContentText("It is possible to solve this Sudoku, and U can do it");
                alert.setHeaderText(null);
            } else {
                alert.setContentText("there is no solution for this Sudoku try again !!");
                alert.setHeaderText(null);
            }
            alert.show();
        });

        upload.setOnAction(event -> {
            mySudoku.clear();
            Sudokulist s = new Sudokulist();
            Sudoku mySudo = s.apply(mySudoku);
            for (Node node : tilePane.getChildren()) {
                int row = Character.getNumericValue(node.getId().charAt(0));
                int col = Character.getNumericValue(node.getId().charAt(2));
                if (node instanceof TextField) {
                    if(mySudo.getValue(row,col) != 0){
                    ((TextField) node).setText(mySudo.getValue(row,col)+"");
                }
                }}
        });

        new_game.setOnAction(event -> {
            for (Node node : tilePane.getChildren()) {
                if (node instanceof TextField) {
                    mySudoku.clear();
                    ((TextField) node).clear();
                }
            }
        });


        anchorPane.getChildren().addAll(vBox);
        stage.setTitle("Sudoku");
        stage.setResizable(false);
        stage.setScene(new Scene(anchorPane));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }

}