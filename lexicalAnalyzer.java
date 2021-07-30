package lexical_syntax_analyzer;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


//@Seonju Shin
/*
 * lexical analyzer
 */

/*
 * List of lexmes
 * 
 * 	int_lit = \\d+
 *	float_lit = [+-]?(\\d+([.]\\d*)?([eE][+-]?\\d+)?|[.]\\d+([eE][+-]?\\d+)?)
 * 	variable = \\w+[\\_\\w\\d]*
 * 	plus = +
 * 	minus = -
 * 	division = /
 * 	multiply = *
 * 	remainder = %
 * 	lesser = <
 * 	greater = >
 * 	assignment = =
 * 	exclamation mark = !
 * 	left parenthesis = (
 * 	right parenthesis = )
 * 	left curly braces = {
 * 	right curly braces = }
 * 	semi-colon = ;
 * 	equal = ==
 * 	not eqaul = !=
 * 
 * Reserved words
 * 	for, if, else, while, do, int, float, switch, class, int, void, bool
 * 
 */



public class lexicalAnalyzer {
	/*
	 * type of lexemes
	 */
    static enum Type {
        INT_LIT, FLOAT_LIT, VAR, ADD, SUBTRACT, MULTIPLY, DIVIDE, REMAINDER, LEFT_PAREN, RIGHT_PAREN, LEFT_CURL,RIGHT_CURL,EX,SEMI,EQUAL,NOT_EQUAL,ASSIGN,GREATER, LESSER,
        FOR,IF,ELSE,WHILE,DO,INT,FLOAT,SWITCH,CLASS,INT_W,VOID,BOOL;
        ; 
    }
    /*
     * regular expression for int_literal, float_literal and variable
     */
    private static final Pattern INT_REG = Pattern.compile("\\d+");
	private static final Pattern FLOAT_REG = Pattern.compile("[+-]?(\\d+([.]\\d*)?([eE][+-]?\\d+)?|[.]\\d+([eE][+-]?\\d+)?)");
	private static final Pattern VAR_REG = Pattern.compile("\\w+[\\_\\w\\d]*");
   
	/*
	 * Token class holds the type and value of token
	 */
    static class Token<TokenType, TokenValue> {
        private final TokenType t;
        private final TokenValue value;
        
        /*
         * token constructor
         */
        public Token(TokenType t, TokenValue value) {
            this.t = t;
            this.value = value;
        }
        
        /*
         * returns the token's value and lexical type
         */
       @Override
        public String toString() {
            return "Token : " + this.value + System.getProperty("line.separator") + "Token lexeme--> " + this.t;
        }

		
    }
   
    private final static LinkedList<Token<Type, String>> tokens = new LinkedList<>();
    private final static List<Integer> tokenSyntax = new ArrayList<>();
   

    /*
     * gets either if string is int_literal, float_literal, or variable. it picks that string out from text
     * and returns the string 
     */
    private static String getOperand(String operand, int index) {
        int i = index;
        for( ; i < operand.length(); ) {
            if((Character.isLetterOrDigit(operand.charAt(i))) || operand.charAt(i) == ('.') || operand.charAt(i) == ('_')) {
                i++;
            }
            else {
                return operand.substring(index, i);
            }
        }
        return operand.substring(index, i);
    }
    

