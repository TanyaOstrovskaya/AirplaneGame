package ru.tanya.airplanegame;

public class MainThread extends Thread {
    private GameManager gameManager;
    private CanvasView canvasView;
    private boolean isRunning;

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public MainThread(GameManager gameManager, CanvasView canvasView) {
        this.gameManager = gameManager;
        this.canvasView = canvasView;
    }

    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            gameManager.startGame();
        }
    }
}
