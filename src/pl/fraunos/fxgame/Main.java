package pl.fraunos.fxgame;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	String TITLE = "Lelod";
	static KeyProcessor kp = new KeyProcessor();
	static Random random;
	static Scene scene;
	static Group mapTiles;
	static Group entities;
	static Group group;
	static Player player;
	final static int sizeX = 1000;
	final static int sizeY = 1000;
	static AudioClip ac;
	static double mouseX;
	static double mouseY;
	static int gameTime = 0;
	static WorldMap map = new WorldMap();

	Timeline time = new Timeline(60, new KeyFrame(Duration.millis(1000 / 60), e -> {
		updateWorld();
		gameTime++;
	}));

	public static void main(String[] args) {
		// ac = new
		// AudioClip(Main.class.getResource("res/dobra.mp3").toString());
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setResizable(false);
		primaryStage.setTitle(TITLE);

		random = new Random();
		player = new Player(sizeX / 2, sizeY / 2);
		mapTiles = new Group();
		entities = new Group();
		group = new Group(mapTiles, entities);
		scene = new Scene(group, sizeX, sizeY, true, SceneAntialiasing.DISABLED);
		scene.setFill(Color.GRAY);
		drawMap();
		entities.getChildren().add(player);
		scene.setOnMouseMoved(e -> {
			mouseX = e.getX();
			mouseY = e.getY();
		});

		time.setCycleCount(Animation.INDEFINITE);
		scene.setOnKeyPressed(e -> {
			kp.process(e.getCode(), true);
			if (e.getCode() == KeyCode.SPACE) {
				System.out.println(gameTime);
			}
		});
		scene.setOnKeyReleased(e -> {
			kp.process(e.getCode(), false);
		});
		scene.setOnKeyTyped(e -> {

		});
		time.play();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// public static boolean checkCollision(Player player, Circle circle) {
	// double totalRadius = player.getRadius() + circle.getRadius();
	// double distance = Math.hypot(player.getCenterX() -
	// circle.getCenterX(),player.getCenterY() - circle.getCenterY());
	// return distance < totalRadius;
	// }

	public static void updateWorld() {
		player.update(mouseX, mouseY);
		updateMap();
		// if (checkCollision(player, circle)) {
		// circle.setCenterX(random.nextDouble() * sizeX);
		// circle.setCenterY(random.nextDouble() * sizeY);
		// }
	}

	public void drawMap() {
		for (int i = 0; i < map.map.length; i++) {
			for (int j = 0; j < map.map.length; j++) {
				mapTiles.getChildren().addAll(new ImageView(SpriteLoader.getSprite(4, 4)));
				((ImageView) mapTiles.getChildren().get(j + 10 * i)).setX(j * (SpriteLoader.spriteSize - 1));
				((ImageView) mapTiles.getChildren().get(j + 10 * i)).setY(i * (SpriteLoader.spriteSize - 1));
			}
		}
	}

	public static void updateMap() {
		mapTiles.setTranslateX(mapTiles.getTranslateX() - player.deltaX);
		mapTiles.setTranslateY(mapTiles.getTranslateY() - player.deltaY);
		// for (int i = 0; i < map.map.length; i++) {
		// for (int j = 0; j < map.map.length; j++) {
		// ((ImageView) mapTiles.getChildren().get(j + 10 *
		// i)).setX(((ImageView)
		// mapTiles.getChildren().get(j + 10 * i)).getX() - player.deltaX);
		// ((ImageView) mapTiles.getChildren().get(j + 10 *
		// i)).setY(((ImageView)
		// mapTiles.getChildren().get(j + 10 * i)).getY() - player.deltaY);
		// }
		// }
	}
}