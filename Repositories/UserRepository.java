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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Model.User;

public class UserRepository{
	private User user;
	//declare file location for json
	private static File lj = new File("C:\\Users\\User\\eclipse-workspace\\TP055637 Java Assignment\\TP055637_Java_Assignment\\Repositories\\repository\\librarian.json");
	private static File sj = new File("C:\\Users\\User\\eclipse-workspace\\TP055637 Java Assignment\\TP055637_Java_Assignment\\Repositories\\repository\\student.json");
	private static File stj = new File("C:\\Users\\User\\eclipse-workspace\\TP055637 Java Assignment\\TP055637_Java_Assignment\\Repositories\\repository\\staff.json");
	
public UserRepository (User user){
	this.user= user;
	}

	public static boolean Register(User user){
		Gson gson = new GsonBuilder().create(); //create a medium that converts java objects into json representaion.
		try {
			JSONParser jp = new JSONParser(); //creates a new jsonparser
			Object obj = jp.parse(new FileReader(lj)); //reads librarian.json and parse it into an jsonobject
			JSONArray librarianList = (JSONArray)obj; //converts the jsonobject into jsonarray
			Iterator<JSONObject> it = librarianList.iterator(); //tool that helps to loop the jsonarray
			List<User> librarian = new ArrayList<>(); //declare a list
			while(it.hasNext()) {
				librarian.add(gson.fromJson(it.next().toString(), User.class)); //whenever the itreator loads a new line, it is copied to the list line by line
			}	
			librarian.add(user); //add a new line to the list which is obtained from user input
			Writer w = new FileWriter(lj); 
			gson.toJson(librarian, w); //overwrites the items in list into json array using gson and filewriter
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

	public static boolean login(String email, String pass) {
		Boolean b;
		Gson gson = new GsonBuilder().create();
		JSONParser jp = new JSONParser();
		Object obj = null;
		try {
			obj = jp.parse(new FileReader(lj));
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
		JSONArray librarianList = (JSONArray)obj;
		Iterator<JSONObject> it = librarianList.iterator();
		List<User> librarian = new ArrayList<>();
		
		while (it.hasNext()) {
			librarian.add(gson.fromJson(it.next().toString(), User.class));
		}
		b = false;
		
		for (int i =0; i<librarian.size(); i++) {
			if(librarian.get(i).getEmail().equals(email) && librarian.get(i).getPassword().equals(pass)) {
				b = true;
			}
		}
		return b;
	}
	
	public static String checkStudent(String ID, String name, String role) {
		Gson gson = new GsonBuilder().create();
		JSONParser jp = new JSONParser();
		Object obj = null;
		try {
			obj = jp.parse(new FileReader(sj));
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
		JSONArray studentList = (JSONArray)obj;
		Iterator<JSONObject> it = studentList.iterator();
		List<User> student = new ArrayList<>();
		
		while (it.hasNext()) {
			student.add(gson.fromJson(it.next().toString(), User.class));
		}
		
		for (int i =0; i<student.size(); i++) {
			if(student.get(i).getID().equals(ID) && student.get(i).getRole().equals(role)) {		
				name = student.get(i).getName();
				return name;
			}
		}
		return null;
	}
	
	public static String checkStaff(String ID, String name, String role) {
		Gson gson = new GsonBuilder().create();
		JSONParser jp = new JSONParser();
		Object obj = null;
		try {
			obj = jp.parse(new FileReader(stj));
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
		JSONArray staffList = (JSONArray)obj;
		Iterator<JSONObject> it = staffList.iterator();
		List<User> staff = new ArrayList<>();
		
		while (it.hasNext()) {
			staff.add(gson.fromJson(it.next().toString(), User.class));
		}
		
		for (int i =0; i<staff.size(); i++) {
			if(staff.get(i).getID().equals(ID) && staff.get(i).getRole().equals(role)) {
				name = staff.get(i).getName();
				return name;
			}
		}
		return null;
	}
}
