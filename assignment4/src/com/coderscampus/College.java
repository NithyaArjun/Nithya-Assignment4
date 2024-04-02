package com.coderscampus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class College {

	public static void main(String[] args) throws IOException {
		String[] COMP = new String[50];
		String[] STAT = new String[50];
		String[] APMTH = new String[50];
		String filename ="student-master-list.csv";
		BufferedReader Reader = new BufferedReader(new FileReader(filename));
		String line;
		while((line=Reader.readLine())!=null){
			String[] students = line.split(",");
			String course = students[2].trim();
		    String  grade =students[3];
			String studentinfo = students[0]+","+students[1]+","+course+","+grade;					
			if(studentinfo.contains("COMPS")) {
				getInfo(COMP,studentinfo);
				
			}
			else if(studentinfo.contains("STAT")) {
				getInfo(STAT,studentinfo);
			}
			else if(studentinfo.contains("APMTH")) {
				getInfo(APMTH,studentinfo);
			}
			
		}	
		sortstudent(COMP);
		sortstudent(STAT);
		sortstudent(APMTH);
		writeToFiles(COMP,"course1.csv");
		writeToFiles(STAT,"course2.csv");
		writeToFiles(APMTH,"course3.csv");
		Reader.close();
	}
	
	private static void getInfo(String[] studentcourse, String studentinfo) {
		for(int i=0; i < studentcourse.length; i++) {
			if(studentcourse[i] == null) {
				studentcourse[i] = studentinfo;
				break;
			}
		}
	}
	private static void sortstudent(String[] coursestudents) {
		Arrays.sort(coursestudents, Comparator.nullsLast(Comparator.comparingInt(s -> {
            if (s == null || ((String) s).startsWith("Student ID")) return Integer.MIN_VALUE;
            
            String[] data = ((String) s).split(",");
            return Integer.parseInt(data[3].trim());
            
        }).reversed()));
	}
    
	public static void writeToFiles(String[] students,String filename) throws IOException {
		FileWriter writer = new FileWriter(filename);
		 writer.write("Student ID,Student Name,Course,Grade\n");
         for (String student : students) {
             if (student != null) {
                 writer.write(student + "\n");
             }
         }
         writer.close();
        
	}
}

	