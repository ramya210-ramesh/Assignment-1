package com.te.Assignment.string;

public class Palindrone {
	public static void reverse(String s) {
		String s1 = "";
		for (int i = s.length() - 1; i >= 0; i--)
			s1 = s1 + s.charAt(i);
		System.out.println(s1.equals(s) ? s1 + "" : "");
	}

	public static void main(String[] args) {
		String s = "Madam Malayalam is a different level langauage";
		String s1 = "";
		for (int i = 0; i < s.length(); i++) {
			if (i == s.length() - 1 || s.charAt(i) == ' ') {
				if (i == s.length() - 1)
					s1 = s1 + s.charAt(s.length() - 1);
				reverse(s1);
				i++;
				s1 = "";
			}
			if (i < s.length())
				s1 = s1 + s.charAt(i);
		}
	}
}
