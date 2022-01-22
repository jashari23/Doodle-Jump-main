import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

public class DoodleJump extends JFrame implements MouseListener, KeyListener {
    public String username = Login.userName;
    public static Random rx;
    public static Line line;
    public static Random rw;
    static Timer moveTimer;
    private static final int D_W = 900;
    private static final int D_H = 700;
    DrawPanel drawPanel = new DrawPanel();
    static boolean gamePaused = false;
    private int keycode;
    ArrayList<Line> lines = new ArrayList<Line>();
    private boolean gameOver;
    private boolean start;
    private static int scores;

    private Clip clip, jump;
    private AudioInputStream audioInputStream, jump1;

    private int x = 250, y = 250, h = 250;
    private double dy = 0;

    private Timer timer;

    public DoodleJump() {

        JPanel jPanel = new JPanel();
        add(drawPanel);
        setTitle("Doodle Jump");
        setSize(D_W, D_H);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        addKeyListener(this);
        addMouseListener(this);

        // Background audio
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/Ronald-Kah-Desolation-Of-Mind.wav").getAbsoluteFile());

            // create clip reference
            clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);

            FloatControl clipVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clipVolume.setValue(-15.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            jump1 = AudioSystem.getAudioInputStream(new File("sounds/jump.wav").getAbsoluteFile());

            // create clip reference
            jump = AudioSystem.getClip();

            // open audioInputStream to the clip
            jump.open(jump1);

            FloatControl jumpVolume = (FloatControl) jump.getControl(FloatControl.Type.MASTER_GAIN);
            jumpVolume.setValue(-10.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // This arranges the location of platforms
        for (int i = 0; i < 10; i++) {
            line = new Line();
            rx = new Random();
            line.x = rx.nextInt(600) + 20;

            line.y = i * 50;

            // This arranges the length of the platform
            rw = new Random();
            line.width = rw.nextInt(60) + 120;

            lines.add(line);
        }
    }

    public class DrawPanel extends JPanel {
        private String highscore = "";
        public DrawPanel() {

            ActionListener listener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if (start) {
                        dy += 0.2;
                        y += dy;

                        // When character falls, game will finish
                        if (y > 700)
                            gameOver = true;
                        // Game Over is here

                        // Screen follows the character
                        if (y < h) {
                            for (Line line : lines) {
                                y = h;
                                line.y = line.y - (int) dy;

                                // This assigns random platform to the screen
                                if (line.y > 700) {
                                    Random rw = new Random();
                                    line.y = 80;
                                    line.x = rw.nextInt(600) + 20;
                                }
                            }
                        }

                        int count = 0;
                        // This make doodle to jump on the platforms and increases score
                        for (Line line : lines) {
                            if ((x > line.x && x < line.x + line.width) && (y >= line.y && y <= line.y + 10 && dy > 0)) {
                                // makes sound when doodle touches the platform
                                if (clip.isRunning() && !gameOver) {
                                    jump.setFramePosition(0);
                                    jump.start();
                                }

                                scores += 20;

                                // For orange platforms character jumps higher than green platforms
                                if (count % 13 == 0 && !gameOver) dy = -20;
                                else if (!gameOver) dy = -10;
                            }
                            count++;

                            if (scores >= 50) {
                                line.width = rw.nextInt(60) + 110;

                            }
                            if (scores >= 70) {
                                line.width = rw.nextInt(50) + 100;
                            }
                            if (scores >= 100) {
                                line.width = rw.nextInt(45) + 90;
                            }
                            if (scores >= 120) {
                                line.width = rw.nextInt(40) + 80;
                            }
                            if (scores >= 150) {
                                line.width = rw.nextInt(30) + 60;
                            }
                        }
                    }
                    // It shows the changes about looking of the game
                    drawPanel.repaint();
                }
            };

            timer = new Timer(10, listener);


            timer.start();
        }

