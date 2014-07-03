package com.example.firstpage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import org.apache.http.*;
import org.json.*;


public class ItemsList extends ListActivity {
	 ArrayList<String> nume = new ArrayList<String>();
	 ListView lista;
	 ArrayAdapter<String> adapter;
	 String rez;
	 Button btnreturn;
	    
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.items);
	        lista = (ListView) findViewById(android.R.id.list);
	        
	       
	        
	        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nume);
	        setListAdapter(adapter);
	       
	     RequestParams params = new RequestParams();
	        params.put("api_auth", "VECeKU2puHatudreb7A3");
	        params.put("trigger", "user_profile");
	        params.put("id_user", "35");
	        params.put("session", "61ff95571aa3db550df827f0cae10c938cd31fd2");

	        AsyncHttpClient client = new AsyncHttpClient();
	        
	        
	        client.get("http://10.0.2.2:5000/api/list", new JsonHttpResponseHandler() {
	            @Override
	            public void onStart() {
	                // called before request is started
	            }
	            @Override
	            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
	                // called when response HTTP status is "200 OK"
	                rez = new String(response.toString());
	                
	               
	                String []tok = rez.split("\"");
	                int i = 0;
	                for(String token:tok)
	                {
	                	char tkst = token.charAt(0);
	                	if(!(tkst >= 'a' && tkst <='z')&&!(tkst >= 'A' && tkst <='Z')) continue;
	                	if(token.equals("name")) continue;
	                	nume.add(token);
	                	adapter.notifyDataSetChanged();
	                	System.out.println(nume.get(i++));
	                }
	                
	                System.out.println("Succes");
	            }
	            @Override
	            public void onFailure(int statusCode, Header[] headers, java.lang.Throwable throwable, JSONObject errorResponse) {
	                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
	                System.out.println(errorResponse.toString());
	            }
	            @Override
	            public void onRetry(int retryNo) {
	                // called when request is retried
	            }
	        });
	       lista.setOnItemClickListener(new OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> adaptor, View arg1, int position,
	                    long id) {
	            		         			
	            		
	            		
	            		Intent intent2 = new Intent(ItemsList.this, ShoppingCart.class);
	            		intent2.putExtra("param_a",nume.get(position));
	            		startActivity(intent2);
	            		
	            }
	       }); 
	       
	       
	      /* System.out.println("Printing nume");System.out.println(nume.get(0));
	       System.out.println("lungimea: " + nume.size());*/
	       
		}
	 

}
