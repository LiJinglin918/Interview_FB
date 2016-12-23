/*
Print JSON Formal String

"{"id": "0001",	"type": "donut","name": "Cake","ppu": 0.55, "batters":{"batter":[{ "id": "1001", "type": "Regular" },{ "id": "1002", "type": "Chocolate" }]},"topping":[{ "id": "5001", "type": "None" },{ "id": "5002", "type": "Glazed" }]}"
we need to print it in following order:

{
	"id": "0001",
	"type": "donut",
	"name": "Cake",
	"ppu": 0.55,
	"batters":
		{
			"batter":
				[
					{ "id": "1001", "type": "Regular" },
					{ "id": "1002", "type": "Chocolate" }
				]
		},
	"topping":
		[
			{ "id": "5001", "type": "None" },
			{ "id": "5002", "type": "Glazed" }
		]
}
*/

import java.util.*;
public class PrintJSONFormalString {
	public static void main(String[] args) {
		String test = "";
		test += "{\"id\": \"0001\",\"type\": \"donut\",\"name\": \"Cake\",\"ppu\": 0.55,"
				+ "\"batters\":{\"batter\":[{ \"id\": \"1001\", \"type\": \"Regular\" },{ \"id\": \"1002\", \"type\": \"Chocolate\" }]},"
				+ "\"topping\":[{ \"id\": \"5001\", \"type\": \"None\" },{ \"id\": \"5002\", \"type\": \"Glazed\" }]}";
		
		new PrintJSONFormalString().printJsonString(test);
		new PrintJSONFormalString();
		System.out.println(PrintJSONFormalString.generate(test));
	}
	
/*-------- Print ---------------------------------------------------------------------------------------------*/
	void printJsonString(String jsonStr){
		if(jsonStr == null || jsonStr.trim().length() == 0){
			System.out.println(jsonStr);
			return;
		}
		final String ret = "\n";
		StringBuilder formattedJson = new StringBuilder();
		StringBuilder spaces = new StringBuilder();
		Deque<Character> stack = new ArrayDeque<>();
		for(int i=0; i<jsonStr.length(); ){
			char c = jsonStr.charAt(i);
			switch(c){
			case '{':
			case '[':
				stack.push(c);
				spaces.append("\t");
				formattedJson.append(c).append(ret).append(spaces);
				i++;
				break;
			case '}':
			case ']':
				stack.pop();
				spaces.deleteCharAt(spaces.length()-1);
				formattedJson.append(ret).append(spaces).append(c);
				i++;
				if(!(i<jsonStr.length() && jsonStr.charAt(i) == ',')){
					formattedJson.append(ret).append(spaces);
				}
				break;
			case ',':
				formattedJson.append(c).append(ret).append(spaces);
				i++;
				break;
			default:
				formattedJson.append(c);
				i++;
				break;
			}
		}
		System.out.println(formattedJson);
	}
	
/*------ 变体，返回的是String -------------------------------------------------------------------------------------------------*/
	
	private static String generate(String jsonString) {
		 StringBuilder builder = new StringBuilder();
		 StringBuilder spaces = new StringBuilder();
		 
		 for (int i = 0; i < jsonString.length(); i ++) {
			 char c = jsonString.charAt(i);
			 switch (c) {
			 	case '{' :
			 		spaces.append(' ');
			 		builder.append(c).append('\n').append(spaces);
			 		break;
			 	case '[' :
			 		spaces.append('\t');
			 		builder.append(c).append('\n').append(spaces);
			 		break;
			 	case ']' :
			 		builder.append('\n').append(spaces).append(c);
			 		spaces.deleteCharAt(spaces.length()-1);
			 		break;
			 	case '}' : 
			 		spaces.deleteCharAt(spaces.length()-1);
			 		builder.append('\n').append(spaces).append(c);
			 		break;
			 	case ',':
			 		builder.append(c).append('\n').append(spaces);
			 		break;
			 	default :
			 		builder.append(c);
			 }
		 }
		 
		 return builder.toString();
	 }
}
