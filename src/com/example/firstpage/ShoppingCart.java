package com.example.firstpage;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListView;
import android.widget.Toast;

public class ShoppingCart extends ListActivity {
	Button btnbuy;
	Button btnadd;
	final Context context = this;
    ArrayList<String> nume;
    String items;
    ListView lista;
    ArrayAdapter<String> adapter;
	Bundle b = new Bundle();
	String key; String value;
	Intent intent2;
    
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        lista = (ListView) findViewById(android.R.id.list);
        
        nume = new ArrayList<String>();
        
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nume);
        setListAdapter(adapter);

        lista.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adaptor, View arg1, int position,
                    long id) {
                Toast.makeText(ShoppingCart.this, "Click "+ adaptor.getItemAtPosition(position)+ "?", Toast.LENGTH_LONG).show()    ;
            }
            

        });
        
        lista.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int pos, long id) {
				nume.remove(pos);
				adapter.notifyDataSetChanged();

                return true;
            }
        });
        btnbuy = (Button)findViewById(R.id.buy);
        btnbuy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
		 
					// set title
					alertDialogBuilder.setTitle("Are you sure?");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("Click yes to continue!")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								ShoppingCart.this.finish();
							}
						  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								dialog.cancel();
							}
						});
		 
						AlertDialog alertDialog = alertDialogBuilder.create();
						alertDialog.show();
					}
		});	
        btnadd = (Button)findViewById(R.id.add);	
        btnadd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
            	
				intent2 = new Intent(ShoppingCart.this, ItemsList.class);
            	startActivity(intent2);
          
			}
		});
          try{
      	Intent search = getIntent();
        String nume2 = search.getExtras().getString("param_a");
      	System.out.println(nume2);
      	nume.add(nume2);
      	adapter.notifyDataSetChanged();
          }catch(Exception e){
        	  System.out.println("dute acasa");
          }
	}

	
}

