package org.magnum.videoup.client;

public class GraphViewData implements com.jjoe64.graphview.GraphViewDataInterface {
    private double x;
    private double y;
 
    public GraphViewData(double x, double y) {
        this.x = x;
        this.y = y;
    }
 
    @Override
    public double getX() {
        return this.x;
    }
 
    @Override
    public double getY() {
        return this.y;
    }
}
