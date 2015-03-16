package org.magnum.videoup.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
public class PrescriptionAdapter extends ArrayAdapter<Medication> {
private LayoutInflater mInflater;
public PrescriptionAdapter(Context context, int textViewResourceId, List<Medication> objects) {
super(context, textViewResourceId, objects);
mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
}
@Override
public View getView(int position, View convertView, ViewGroup parent) {
View view = convertView;
Holder holder;
if (view == null) {
// View doesn't exist so create it and create the holder
view = mInflater.inflate(R.layout.medicationlayout, parent, false);
holder = new Holder();

holder.lblTitle1Text = (TextView) view.findViewById(R.id.textView1);
holder.lblTitle2Text = (TextView) view.findViewById(R.id.textView2);
holder.lblTitle3Text = (TextView) view.findViewById(R.id.textView3);
holder.lblTitle4Text = (TextView) view.findViewById(R.id.textView4);

view.setTag(holder);
} else {
// Just get our existing holder
holder = (Holder) view.getTag();
}
// Populate via the holder for speed
Medication stream = getItem(position);
SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy h:mm a");
String dateString = sdf.format(stream.getPrescriptionDate());    
// Populate the item contents
holder.lblTitle1Text.setText(stream.getPatientName());
holder.lblTitle2Text.setText(dateString);
holder.lblTitle3Text.setText(stream.getMedicine1Name());
holder.lblTitle4Text.setText(stream.getMedicine2Name());

// Load the screen cap image on a background thread

return view;
}
// Holder class used to efficiently recycle view positions
private static final class Holder {

public TextView lblTitle1Text;
public TextView lblTitle2Text;
public TextView lblTitle3Text;
public TextView lblTitle4Text;


}
}