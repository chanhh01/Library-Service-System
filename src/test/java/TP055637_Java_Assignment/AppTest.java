package TP055637_Java_Assignment;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import Model.User;
import repository.BookRepository;
import repository.UserRepository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
	private String name;
	private String role;
	private static String author;
	private static String publisher;
	private String id;
	private static String ISBN;
	private static String title;
	private static String category;
	private Boolean status;
	private int duration;
	private LocalDate borrow;
	private LocalDate due;
	private String dueDate;
	private String borrowDate;
    @Test
    public static void main(String[] args)
    {
    	
    	LocalDate current = LocalDate.now();
    	int currentDay = current.getDayOfMonth();
    	LocalDate borrow = LocalDate.parse("2021-03-18");
    	LocalDate due = LocalDate.parse("2021-04-01");
    	int dueDay = due.getDayOfMonth();
    	
    	long monthsBetween = ChronoUnit.DAYS.between(
    			LocalDate.now().withDayOfMonth(1),
    	        LocalDate.parse("2021-04-01").withDayOfMonth(1));
    	System.out.println(monthsBetween);
    }
}
