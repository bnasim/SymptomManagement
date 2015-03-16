package org.magnum.videoup.client;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class CheckInActivity extends Activity {
 
 MyCustomAdapter dataAdapter = null;
// public static final String MyPREFERENCES = "MyPrefs" ;
 public static final String Name1 = "nameKey"; 
 public static final String Name2 = "phoneKey"; 
 public static final String Name3 = "emailKey"; 
 TextView name ;
 
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  name = (TextView) findViewById(R.id.editTextName);
  
  //Generate list View from ArrayList
  displayListView();
 
  checkButtonClick();
 
 }
 
 private void displayListView() {
 
  //Array list of countries
  ArrayList<Country> countryList = new ArrayList<Country>();
  Country country = new Country("AFG","Afghanistan",false);
  countryList.add(country);
  country = new Country("ALB","Albania",true);
  countryList.add(country);
  country = new Country("DZA","Algeria",false);
  countryList.add(country);
 
 
  //create an ArrayAdaptar from the String Array
  dataAdapter = new MyCustomAdapter(this,
    R.layout.medicine_info, countryList);
  ListView listView = (ListView) findViewById(R.id.listView1);
  // Assign adapter to ListView
  listView.setAdapter(dataAdapter);
 
 
  listView.setOnItemClickListener(new OnItemClickListener() {
   public void onItemClick(AdapterView<?> parent, View view,
     int position, long id) {
    // When clicked, show a toast with the TextView text
    Country country = (Country) parent.getItemAtPosition(position);
    Toast.makeText(getApplicationContext(),
      "Clicked on Row: " + country.getName(),
      Toast.LENGTH_LONG).show();
   
   }
  });
 
 }
 
 private class MyCustomAdapter extends ArrayAdapter<Country> {
 
  private ArrayList<Country> countryList;
 
  public MyCustomAdapter(Context context, int textViewResourceId,
    ArrayList<Country> countryList) {
   super(context, textViewResourceId, countryList);
   this.countryList = new ArrayList<Country>();
   this.countryList.addAll(countryList);
  }
 
  private class ViewHolder {
   TextView code;
   CheckBox name;
  }
 
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
 
   ViewHolder holder = null;
   Log.v("ConvertView", String.valueOf(position));
 
   if (convertView == null) {
   LayoutInflater vi = (LayoutInflater)getSystemService(
     Context.LAYOUT_INFLATER_SERVICE);
   convertView = vi.inflate(R.layout.medicine_info, null);
 
   holder = new ViewHolder();
   holder.code = (TextView) convertView.findViewById(R.id.code);
   holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
   convertView.setTag(holder);
 
    holder.name.setOnClickListener( new View.OnClickListener() { 
     public void onClick(View v) { 
      CheckBox cb = (CheckBox) v ; 
      Country country = (Country) cb.getTag(); 
      Toast.makeText(getApplicationContext(),
       "Clicked on Checkbox: " + cb.getText() +
       " is " + cb.isChecked(),
       Toast.LENGTH_LONG).show();
      country.setSelected(cb.isChecked());
     } 
    }); 
   }
   else {
    holder = (ViewHolder) convertView.getTag();
   }
 
   Country country = countryList.get(position);
   holder.code.setText(" (" +  country.getCode() + ")");
   holder.name.setText(country.getName());
   holder.name.setChecked(country.isSelected());
   holder.name.setTag(country);
 
   return convertView;
 
  }
 
 }
 private void SavePreferences(String key, String value)
	{  
		SharedPreferences sharedPreferences = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE); 
	SharedPreferences.Editor editor = sharedPreferences.edit(); 
	editor.putString(key, value); 
	//editor.putString("MY_NAME", "nass"); 
	editor.commit();   }
 private void checkButtonClick() {
 
 
  Button myButton = (Button) findViewById(R.id.findSelected);
  myButton.setOnClickListener(new OnClickListener() {
 
   @Override
   public void onClick(View v) {
 
  //  StringBuffer responseText = new StringBuffer();
  //  responseText.append("The following were selected...\n");
	final   String[] myList = new String[3];
    ArrayList<Country> countryList = dataAdapter.countryList;
    for(int i=0;i<countryList.size();i++){
     Country country = countryList.get(i);
     if(country.isSelected()){
      myList[i]= country.getName();
     }
    }
    SavePreferences("Name1", myList[0]);
    SavePreferences("Name2", myList[1]);	
    SavePreferences("Name3", myList[2]);	
  //  Toast.makeText(getApplicationContext(),
    //	       "the value: " + myList[0] +
    //	       " is " + myList[1],
    //	       Toast.LENGTH_LONG).show();
    Intent intent = new Intent(CheckInActivity.this, DatetimeActivity.class);
    startActivity(intent);    
   }
  });
  
 }
 
}