package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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

import Model.Book;
import Model.User;

public class BookRepository{
	
private Book book;

private static File f = new File("C:\\Users\\User\\eclipse-workspace\\TP055637 Java Assignment\\TP055637_Java_Assignment\\Repositories\\repository\\book.json");

public BookRepository (Book book){ //construct BookRespository which is related to Model.Book
	this.book= book;
	}

	public static boolean Filter(String ISBN) { 
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
		JSONArray bookList = (JSONArray)obj;
		Iterator<JSONObject> it = bookList.iterator();
		List<Book> bookdetail = new ArrayList<>();
		
		while (it.hasNext()) {
			bookdetail.add(gson.fromJson(it.next().toString(), Book.class));
		}
		b = false;
		
		for (int i =0; i<bookdetail.size(); i++) {
			if(bookdetail.get(i).getISBN().equals(ISBN)) {
				b = true;
			}
		}
		return b;
	}
	
	public static Vector<Vector<String>> display() {  
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
		JSONArray bookList = (JSONArray)obj;
		Iterator<JSONObject> it = bookList.iterator();
		List<Book> bookdetail = new ArrayList<>();
		while (it.hasNext()) {
			bookdetail.add(gson.fromJson(it.next().toString(), Book.class));
		}
		
		Vector<Vector<String>> bookdata = new Vector<>();  //declare a big vector for vector for string
		for (int i =0; i<bookList.size(); i++) {
			
			Vector<String> book = new Vector<>(); //declare vector for string
			
			book.add(String.valueOf(bookdetail.get(i).getISBN()));
			book.add(bookdetail.get(i).getTitle());
			book.add(bookdetail.get(i).getCategory());
			book.add(bookdetail.get(i).getAuthor());
			book.add(bookdetail.get(i).getPublisher());  //add lists of items into the vector
			
			bookdata.add(book); //add lists of vectors of string into a bigger vector
		}
		
		return bookdata; //return the vectors inside the big vector
	}
	
	//Overload
	public static Vector<Vector<String>> display(String ISBN) {
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
		JSONArray bookList = (JSONArray)obj;
		Iterator<JSONObject> it = bookList.iterator();
		List<Book> bookdetail = new ArrayList<>();
		while (it.hasNext()) {
			bookdetail.add(gson.fromJson(it.next().toString(), Book.class));
		}
		
		Vector<Vector<String>> bookdata = new Vector<>();
		for (int i =0; i<bookList.size(); i++) {
			if (bookdetail.get(i).getISBN().equals(ISBN)) {
				
				Vector<String> book = new Vector<>();
				
				book.add(String.valueOf(bookdetail.get(i).getISBN()));
				book.add(bookdetail.get(i).getTitle());
				book.add(bookdetail.get(i).getCategory());
				book.add(bookdetail.get(i).getAuthor());
				book.add(bookdetail.get(i).getPublisher());
				
				bookdata.add(book);
			}
		}
		
		return bookdata;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean insert(Book book) {
		Gson gson = new GsonBuilder().create();
		try {
			JSONParser jp = new JSONParser();
			Object obj = jp.parse(new FileReader(f));
			JSONArray bookList = (JSONArray)obj;
			Iterator<JSONObject> it = bookList.iterator();
			List<Book> bookdetail = new ArrayList<>(); 
			while(it.hasNext()) {
				bookdetail.add(gson.fromJson(it.next().toString(), Book.class));
			}	
			bookdetail.add(book);
			Writer w = new FileWriter(f);
			gson.toJson(bookdetail, w);
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
	
	public static String [] showDetail(String ISBN) {
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
		JSONArray bookList = (JSONArray)obj;
		Iterator<JSONObject> it = bookList.iterator();
		List<Book> bookdetail = new ArrayList<>();
		
		while (it.hasNext()) {
			bookdetail.add(gson.fromJson(it.next().toString(), Book.class));
		}
		
		for (int i =0; i<bookdetail.size(); i++) {
			if(bookdetail.get(i).getISBN().equals(ISBN)) {
				String [] details = new String[4];
				
				details[0] = bookdetail.get(i).getTitle();
				details[1] = bookdetail.get(i).getCategory();
				details[2] = bookdetail.get(i).getAuthor(); 	
				details[3] = bookdetail.get(i).getPublisher();
				
				return details; //array is called to record the returned lists of items that passes the if statement then the array is returned by the function
			}
		}
		return null;
	}
}