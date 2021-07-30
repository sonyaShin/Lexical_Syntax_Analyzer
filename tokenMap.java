package lexical_syntax_analyzer;

import java.util.HashMap;
import java.util.regex.Pattern;

/*
 * tokenMap to be used for syntaxAnalyzer
 * saves tokens into hashmaps with token number(position)
 */
public class tokenMap {
		//regular expression
	 	public final static Pattern INT_REG = Pattern.compile("\\d+");
	    public final static Pattern FLOAT_REG= Pattern.compile("[+-]?(\\d+([.]\\d*)?([eE][+-]?\\d+)?|[.]\\d+([eE][+-]?\\d+)?)");
	    public final static Pattern VAR_REG = Pattern.compile("\\w+[\\_\\w\\d]*");
	    
	    //reserved words
	    //for, if, else, while, do, int, float, switch, class, void, bool.
	    
	    public final static String FOR_WORD = "for";
	    public final static String IF_WORD = "if";
	    public final static String ELSE_WORD = "else";
	    public final static String WHILE_WORD = "while";
	    public final static String DO_WORD = "do";
	    public final static String INT_WORD = "int";
	    public final static String FLOAT_WORD = "float";
	    public final static String SWITCH_WORD = "switch";	
	    public final static String CLASS_WORD = "class";	
	    public final static String VOID_WORD = "void";
	    public final static String BOOL_WORD = "bool";	
	    
	    //signs
	    public final static char ADD_SIGN = '+';
	    public final static char SUB_SIGN = '-';
	    public final static char MUL_SIGN = '*';
	    public final static char DIV_SIGN = '/';
	    public final static char REM_SIGN = '%';	    
	    public final static char LESSER_SIGN = '<';
	    public final static char GREATER_SIGN = '>';
	    public final static char ASSIGN_SIGN = '=';
	    public final static char LEFT_PAREN_SIGN = '(';
	    public final static char RIGHT_PAREN_SIGN = ')';
	    public final static char LEFT_CURL_SIGN = '{';
	    public final static char RIGHT_CURL_SIGN = '}';
	    public final static char SEMI_SIGN = ';';
	    public final static char EX_SIGN= '!';
	    public final static String EQUAL_SIGN = "==";             
	 	public final static String NOT_EQUAL_SIGN = "!=";
	    
	    //token value
	    public final static int INT_LIT = 10;
	    public final static int FLOAT_LIT = 20;
	    public final static int VAR = 30;
	    public final static int ADD = 40;
	    public final static int SUB = 50;
	    public final static int MUL = 60;
	    public final static int DIV = 70;
	    public final static int REM = 80;
	    public final static int EX = 90;
	    public final static int LESSER = 100;
	    public final static int GREATER = 110;
	    public final static int EQUAL = 120;
	    public final static int NOT_EQUAL = 130;
	    public final static int ASSIGN = 140;
	    public final static int LEFT_PAREN = 150;
	    public final static int RIGHT_PAREN = 160;
	    public final static int LEFT_CURL = 170;   
	    public final static int RIGHT_CURL = 180; 
	    public final static int SEMI = 190; 
	    
	    public final static int FOR = 200;
	    public final static int IF = 210;
	    public final static int ELSE = 220;
	    public final static int WHILE = 230;
	    public final static int DO = 240;
	    public final static int INT= 250 ;
	    public final static int FLOAT = 260;
	    public final static int SWITCH = 270;	
	    public final static int CLASS = 280;	
	    public final static int VOID = 290;
	    public final static int BOOL = 300;
	    
	    //hashmap of tokenMap
	    public static final HashMap<Character, Integer> tokenMap = new HashMap<>();
	    static {    
	        tokenMap.put(ADD_SIGN, ADD);
	        tokenMap.put(SUB_SIGN, SUB);
	        tokenMap.put(MUL_SIGN, MUL);
	        tokenMap.put(DIV_SIGN, DIV);
	        tokenMap.put(REM_SIGN, REM);
	        tokenMap.put(LESSER_SIGN, LESSER);
	        tokenMap.put(GREATER_SIGN, GREATER);
	        tokenMap.put(LEFT_PAREN_SIGN, LEFT_PAREN);
	        tokenMap.put(RIGHT_PAREN_SIGN, RIGHT_PAREN);
	        tokenMap.put(LEFT_CURL_SIGN, LEFT_CURL); 
	        tokenMap.put(RIGHT_CURL_SIGN, RIGHT_CURL); 
	        tokenMap.put(SEMI_SIGN, SEMI);
	        tokenMap.put(EX_SIGN, EX);
	        tokenMap.put(ASSIGN_SIGN, ASSIGN);
	        	                 
	    } 

}
