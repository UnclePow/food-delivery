package uestc.learning.utils;

public class NameUtils {
	public static String convert2Camel(String input) {
		String[] temp = input.split("_");
		StringBuilder sBuilder = new StringBuilder();
		for(int i = 0; i < temp.length; i++) {
			if(i==0) {
				sBuilder.append(temp[i]);
			}
			else {
				String firstAlp = temp[i].substring(0, 1).toUpperCase();
				sBuilder.append(firstAlp);
				sBuilder.append(temp[i].substring(1, temp[i].length()));
			}
		}
		
		return sBuilder.toString();
	}
	
	public static String firstUpper(String varName) {
		StringBuilder varSb = new StringBuilder();
		String firstAlp = varName.substring(0, 1).toUpperCase();
		varSb.append(firstAlp);
		//×·¼ÓÆäËû×ÖÄ¸
		varSb.append(varName.substring(1, varName.length()));
		return varSb.toString();
	}
	
	public static void main(String[] args) {
		String testStr = "hello_world";
		System.out.print(convert2Camel(testStr));
	}
}
