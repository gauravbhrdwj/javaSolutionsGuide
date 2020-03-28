package com.blogspot.javasolutionsguide.convert;

import org.apache.commons.lang3.math.NumberUtils;
import com.google.common.primitives.Ints;

public class StringToInt {
	
	public static void main(String[] args) {
		String empId1 = "1001";
        int intEmpId1 = Integer.parseInt(empId1);
		System.out.println(intEmpId1);
		
		String empId2 = "2001";
		Integer integerEmpId2 = Integer.valueOf(empId2);
		System.out.println(integerEmpId2);
		
		String empId3 = "3001";
		int intEmpId3 = NumberUtils.toInt(empId3);
		System.out.println(intEmpId3);
		
		int intEmpId4 = NumberUtils.toInt(null);
		System.out.println(intEmpId4);		
		
		int intEmpId5 = NumberUtils.toInt("1001ABC");
		System.out.println(intEmpId5);

		int intEmpId6 = NumberUtils.toInt("1001ABC", 10);
		System.out.println(intEmpId6);
		
		String empId4 = "4001";
		Integer integerEmpId7 = NumberUtils.createInteger(empId4);
		System.out.println(integerEmpId7);
		
		String empId5 = "5001";
		Integer integerEmpId8 = Ints.tryParse(empId5);
		System.out.println(integerEmpId8);
		
		String empId6 = "6001";
		Integer integerEmpId9 = Integer.decode(empId6);
		System.out.println(integerEmpId9);
		
		String empId7 = "7001";
		//Below Constructor is deprecated,so not advised to use this.
		Integer integerEmpId10 = new Integer(empId7);
		System.out.println(integerEmpId10);
		
		// Following code snippets will give NumberFormatException.
		
		// Alphabets in the input.
		 Integer.parseInt("100AB");

		//Input number is greater than the Integer range.
		 Integer.parseInt("2147483648");

		//Number with decimal
		 Integer.parseInt("1.1"); 

		 //empty String
		 Integer.parseInt(""); 

		 //Blank space
		 Integer.parseInt(" "); 
	}
}
