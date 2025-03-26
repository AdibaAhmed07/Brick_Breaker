package Brick_Breaker;

import java.awt.*;

public class BrickManager {

    public int bricks[][];
    public int brickWidth;
    public int brickHeight;
    public int level; // **Added level variable**

    public BrickManager(int rows, int cols, int level) {
        this.level = level; // **Initialize level**
        bricks = new int[rows][cols];
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                double rand = Math.random();
                if (level > 1) {
                    // **Increase chance of strong bricks or obstacles in higher levels**
                    if (rand < 0.3) {
                        bricks[i][j] = -1; // Obstacle
                    } else if (rand < 0.6) {
                        bricks[i][j] = 2; // Strong brick
                    } else {
                        bricks[i][j] = 1; // Normal brick
                    }
                } else {
                    // Normal brick distribution for lower levels
                    if (rand < 0.2) {
                        bricks[i][j] = -1; // Obstacle
                    } else if (rand < 0.5) {
                        bricks[i][j] = 2; // Strong brick
                    } else {
                        bricks[i][j] = 1; // Normal brick
                    }
                }
            }
        }
        brickWidth = 700 / cols;
        brickHeight = 200 / rows;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j] > 0) {
                    if (bricks[i][j] == 2) {
                        g.setColor(Color.RED); // Strong bricks are red
                    } else {
                        g.setColor(Color.WHITE); // Normal bricks are white
                    }
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                } else if (bricks[i][j] == -1) {
                    g.setColor(Color.GRAY); // Obstacles are gray
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void hitBrick(int row, int col) {
        if (row >= 0 && row < bricks.length && col >= 0 && col < bricks[0].length) {
            if (bricks[row][col] > 1) {
                bricks[row][col]--; // Reduce strength if it’s a strong brick
            } else if (bricks[row][col] == 1) {
                bricks[row][col] = 0; // Destroy the normal brick
            } // obstacles are indestructible
        }
    }
}
