package pl.fraunos.fxgame;

import java.util.Random;

import javafx.scene.image.Image;

public class WorldMap {
	final int MAP_SIZE = 5;
	int[] map = new int[MAP_SIZE * MAP_SIZE];
	Random random = new Random();

	Image grass = SpriteLoader.getSprite(1, 1);

	public WorldMap() {
		System.out.println(map.length);
		for (int i = 0; i < MAP_SIZE; i++) {
			for (int j = 0; j < MAP_SIZE; j++) {
				map[j + i * MAP_SIZE] = random.nextInt(5);
			}
		}
	}

}