    /*
     * lexically analyzes the expression then return linked list of tokens
     */
    static LinkedList<Token<Type, String>> anaylze(String expression) {
        //LinkedList<Token<Type, String>> tokens = new LinkedList<>();
        for(int i = 0; i < expression.length(); ) {
            char currentCharacter = expression.charAt(i);
            switch(currentCharacter) {
                case '+':
                    tokens.add(new Token<>(Type.ADD, String.valueOf(currentCharacter)));
                    tokenSyntax.add(tokenMap.ADD);
                    i++;
                    break;
                case '-':
                    tokens.add(new Token<>(Type.SUBTRACT, String.valueOf(currentCharacter)));
                    tokenSyntax.add(tokenMap.SUB);
                    i++;
                    break;
                case '*':
                    tokens.add(new Token<>(Type.MULTIPLY, String.valueOf(currentCharacter)));
                    tokenSyntax.add(tokenMap.MUL);
                    i++;
                    break;
                case '/':
                    tokens.add(new Token<>(Type.DIVIDE, String.valueOf(currentCharacter)));
                    tokenSyntax.add(tokenMap.DIV);
                    i++;
                    break;
                case '%':
                    tokens.add(new Token<>(Type.REMAINDER, String.valueOf(currentCharacter)));
                    tokenSyntax.add(tokenMap.REM);
                    i++;
                    break;
                case '=':
                	if(((char)expression.charAt(i+1)) == '='){
                		tokens.add(new Token<>(Type.EQUAL, String.valueOf((String)expression.substring(i-1, i+2))));
                		tokenSyntax.add(tokenMap.EQUAL);
                		i+=2;
                		break;
                	}
                	tokens.add(new Token<>(Type.ASSIGN, String.valueOf(currentCharacter)));
                	tokenSyntax.add(tokenMap.ASSIGN);
                	i++;
                	break;
                case '!':
                	if(((char)expression.charAt(i+1)) == '='){
                		tokens.add(new Token<>(Type.NOT_EQUAL, String.valueOf((String)expression.substring(i-1, i+2))));
                		tokenSyntax.add(tokenMap.NOT_EQUAL);
                		i+=2;
                		break;
                	}
                	tokens.add(new Token<>(Type.EX, String.valueOf(currentCharacter)));
                	i++;
                case '>':
                	tokens.add(new Token<>(Type.GREATER,String.valueOf(currentCharacter)));
                	tokenSyntax.add(tokenMap.GREATER);
                	i++;
                	break;
                case '<':
                	tokens.add(new Token<>(Type.LESSER,String.valueOf(currentCharacter)));
                	tokenSyntax.add(tokenMap.LESSER);
                	i++;
                	break;
                case ';':
                	tokens.add(new Token<>(Type.SEMI, String.valueOf(currentCharacter)));
                	tokenSyntax.add(tokenMap.SEMI);
                	i++;
                	break;
                case '(':
                	tokens.add(new Token<>(Type.LEFT_PAREN, String.valueOf(currentCharacter)));
                	tokenSyntax.add(tokenMap.LEFT_PAREN);
                	i++;
                	break;
                case ')':
                	tokens.add(new Token<>(Type.RIGHT_PAREN, String.valueOf(currentCharacter)));
                	tokenSyntax.add(tokenMap.RIGHT_PAREN);
                	i++;
                	break;
                case '{':
                	tokens.add(new Token<>(Type.LEFT_CURL, String.valueOf(currentCharacter)));
                	tokenSyntax.add(tokenMap.LEFT_CURL);
                	i++;
                	break;
                case '}':
                	tokens.add(new Token<>(Type.RIGHT_CURL, String.valueOf(currentCharacter)));
                	tokenSyntax.add(tokenMap.RIGHT_CURL);
                	i++;
                	break;
                default:
                    if(Character.isWhitespace(currentCharacter)) {
                        i++;
                    }
                    else {
                        //Get the operand and increment i(index) by the operand's length to continue parsing after that index
                        String operand = lexicalAnalyzer.getOperand(expression, i);
                        i += operand.length();
                        if (operand.equals(tokenMap.FOR_WORD)) {
                        	tokens.add(new Token<>(Type.FOR, operand));
                        	tokenSyntax.add(tokenMap.FOR);
                        }
                        else if (operand.equals(tokenMap.IF_WORD)) {
                        	tokens.add(new Token<>(Type.IF, operand));
                        	tokenSyntax.add(tokenMap.IF);
                        }
                        else if (operand.equals(tokenMap.ELSE_WORD)) {
                        	tokens.add(new Token<>(Type.ELSE, operand));
                        	tokenSyntax.add(tokenMap.ELSE);
                        }
                        else if (operand.equals(tokenMap.WHILE_WORD)) {
                        	tokens.add(new Token<>(Type.WHILE, operand));
                        	tokenSyntax.add(tokenMap.WHILE);
                        }
                        else if (operand.equals(tokenMap.DO_WORD)) {
                        	tokens.add(new Token<>(Type.DO, operand));
                        	tokenSyntax.add(tokenMap.DO);
                        }
                        else if (operand.equals(tokenMap.INT_WORD)) {
                        	tokens.add(new Token<>(Type.INT_W, operand));
                        	tokenSyntax.add(tokenMap.INT);
                        }
                        else if (operand.equals(tokenMap.FLOAT_WORD)) {
                        	tokens.add(new Token<>(Type.FLOAT, operand));
                        	tokenSyntax.add(tokenMap.FLOAT);
                        }
                        else if (operand.equals(tokenMap.SWITCH_WORD)) {
                        	tokens.add(new Token<>(Type.SWITCH, operand));
                        	tokenSyntax.add(tokenMap.SWITCH);
                        }
                        else if (operand.equals(tokenMap.CLASS_WORD)) {
                        	tokens.add(new Token<>(Type.CLASS, operand));
                        	tokenSyntax.add(tokenMap.CLASS);
                        }
                        else if (operand.equals(tokenMap.VOID_WORD)) {
                        	tokens.add(new Token<>(Type.VOID, operand));
                        	tokenSyntax.add(tokenMap.VOID);
                        }
                        else if (operand.equals(tokenMap.BOOL_WORD)) {
                        	tokens.add(new Token<>(Type.BOOL, operand));
                        	tokenSyntax.add(tokenMap.BOOL);
                        }
                        else if(INT_REG.matcher(operand).matches()) {
                        	tokens.add(new Token<>(Type.INT_LIT, operand));
                        	tokenSyntax.add(tokenMap.INT_LIT);
                        }
                        else if(FLOAT_REG.matcher(operand).matches()) {
                        	tokens.add(new Token<>(Type.FLOAT_LIT, operand));    
                        	tokenSyntax.add(tokenMap.FLOAT_LIT);
                        	}
                        else if(VAR_REG.matcher(operand).matches()){
                        	tokens.add(new Token<>(Type.VAR, operand));
                        	tokenSyntax.add(tokenMap.VAR);
                        }
                    }
                    break;
            }
        }
        return tokens;
        
       
     }
    
    /*
     * returns tokenSyntax which is tokenlist for syntaxAnalyze
     */
     public static List<Integer> getTokens() {
		    return tokenSyntax;
		}

     //ends of lexicalAnalyzer.java
     
     
    /*
     * Main driver of lexical and syntax analyzer
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	//read file path
    	//type the file path which you downloaded the test.txt
    	
    	Scanner scan = new Scanner(System.in);
    	String path;
    	System.out.println("Enter file path:");
    	path = scan.nextLine();
    		
    	//converting file to string
    	FileReader fr=new FileReader(path);   
    	
    	String file;
    	StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) 
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) 
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
         	file = contentBuilder.toString();
		
      
         	
        List<Token<Type, String>> tokens = lexicalAnalyzer.anaylze(file);
        List<Integer> token2 = getTokens();
        List<Integer> lexToken3 = new ArrayList<>(token2);
        for(Token token : tokens) {
           System.out.println(token);
        }
       
       syntaxAnalyzer syntax = new syntaxAnalyzer(lexToken3);
       syntax.syntasize();
 
       
       
    }
    

}

