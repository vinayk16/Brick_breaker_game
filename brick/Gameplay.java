package brick;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener,ActionListener {
	private boolean play = false;
	private boolean start =false;
	private int score = 0;
	private int totalBricks=65;
	private Timer timer;
	private int delay = 8;
	private int playerX = 310;
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballxdir = -1;
	private int ballydir = -2;
	private MapGenerator map;

	public Gameplay() {
		map =new MapGenerator(7,15);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	
	}

	public void paint(Graphics g) {
		//starting
		if(!start)
		{
			g.setColor(Color.cyan);
			g.fillRect(1,1,692,592);
			
			g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman",Font.BOLD, 50));
            g.drawString("JAVA MINI PROJECT", 60,100);
            
            g.setColor(Color.black);
            g.drawRect(50, 130,550, 80);
            
            g.setColor(Color.gray);
            g.setFont(new Font("TimesRoman",Font.BOLD, 45));
            g.drawString("BRICK BREAKER GAME", 60,180);
            
            
            g.setColor(Color.magenta);
            g.setFont(new Font("serif",Font.BOLD, 20));           
            g.drawString("By Vinay Kumar Kureel", 400,230);  
            g.drawString("Rajan Gupta", 430,250);  
            g.drawString("Premchand Gupta", 430,270);  
            
            g.setColor(Color.black);
            g.setFont(new Font("serif",Font.BOLD, 30));
            g.drawString("PRESS 1 TO START THE GAME", 100,400);  
		}
		//for maps
		else {
		//background
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		
		//drawing map
		map.draw((Graphics2D)g);
		
		 //borders
		g.setColor(Color.blue);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(683, 0, 3, 592);
		// the scores 		
				g.setColor(Color.white);
				g.setFont(new Font("serif",Font.BOLD, 25));
				g.drawString(""+score, 590,30);
		//paddle
		g.setColor(Color.green);
		g.fillRect(playerX,550,100,8);
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		if(totalBricks <= 0)
		{
			 play = false;
             ballxdir = 0;
     		 ballydir = 0;
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 30));
             g.drawString("You Won", 260,300);
             
             g.setColor(Color.cyan);
             g.setFont(new Font("serif",Font.BOLD, 20));           
             g.drawString("Press 2 to go to Next Level", 230,330);    
		}
		
		if(ballposY > 570)
        {
			 play = false;
             ballxdir = 0;
     		 ballydir = 0;
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 30));
             g.drawString("Game Over, Scores: "+score, 190,300);
             
             g.setColor(Color.cyan);
             g.setFont(new Font("serif",Font.BOLD, 20));  
             g.drawString("Press 2 to go to Next Level", 230,330);       
             g.drawString("Press Enter to exit",230,350);        
        }
		}
		
		g.dispose();
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8)))
			{
				ballydir = -ballydir;
				
			}
			
			
			A : for(int i=0;i<map.map.length;i++) {
				for(int j=0;j<map.map[0].length;j++)
				{
					if(map.map[i][j]>0) {
						int brickx=j*map.brickwidth+80;
						int bricky=i*map.brickheight+50;
						int brickwidth=map.brickwidth;
						int brickheight=map.brickheight;
						
						Rectangle rect=new Rectangle(brickx,bricky,brickwidth,brickheight);
						Rectangle ballrect= new Rectangle(ballposX,ballposY,20,20);
						Rectangle brickrect=rect;
						
						if(ballrect.intersects(brickrect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score+=5;
							
							if(ballposX+19<=brickrect.x ||ballposX+1>=brickrect.x + brickrect.width) {
								ballxdir= -ballxdir;
							}else {
								ballydir= -ballydir;
							}
							break A;
						}
					}
				}
			}
			
			ballposX += ballxdir;
			ballposY += ballydir;
			if(ballposX < 0) {
				ballxdir= -ballxdir;
			}
			if(ballposY < 0) {
				ballydir= -ballydir;
			}
			if(ballposX >670) {
				ballxdir= -ballxdir;
			}
		}
		repaint();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX=600;
			}
			else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX=10;
			}
			else {
				moveLeft();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
			System.exit(0);
        }
		if (e.getKeyCode() == KeyEvent.VK_2)
		{          
			if(!play)
			{
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballxdir = -1;
				ballydir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 49;
				map = new MapGenerator(7, 15,2);
			
				repaint();
			}
        }
		
		if(e.getKeyCode()==KeyEvent.VK_1)
		{
		    start = true;	
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	public void moveRight() {
		play =true;
		playerX+=20;
	}
	public void moveLeft() {
		play =true;
		playerX-=20;
	}

}
