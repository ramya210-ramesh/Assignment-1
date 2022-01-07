package com.te.Assignment.string;

public class ReverseString {
	public static void main(String[] args) {
		String s="Ramya";
		String s1="";
//		String words[]=s.split("");
		for(int i=s.length()-1;i>=0;i--)
//		{
//			s1=s1+new StringBuffer(words[i].reverse());
//			if(i<words.length-1)
//			s1=s1+"";
//		}
			s1=s1+s.charAt(i);
		System.out.println(s1);
	}

}
