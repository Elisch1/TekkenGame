package dev.Elias.TekkenGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{
	
	private Display display;
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
//	private BufferedImage testImage;
//	private SpriteSheet sheet;
	
	private State gameState;
	private State menuState;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		
		
	}
	private void init() {
		display = new Display(title, width, height);
		Assets.init();
//		testImage = ImageLoader.loadImage("/textures/RedFighterStandSheet.png");
//		sheet = new SpriteSheet(testImage);
		gameState = new GameState();
		menuState = new MenuState();
		State.setState(gameState);
	}
	
	private void tick() {
	if(State.getState() != null)
		State.getState().tick();
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		//RedFighterStandSheet horizontal = 69,00 vertical = 83,00
		//g.drawImage(Assets.redStandOne, 10, 10, null);
//		g.drawImage(sheet.crop(0, 0, 64, 83), 5, 5, null);
		g.drawImage(Assets.redStandOne, 10, 10, null);
		if(State.getState() != null)
			State.getState().render(g);
		
		bs.show();
		g.dispose();
		
	}
	
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
			tick();
			render();
			ticks++;
			delta--;
			}
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		stop();
		
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
