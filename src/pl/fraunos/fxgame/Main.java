package pl.fraunos.fxgame;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	static KeyProcessor kp = new KeyProcessor();
	static Random random;
	static Scene scene;
	static Group group;
	static Player player;
	static Circle circle;
	final static int sizeX = 200;
	final static int sizeY = 200;
	static AudioClip ac;

	Timeline time = new Timeline(60, new KeyFrame(Duration.millis(1000 / 60), e -> {
		updateWorld();

	}));

	public static void main(String[] args) {
		ac = new AudioClip(Main.class.getResource("res/dobra.mp3").toString());
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) {
		random = new Random();
		circle = new Circle(random.nextDouble() * sizeX, random.nextDouble() * sizeY, 15);
		player = new Player(sizeX / 2, sizeY / 2);
		group = new Group(player, circle);
		scene = new Scene(group, 400, 400, true, SceneAntialiasing.DISABLED);
		scene.setFill(Color.GRAY);
		time.setCycleCount(Animation.INDEFINITE);
		scene.setOnKeyPressed(e -> {
			kp.process(e.getCode(), true);
		});
		scene.setOnKeyReleased(e -> {
			kp.process(e.getCode(), false);
		});
		time.play();
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static boolean checkCollision(Player player, Circle circle) {
		double totalRadius = player.getRadius() + circle.getRadius();
		double distance = Math.hypot(player.getCenterX() - circle.getCenterX(), player.getCenterY() - circle.getCenterY());
		return distance < totalRadius;
	}

	public static void updateWorld() {
		player.update();

		if (checkCollision(player, circle)) {
			circle.setCenterX(random.nextDouble() * sizeX);
			circle.setCenterY(random.nextDouble() * sizeY);
		}
	}
}