package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Model.Cart;

public class CartRepository{
	
private Cart cart;

private static File f = new File("C:\\Users\\User\\eclipse-workspace\\TP055637 Java Assignment\\TP055637_Java_Assignment\\Repositories\\repository\\cart.json");

public CartRepository (Cart cart){
	this.cart = cart;
	}

	public static boolean add(Cart cart) {
		Gson gson = new GsonBuilder().create();
		try {
			JSONParser jp = new JSONParser();
			Object obj = jp.parse(new FileReader(f));
			JSONArray cartList = (JSONArray)obj;
			Iterator<JSONObject> it = cartList.iterator();
			List<Cart> cartdetail = new ArrayList<>(); 
			while(it.hasNext()) {
				cartdetail.add(gson.fromJson(it.next().toString(), Cart.class));
			}	
			cartdetail.add(cart);
			Writer w = new FileWriter(f);
			gson.toJson(cartdetail, w);
			w.flush();
			w.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean checkExisted(String id, String ISBN, String title, String category) {
		Boolean b;
		Gson gson = new GsonBuilder().create();
		JSONParser jp = new JSONParser();
		Object obj = null;
		try {
			obj = jp.parse(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray cartList = (JSONArray)obj;
		Iterator<JSONObject> it = cartList.iterator();
		List<Cart> cartdetail = new ArrayList<>();
		
		while (it.hasNext()) {
			cartdetail.add(gson.fromJson(it.next().toString(), Cart.class));
		}
		b = false;
		
		for (int i =0; i<cartdetail.size(); i++) {
			if(cartdetail.get(i).getId().equals(id) && cartdetail.get(i).getISBN().equals(ISBN) && cartdetail.get(i).getTitle().equals(title)&& cartdetail.get(i).getCategory().equals(category)) {
				b = true;
			} else {
				b = false;
			}
		}
		return b;
	}
	
	public static Vector<Vector<String>> filter(String id){
		Gson gson = new GsonBuilder().create();
		JSONParser jp = new JSONParser();
		Object obj = null;
		try {
			obj = jp.parse(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray cartList = (JSONArray)obj;
		Iterator<JSONObject> it = cartList.iterator();
		List<Cart> cartdetail = new ArrayList<>();
		while (it.hasNext()) {
			cartdetail.add(gson.fromJson(it.next().toString(), Cart.class));
		}
		
		Vector<Vector<String>> cartdata = new Vector<>();
		for (int i =0; i<cartList.size(); i++) {
			if (cartdetail.get(i).getId().equals(id)) {
				Vector<String> cart = new Vector<>();			
				cart.add(String.valueOf(cartdetail.get(i).getISBN()));
				cartdata.add(cart);
			}
		}
		
		return cartdata;
	}
	
	public static String [] showDetails(String id, String ISBN) {
		Gson gson = new GsonBuilder().create();
		JSONParser jp = new JSONParser();
		Object obj = null;
		try {
			obj = jp.parse(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray cartList = (JSONArray)obj;
		Iterator<JSONObject> it = cartList.iterator();
		List<Cart> cartdetail = new ArrayList<>();
		
		while (it.hasNext()) {
			cartdetail.add(gson.fromJson(it.next().toString(), Cart.class));
		}
		
		for (int i =0; i<cartdetail.size(); i++) {
			if(cartdetail.get(i).getId().equals(id)&& cartdetail.get(i).getISBN().equals(ISBN)) {
				String [] details = new String [5];
				
				details [0] = cartdetail.get(i).getTitle();
				details [1] = cartdetail.get(i).getBorrowDate();
				details [2] = cartdetail.get(i).getCategory();
				details [3] = String.valueOf(cartdetail.get(i).getDuration());
				details [4] = cartdetail.get(i).getDueDate();
				
				return details;
			}
		}
		return null;
		
	}
	
	public static boolean checkRenew(String id, String ISBN, boolean status) {
		Boolean b;
		Gson gson = new GsonBuilder().create();
		JSONParser jp = new JSONParser();
		Object obj = null;
		try {
			obj = jp.parse(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray cartList = (JSONArray)obj;
		Iterator<JSONObject> it = cartList.iterator();
		List<Cart> cartdetail = new ArrayList<>();
		
		while (it.hasNext()) {
			cartdetail.add(gson.fromJson(it.next().toString(), Cart.class));
		}
		b = true;
		
		for (int i =0; i<cartdetail.size(); i++) {
			if(cartdetail.get(i).getId().equals(id) && cartdetail.get(i).getISBN().equals(ISBN)) {
				status = cartdetail.get(i).isStatus();
				if (status == true) {
					b = true;
				} else {
					b = false;
				}
			} else {
				b = false;
			}
		}
		return b;
	}
	
	public static boolean renew(String id, String ISBN, int duration, String dueDate) {
		Boolean b;
		LocalDate newDue;
		int newDuration;
		boolean status;
		Gson gson = new GsonBuilder().create();
		JSONParser jp = new JSONParser();
		Object obj = null;
		try {
			obj = jp.parse(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray cartList = (JSONArray)obj;
		Iterator<JSONObject> it = cartList.iterator();
		List<Cart> cartdetail = new ArrayList<>();
		
		while (it.hasNext()) {
			cartdetail.add(gson.fromJson(it.next().toString(), Cart.class));
		}
		b = true;
		
		for (int i =0; i<cartdetail.size(); i++) {
			if(cartdetail.get(i).getId().equals(id) && cartdetail.get(i).getISBN().equals(ISBN)) {
				newDue = LocalDate.parse(dueDate).plusDays(duration);
				newDuration = duration + duration;
				status = false;
				
				cartdetail.get(i).setDueDate(String.valueOf(newDue));
				cartdetail.get(i).setDuration(newDuration);
				cartdetail.get(i).setStatus(status);
				Writer w;
				try {
					w = new FileWriter(f);
					gson.toJson(cartdetail, w);
					w.flush();
					w.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				b = true;
				return b;		
			} else {
				b = false;
			}
		}
		return b;
	}
	
	public static boolean returnBook(String id, String ISBN) {
		Boolean b;
		Gson gson = new GsonBuilder().create();
		JSONParser jp = new JSONParser();
		Object obj = null;
		try {
			obj = jp.parse(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray cartList = (JSONArray)obj;
		Iterator<JSONObject> it = cartList.iterator();
		List<Cart> cartdetail = new ArrayList<>();
		
		while (it.hasNext()) {
			cartdetail.add(gson.fromJson(it.next().toString(), Cart.class));
		}
		b = true;
		
		for (int i =0; i<cartdetail.size(); i++) {
			if(cartdetail.get(i).getId().equals(id) && cartdetail.get(i).getISBN().equals(ISBN)) {
				cartdetail.remove(i);
				Writer w;
				try {
					w = new FileWriter(f);
					gson.toJson(cartdetail, w);
					w.flush();
					w.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				b = true;
				return b;		
			} else {
				b = false;
			}
		}
		return b;
	}
	
	public static int showDuration(String id, String ISBN, int duration) {
		Gson gson = new GsonBuilder().create();
		JSONParser jp = new JSONParser();
		Object obj = null;
		try {
			obj = jp.parse(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray cartList = (JSONArray)obj;
		Iterator<JSONObject> it = cartList.iterator();
		List<Cart> cartdetail = new ArrayList<>();
		
		while (it.hasNext()) {
			cartdetail.add(gson.fromJson(it.next().toString(), Cart.class));
		}
		
		for(int i = 0; i< cartdetail.size(); i++) {
			if (cartdetail.get(i).getId().equals(id) && cartdetail.get(i).getISBN().equals(ISBN)) {
				duration = cartdetail.get(i).getDuration();
				
				return duration;
			}
		}
		return 0;
	}
}