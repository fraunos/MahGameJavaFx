package pl.fraunos.fxgame;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	String TITLE = "Lelod";
	static KeyProcessor kp = new KeyProcessor();
	static Random random;
	static Scene scene;
	static Group group;
	static Player player;
	final static int sizeX = 600;
	final static int sizeY = 600;
	static AudioClip ac;
	static double mouseX;
	static double mouseY;
	static int gameTime = 0;

	Timeline time = new Timeline(60, new KeyFrame(Duration.millis(1000 / 60), e -> {
		updateWorld();
		gameTime++;
	}));

	public static void main(String[] args) {
		ac = new AudioClip(Main.class.getResource("res/dobra.mp3").toString());
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setResizable(false);
		primaryStage.setTitle(TITLE);
		random = new Random();
		player = new Player(sizeX / 2, sizeY / 2);
		Image map = SpriteLoader.getSprite(4, 4);
		group = new Group(new ImageView(map) {
			{
				setScaleX(4);
				setScaleY(4);
			}
		}, player);
		scene = new Scene(group, sizeX, sizeY, true, SceneAntialiasing.DISABLED);
		scene.setFill(Color.GRAY);
		scene.setOnMouseMoved(e -> {
			mouseX = e.getX();
			mouseY = e.getY();

		});
		time.setCycleCount(Animation.INDEFINITE);
		scene.setOnKeyPressed(e -> {
			kp.process(e.getCode(), true);
			player.isMoving = true;
		});
		scene.setOnKeyReleased(e -> {
			kp.process(e.getCode(), false);
			player.isMoving = false;

		});
		time.play();
		// primaryStage.setOpacity(0.5);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// public static boolean checkCollision(Player player, Circle circle) {
	// double totalRadius = player.getRadius() + circle.getRadius();
	// double distance = Math.hypot(player.getCenterX() - circle.getCenterX(),
	// player.getCenterY() - circle.getCenterY());
	// return distance < totalRadius;
	// }

	public static void updateWorld() {
		player.update(mouseX, mouseY);

		// if (checkCollision(player, circle)) {
		// circle.setCenterX(random.nextDouble() * sizeX);
		// circle.setCenterY(random.nextDouble() * sizeY);
		// }
	}
}