package pl.fraunos.fxgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen extends Application {
	String TITLE = "TempNameGame";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Button btn = new Button("Do³¹cz do serwera");
		Button btn1 = new Button("Za³ó¿ serwer");
		Button btn2 = new Button("Ustawienia");
		btn.setOnAction(e -> {
			System.out.println("ehe");
		});
		VBox pane = new VBox(20, btn, btn1, btn2);
		pane.setPadding(new Insets(30, 30, 30, 30));
		pane.setAlignment(Pos.BOTTOM_RIGHT);
		Scene scene = new Scene(new StackPane(new ImageView(new Image(MainScreen.class.getResource("res/temp.jpg").toExternalForm())), pane));

		primaryStage.setScene(scene);
		primaryStage.setTitle(TITLE);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();

		primaryStage.show();
	}
}
