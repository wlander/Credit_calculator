
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.text.*;
import javax.swing.*;

//-----------------------------------------------------------------------------

class drawPanel extends JPanel{
private double mas_point1[] = new double[500];
private double mas_point2[] = new double[500];
private int count;
private double xs=0,ys=0;
private double x0=0,y0=0;
private double step_x, step_y, stx, sty;
private double tmpx, tmpy;
private int shag, summ;
double wall_x = 60, wall_y = 30;
private double t1,t2;
private double max_val;
private NumberFormat mes,nf;
private int cgr = 0;
int line_x = 12, line_y = 10;
Color grid_color = new Color(153,153,153);
Color line_color1 = new Color(0,0,204);
Color line_color2 = new Color(204,0,0);
Color text_color1 = new Color(0,51,102);
Color text_color2 = new Color(70,51,255);

	drawPanel(){
		mas_point1[0] = 1000.0;
		mas_point2[0] = 1000.0;
		max_val = 1000.0;
		nf = NumberFormat.getInstance();
		mes = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
	}

	public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			//рисуем оси
			g2.setStroke(new BasicStroke(2.0f));
			g2.draw(new Line2D.Double(x0, ys, xs+25.0, ys));
			g2.draw(new Line2D.Double(x0, y0-10, x0, ys));
			//подписи
			g2.setPaint(text_color1);
			g2.drawString("мес", ((int)xs)+25, (int)(ys+10.0));
			g2.drawString("сумма", 5, 14);
			g2.setStroke(new BasicStroke(1.0f));
			g2.setPaint(text_color2);
			g2.drawString("0", (int)(x0-2), (int)(ys+y0-10));
			//рисуем сетку по x
			stx = (xs-x0)/(double)line_x;
			shag = count/line_x;
			summ = shag;
			tmpx = x0+stx;
			for (int i=0;i<line_x;i++){
				sty = y0-10;
				g2.setPaint(text_color2);
				g2.drawString(mes.format(summ), (int)tmpx, (int)(ys+sty));
				g2.setPaint(grid_color);
				for (int j=0;j<((int)(ys/10.0))-1;j++){
					g2.draw(new Line2D.Double(tmpx, sty, tmpx, sty+5.0));
					sty = sty+10.0;
				}
				tmpx = tmpx+stx;
				summ = summ+shag;
			}		
			//рисуем сетку по y
			sty = (ys-y0)/(double)line_y;
			t1 = max_val;
			tmpy = y0;
			for (int i=0;i<line_y;i++){
				stx = x0-2;
				g2.setPaint(text_color2);
				g2.drawString(mes.format((int)t1), 4, (int)tmpy);
				g2.setPaint(grid_color);
				for (int j=0;j<((int)((xs-x0)/10.0))+1;j++){
					g2.draw(new Line2D.Double(stx, tmpy, stx+5.0, tmpy));
					stx = stx+10.0;
				}
				t1 = t1-(max_val/(double)line_y);
				tmpy = tmpy+sty;
			}
			
			g2.setPaint(line_color1);
			g2.setStroke(new BasicStroke(2.0f));
			//построение графиков
				step_x = (xs-x0)/(double)count;
				step_y = (ys-y0)/max_val;
			if (cgr>0){
				t1 = mas_point1[0];
				stx = 0;
				sty = ys-step_y*t1;
				for (int i=1; i<count-1; i++){
					t1 = mas_point1[i];
					tmpx = step_x*(double)i;
					tmpy = ys-step_y*t1;
					g2.draw(new Line2D.Double(stx+x0, sty, tmpx+x0, tmpy));
					stx = tmpx;
					sty = tmpy;
				}
			}
			if (cgr>1){
				t1 = mas_point2[0];
				stx = 0;
				sty = ys-step_y*t1;
				g2.setPaint(line_color2);
				for (int i=0; i<count; i++){
					t1 = mas_point2[i];
					tmpx = step_x*(double)i;
					tmpy = ys-step_y*t1;
					g2.draw(new Line2D.Double(stx+x0, sty, tmpx+x0, tmpy));
					stx = tmpx;
					sty = tmpy;
				}
			}		
	}
	public void settPlot(double inmas1[], double inmas2[], int m, Dimension ss, int colg, double maxa){
		mas_point1 = inmas1;
		mas_point2 = inmas2;
		count = m;
		xs = ss.getWidth()-wall_x;
		ys = ss.getHeight()-wall_y;
		x0 = wall_x;
		y0 = wall_y;
		max_val = maxa;
		cgr = colg;
	}
}//end drawPanel

