package brick;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int map[][];
	public int brickwidth;
	public int brickheight;
	public int a,b;
	public MapGenerator(int row, int col) {
		
		
		
		map = new int[row-2][col-2];
		
		for(int i=0;i < map.length ;i++) {
			for(int j=0 ; j < map[0].length;j++) {
				map[i][j]=1;
			}
		}
		
		
		
		
		
		
			brickwidth=540/col;
			brickheight=150/row;	
	
	}
	public MapGenerator(int row, int col,int level) {
		map = new int[row][col];
		a=row-1;
		b=row;
		for(int i=0;i < map.length ;i++) {
			for(int j=0 ; j <a;j++) {
				map[i][j]=0;
			}
			for(int k=a ; k < b ;k++) {
				map[i][k]=1;
			}
			a--;
			b++;
		}
			brickwidth=540/col;
			brickheight=180/row;	
	}
		public void draw(Graphics2D g)
	{
		for(int i=0;i < map.length ;i++) {
			for(int j=0 ; j < map[0].length;j++) {
				if(map[i][j]>0) {
					g.setColor(Color.magenta);
					g.fillRect(j* brickwidth +80, i* brickheight +50, brickwidth,brickheight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j* brickwidth +80, i* brickheight +50, brickwidth,brickheight);				}
			}
		
	}

}
	public void setBrickValue(int value,int row ,int col) {
		map[row][col]=value;
	}
}
