	import java.io.*;
	import java.util.*;

	public class Main {
	   
	  public static String lexmen = "";
	  public  static HashMap<String, Integer> symbols = new HashMap<>();
    public static int token; 
	   
	  public static void main(String args[]) throws IOException {

	  specialsymbols();
 
      String str;

        BufferedReader br = new BufferedReader(new FileReader("file.txt"));

        while ((str = br.readLine()) != null)
        {
            

            for(int i = 0; i < str.length(); i++){


                identify(str.charAt(i), false);
              
            }
            
        }
        identify(' ', true);
        

        System.out.println("Next token is: -1 , Next Lexmen is  EOF");

	    }

	    
	   static void specialsymbols(){

	        symbols.put("letter",0);
	        symbols.put("ident",3);
	        symbols.put("+",2);
	        symbols.put("-",10);
	        symbols.put("/",11);
	        symbols.put("*",14);
	        symbols.put("(",19);
	        symbols.put(")",22);
	        symbols.put("=",55);
	        symbols.put(".", 8);
          symbols.put("{", 44);
          symbols.put("}", 45);
          symbols.put(";", 1);
	        symbols.put("for", 30);
	        symbols.put("if", 31);
          symbols.put("else",32);
          symbols.put("while",33);
          symbols.put("do",34);
          symbols.put("int",35);
          symbols.put("float",36);
          symbols.put("switch",37);
          symbols.put("foreach", 40); //for each
          symbols.put("void", 41);
          symbols.put("assignment", 42);
          symbols.put("main", 43);
          symbols.put("return", 44);

	    }

	    static void identify(char a, boolean finalC){
	        boolean lookup = symbols.containsKey(a+"");
	        if(a == ' '){
	            lookup = true;
	        }
	        if(lookup == false){
	            lexmen += a+"";
	        }else{
	          int tokenId = floatliteral(lexmen) == false ? 0 : 15;
            tokenId = intliteral(lexmen) == false ? 0 : 8;
            
            if(tokenId == 0 && symbols.containsKey(lexmen)){//string
                tokenId = symbols.get(lexmen);
            }
         
      
            if(lexmen.length() > 1){
                System.out.println("Next token is: " + tokenId + ", Next Lexmen is " + lexmen);

                if(lexmen.equals("void")){
                  System.out.println("Enter Void \n");
                }
                if(lexmen.equals("main")){
                  System.out.println("Enter main \n");
                }
                if(lexmen.equals("return")){
                  System.out.println("Enter return \n");
                }
                if(lexmen.equals("==")){
                  System.out.println("Enter Assignment \n");
                }
            }  
           
        
                

            if(symbols.get(a+"") != null)
                System.out.println("Next token is: " +  symbols.get(a+"") + ", Next Lexmen is " + a);
        
              lexmen = "";
              switch(a){
                case '(':
                System.out.println("Enter Expression \n ");
                break; 
                case ')':
                System.out.println("Exit Expression \n");
                break; 
                case '{':
                System.out.println("Enter Block  \n");
                break;
                case '}':
                System.out.println("Exit Block \n");
                break;

              }

        }
        
  }
      
       static void voidSynCheck() throws FileNotFoundException, IOException{
        String line;
        String[] terms;
          BufferedReader br = new BufferedReader(new FileReader("file.txt"));
        while ((line = br.readLine()) != null)
        {
          terms =line.split(" ");
          for(String term :terms){
            if(term.equals("void")){
              System.out.println("Enter Void");
            }
            if(term.equals("main")){
              System.out.println("Enter main");
            }
          }
        }
      }

	    static boolean floatliteral(String str){
	        for(int i = 0; i < str.length(); i++){
	            if(str.charAt(i)=='.'){
	                return true;
	            }
	        }
	        return false;

	    }

	    static boolean intliteral(String str){
	        for(int i = 0; i < str.length(); i++){
	            if(Character.isLetter(str.charAt(i))){
	                return false;
	            }
	        }
	        return true;
	    }
	}