        // It sets background, platforms and character
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            background(g);
            drawPlatforms(g);
            drawValues(g);
        }

        // This draws background
        public void background(Graphics g) {
            g.drawImage(Assets.background, 0, 0, D_W, D_H, null);
        }

        // This draws the platforms
        public void drawPlatforms(Graphics g) {

            int count = 0;

            for (Line line : lines) {
                if (count % 13 == 0) {
                    Graphics brownBorder = g;
                    brownBorder.drawRoundRect(line.x, line.y, line.width, 25, 30, 30);

                    Graphics brown = g;
                    brown.setColor(Color.orange);
                    brown.fillRoundRect(line.x, line.y, line.width, 25, 30, 30);
                } else {
                    Graphics stepBorder = g;
                    stepBorder.setColor(Color.black);
                    stepBorder.drawRoundRect(line.x, line.y, line.width, 25, 30, 30);

                    Graphics step = g;
                    step.setColor(Color.green);
                    step.fillRoundRect(line.x, line.y, line.width, 25, 30, 30);
                }
                count++;
            }
        }

        // This draws character
        public void drawValues(Graphics g) {

            // prints username
            g.setColor(Color.yellow);
            g.setFont(new Font("arial", Font.PLAIN, 20));
            g.drawString("Username: " + username, 500, 30);

            // prints score
            g.setColor(Color.yellow);
            g.setFont(new Font("arial", Font.PLAIN, 20));
            g.drawString("Scores: " + scores, 780, 30);


            // changes the direction of doodle left or right 
            if (keycode == KeyEvent.VK_RIGHT) {
                g.drawImage(Assets.playerRight, x - 45, y - 100, null);
            } else if (keycode == KeyEvent.VK_LEFT) {
                g.drawImage(Assets.playerLeft, x - 45, y - 100, null);
            } else {
                g.drawImage(Assets.playerLeft, x - 45, y - 100, null);
            }


            g.setColor(Color.red);
            g.setFont(new Font("Arial", 1, 50));
            if (!start) {
                g.drawString("Click to start!", D_W / 2 - 150, D_H / 2);
                g.drawString("Press Space Bar to start!", D_W / 2 - 250, D_H / 2 + 50);
            }
            if (gameOver) {
               g.drawString("Press X to play again!", D_W / 2 - 250, D_H / 2);
               g.drawString("Your Score: " + scores, 275 - 25, 450);
            }

        }

    }

        public static class Line {
            int x;
            int y;
            int width;
        }

        // starts the game
        public void start() {
            if (gameOver) {
                clip.stop();
            }

            if (!start) {
                start = true;
            }
        }

        //pause the game
        public void pauseGame() {
            timer.stop();
            gamePaused = true;

        }

        //restart game
        public void restartGame() {
            timer.start();
            gamePaused = false;
        }

        public void level() {

            int count = 0;


            // This make doodle to jump on the platforms and increases score
            for (Line line : lines) {
                if ((x > line.x && x < line.x + line.width) && (y >= line.y && y <= line.y + 10 && dy > 0)) {
                    // makes sound when doodle touches the platform
                    if (clip.isRunning() && !gameOver) {
                        jump.setFramePosition(0);
                        jump.start();
                    }
                    if (count == 100) {
                        scores += 50;
                    }
                    // For orange platforms character jumps higher than green platforms
                    if (count % 13 == 0 && !gameOver) dy = -40;
                    else if (!gameOver) dy = -20;
                }
                count++;

            }

            // It shows the changes about looking of the game
            drawPanel.repaint();
        }


        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            start();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }


        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub

            keycode = e.getKeyCode();
            if (keycode == KeyEvent.VK_X) {
                scores=0;
                clip.close();
                jump.close();
                timer.stop();
                restartGame();
                new DoodleJump();
                setVisible(false);
            }


            if (keycode == KeyEvent.VK_SPACE) {
                start();
            }

            if (keycode == KeyEvent.VK_E) {
                System.exit(0);
            }


            if (keycode == KeyEvent.VK_RIGHT) {
                x += 40;
            }

            if (keycode == KeyEvent.VK_LEFT) {
                x -= 40;
            }

            // mute the sound if unmuted
            if (keycode == KeyEvent.VK_M) {
                clip.stop();
            }

            // unmute the sound if muted
            if (keycode == KeyEvent.VK_U) {
                clip.start();
            }

            if ((e.getKeyCode() == KeyEvent.VK_P) && (!gamePaused)) {

                pauseGame();

            } else if ((e.getKeyCode() == KeyEvent.VK_P) && (gamePaused)) {

                restartGame();

            }

        }


        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
        }

    }
