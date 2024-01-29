package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;

public class MouseHandler implements MouseListener, MouseMotionListener {

    ProjectPanel pp;
    public double x;
    public double y;
    public double width;
    public double height;
    
    public boolean mouseClicked;	
    
    public double hoverCounter = 0;

    public MouseHandler(ProjectPanel pp) {
        this.pp = pp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	mouseClicked = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    	mouseClicked = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX() - width;
        y = e.getY() - height;
        width = pp.tileSize / 4;
        height = pp.tileSize / 2.5;
        
        if(hoverCounter > 0) {
        hoverCounter -= 0.1;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    
    public boolean mouseInside(int x, int y, double width, double height) {
    	if(x < x + width && x > x && y < y + height && y > y) {
    		return true;
    	}
    	return false;
    }
    
    public boolean mouseInside(Area area) {
    	if(x < area.getBounds().x + area.getBounds().width && x > area.getBounds().x && y < area.getBounds().y + area.getBounds().height && y > area.getBounds().y) {
    		return true;
    	}
    	return false;
    }
    
    public boolean mouseClicked() {
    	if(mouseClicked == true) {
    		mouseClicked = false;
    		return true;
    	}
    	return false;
    }
    
    public boolean mouseInsideHover(Area area, float appearenceTime) {
    	if(x < area.getBounds().x + area.getBounds().width && x > area.getBounds().x && y < area.getBounds().y + area.getBounds().height && y > area.getBounds().y) {
    		if(hoverCounter <= appearenceTime * 2) {
    			hoverCounter += 0.1;
    		}else {
    		hoverCounter = appearenceTime * 2;
    		}
    		
    	    return appearenceTime < hoverCounter;
    	    
    	    	}
    		return false;
    		
    }
}
