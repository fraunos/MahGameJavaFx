package pl.fraunos.fxgame;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	String TITLE = "TempNameGame";
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
	Label xyCoords = new Label();

	Timeline time = new Timeline(60, new KeyFrame(Duration.millis(1000 / 60), e -> {
		updateWorld();
		xyCoords.setText((int) player.x + " " + (int) player.y);
		gameTime++;
	}));

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) {
		xyCoords.setTranslateX(200);
		xyCoords.setTranslateY(20);
		xyCoords.setScaleX(2);
		xyCoords.setScaleY(2);
		primaryStage.setResizable(false);
		primaryStage.setTitle(TITLE);
		random = new Random();
		player = new Player(0, 0);
		mapTiles = new Group();
		entities = new Group();
		group = new Group(mapTiles, entities, xyCoords);
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

	public static void updateWorld() {
		player.update(mouseX, mouseY);
		updateMap();
	}

	public void drawMap() {
		for (int i = 0; i < map.MAP_SIZE; i++) {
			for (int j = 0; j < map.MAP_SIZE; j++) {
				System.out.print(map.map[j + map.MAP_SIZE * i] + ",");
				mapTiles.getChildren().add(new ImageView(SpriteLoader.getSprite(map.map[j + map.MAP_SIZE * i], 4)));
				((ImageView) mapTiles.getChildren().get(j + map.MAP_SIZE * i)).setX(j * (SpriteLoader.spriteSize - 1));
				((ImageView) mapTiles.getChildren().get(j + map.MAP_SIZE * i)).setY(i * (SpriteLoader.spriteSize - 1));
			}
		}
	}

	public static void updateMap() {
		mapTiles.setTranslateX(sizeX / 2 - player.x);
		mapTiles.setTranslateY(sizeY / 2 - player.y);
	}
}