package main;

import object.OBJ_Key;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;

    Font arial_30, arial_60_Bold;
    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0; //message-display time

    public boolean gameFinished = false;

    double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.0");

    public UI(GamePanel gp) {

        this.gp = gp;

        arial_30 = new Font("Arial", Font.PLAIN, 30);
        arial_60_Bold = new Font("Super Mario 256", Font.BOLD, 60);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished) {

            String text;
            int textLength;
            int x, y;

            g2.setFont(arial_30);
            g2.setColor(Color.white);

            text = "You found the treasure!";
            //get the length of the drawn text in pixels
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            text = "You Time is: " + dFormat.format(playTime) + "!";
            //get the length of the drawn text in pixels
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize * 4);
            g2.drawString(text, x, y);

            g2.setFont(arial_60_Bold);
            g2.setColor(Color.yellow);

            text = "Congratulations!";
            //get the length of the drawn text in pixels
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        }
        else {

            g2.setFont(arial_30);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, 24, 20, 36, 36, null);
            g2.drawString("x " + gp.player.hasKey, 66, 50);

            //DRAW TIME
            playTime += (double)1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 12, 50);

            //DRAW MESSAGE
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(20f));
                g2.drawString(message, 24, gp.tileSize * 5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }

            }

        }

    }

}
