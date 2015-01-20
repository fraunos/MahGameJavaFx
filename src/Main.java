import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	Group group;
	Player player;
	final int sizeX = 200;
	final int sizeY = 200;

	Timeline time = new Timeline(60, new KeyFrame(Duration.millis(1000 / 60), e -> {
		player.update();
	}));

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		player = new Player(sizeX / 2, sizeY / 2);
		group = new Group(player);
		Scene scene = new Scene(group, 400, 400, true, SceneAntialiasing.DISABLED);
		scene.setFill(Color.GRAY);
		time.setCycleCount(Animation.INDEFINITE);
		scene.setOnKeyTyped(e -> {
			player.startMoving(e.getCode());
			System.out.println(e.toString());
		});
		scene.setOnKeyReleased(e -> {
			player.isMoving = false;
			System.out.println(e.toString());
		});
		time.play();
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}