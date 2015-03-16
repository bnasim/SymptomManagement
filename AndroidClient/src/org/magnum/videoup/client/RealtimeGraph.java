package org.magnum.videoup.client;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.CustomLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
 
public class RealtimeGraph extends Activity {
     
    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    private Runnable mTimer2;
    private GraphView graphView;
    private GraphViewSeries exampleSeries1;
    private GraphViewSeries exampleSeries2;
    private double graph2LastXValue = 5d;
    private GraphViewSeries exampleSeries3;
     
    //change graphType to line if bar chart not required
    private String graphType = "line";
 
   
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphs);
        
        //example series data
        exampleSeries1 = new GraphViewSeries(new GraphViewData[] {
                new GraphViewData(1, 3)
                , new GraphViewData(2, 12)
                , new GraphViewData(2.5, 17)
               
        });
   //     exampleSeries3 = new GraphViewSeries(new GraphViewData[] {});
    //    exampleSeries3.getStyle().color = Color.CYAN;
        if (graphType.equalsIgnoreCase("line")) {
            graphView = new LineGraphView(
                    this
                    , "GraphViewDemo"
                    );
        } else {
            graphView = new BarGraphView(
                    this // context
                    , "GraphViewDemo"
                    );
        }  
        graphView.addSeries(exampleSeries1);
      //  graphView.addSeries(exampleSeries3);
        graphView.setViewPort(1, 8);
        graphView.setScalable(true);
        graphView.getGraphViewStyle().setGridColor(Color.BLACK);
        graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
        graphView.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
        graphView.setVerticalLabels(new String[] {"severe", "moderate", "well"});
        graphView.setHorizontalLabels(new String[] {"morning", "afternoon", "evening"});
        graphView.setCustomLabelFormatter(new CustomLabelFormatter() {
        	@Override
        	public String formatLabel(double value, boolean isValueX) {
        	if (isValueX) {
        	if (value < 5) {
        	return "well";
        	} else if (value < 15) {
        	return "moderate";
        	} else {
        	return "severe";
        	}
        	}
        	return null; // let graphview generate Y-axis label for us
        	}
        	});
 
        LinearLayout layout = (LinearLayout) findViewById(R.id.graph2);
        layout.addView(graphView);
    }
 
    @Override
    protected void onPause() {
        mHandler.removeCallbacks(mTimer1);
      
        super.onPause();
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        mTimer1 = new Runnable() {
            @Override
            public void run() {
                exampleSeries1.resetData(new GraphViewData[] {
                		  new GraphViewData(1, 4)
                          , new GraphViewData(2, 10)
                          , new GraphViewData(2.5, 20)
                });
               
                mHandler.postDelayed(this, 10);
            }
        };
        mHandler.postDelayed(mTimer1, 10);
 
       
    }
}