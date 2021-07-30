package lexical_syntax_analyzer;

import java.util.List;

/*
 * This is syntax Analyzer which will be used in main function in lexicalAnalyzer.java
 */

public class syntaxAnalyzer {
	
 /*
  * <PROGRAM>   --> "{" <START> "}"
    <START>     --> <STMT> ";" {<STMT> ";"}
    <STMT>      --> IfStatement | WhileStatement | doWhileStatement | <EXPR> {"=" <EXPR>}
    <EXPR>      --> <TERM> {("==", "!=", "<", ">") <TERM>}
    <TERM>      --> <FACTOR> {("+", "-") <FACTOR>}
    <FACTOR>    --> <ID> {("*", "/", "%") <ID>}
    <ID>        --> var | int | float | "(" <STMT> ")"
    
    IfStatement  	  -->  if '('EXPR')' <STMT> [else Statement]
    WhileStatement 	  --> while '('EXPR')' Statement
    DoWhileStatement  --> do <STMT> While '(' EXPR ')'
    
     * 
  */
	
	
    private final List<Integer> tokenSyn;
    private int currentIndex = 0;
    
    public syntaxAnalyzer(List<Integer> tokenSyn){
        this.tokenSyn = tokenSyn;
    }
    
    public void syntasize(){
        PROGRAM();
    }
    //<PROGRAM> --> "{" <START> "}"
    private void PROGRAM(){
        if(tokenSyn.get(currentIndex) != tokenMap.LEFT_CURL) 
        	System.exit(invalid());
        currentIndex++;
        START();
        System.exit((tokenSyn.get(currentIndex) == tokenMap.RIGHT_CURL) ? valid() : invalid());
    }
    
    //<START>   --> <STMT> ";" {<STMT> ";"}
    private void START(){
        STMT();
        while(tokenSyn.get(currentIndex) == tokenMap.SEMI){
            getNextToken();
            if(tokenSyn.get(currentIndex) == tokenMap.RIGHT_CURL) return;
            STMT();            
        }
        if(tokenSyn.get(currentIndex) != tokenMap.SEMI) System.exit(invalid());
        currentIndex++;
    }    
    
    //<STMT>     --> <EXPR> {"=" <EXPR>}
    /*	IfStatement  	  -->  if '('EXPR')' <STMT> [else Statement]
    	WhileStatement 	  --> while '('EXPR')' Statement
    	DoWhileStatement  --> do <STMT> While '(' EXPR ')'
    */
    private void STMT(){
    	//if_Else_Statement
    	if(tokenSyn.get(currentIndex) == tokenMap.IF){
    		System.out.println(tokenSyn.get(currentIndex));
    		System.out.println("IF HERE");
    		getNextToken();
    		if(tokenSyn.get(currentIndex) == tokenMap.LEFT_PAREN) {		
    			getNextToken();
    			EXPR();
    			if(tokenSyn.get(currentIndex) == tokenMap.RIGHT_PAREN) {			
    				getNextToken();
    				STMT(); 				
    				if(tokenSyn.get(currentIndex) == tokenMap.ELSE) {
    					getNextToken();
    					STMT();
    				}
    			}
    		}
    	}
        //While Statement
    	if(tokenSyn.get(currentIndex) == tokenMap.WHILE) {
    		getNextToken();
    		if(tokenSyn.get(currentIndex) == tokenMap.LEFT_PAREN) {
    			getNextToken();
    			EXPR();
    			while(tokenSyn.get(currentIndex) == tokenMap.RIGHT_PAREN) {
    				getNextToken();
    				STMT();
    				System.out.println("WHILE HERE");
    				break;
    			}
    		}
    	}
    	
    	//DoWhileStatement  --> do <STMT> While '(' EXPR ')'
    	if(tokenSyn.get(currentIndex) == tokenMap.DO) {
    		getNextToken();
    		STMT();
    		if(tokenSyn.get(currentIndex) == tokenMap.WHILE) {
    			getNextToken();
    			if(tokenSyn.get(currentIndex) == tokenMap.LEFT_PAREN) {
    				getNextToken();
    				EXPR();
    				if(tokenSyn.get(currentIndex) != tokenMap.RIGHT_PAREN) {
    					System.exit(invalid());
    				}
    			}
    		}
    	}
    
    	else{
    		EXPR();
    		while(tokenSyn.get(currentIndex) == tokenMap.ASSIGN){
            	getNextToken();
            	EXPR();
        	}
    	}
    }
    	
    
    //<EXPR>      --> <TERM> {("==", "!=", "<", ">") <TERM>}
    private void EXPR(){
        TERM();
        while( tokenSyn.get(currentIndex) == tokenMap.EQUAL |tokenSyn.get(currentIndex) == tokenMap.NOT_EQUAL |tokenSyn.get(currentIndex) == tokenMap.LESSER |tokenSyn.get(currentIndex) == tokenMap.GREATER){
            getNextToken();
            TERM();            
        }
    }
    
    //<TERM>      --> <FACTOR> {("+", "-") <FACTOR>}
    private void TERM(){
        FACTOR();
        while( tokenSyn.get(currentIndex) == tokenMap.ADD|tokenSyn.get(currentIndex) == tokenMap.SUB) {
            getNextToken();
            FACTOR();
        }
    }
    
    //<FACTOR>    --> <ID> {("*", "/", "%") <ID>}
    private void FACTOR(){
        ID();
        while( tokenSyn.get(currentIndex) == tokenMap.MUL|tokenSyn.get(currentIndex) == tokenMap.DIV|tokenSyn.get(currentIndex) == tokenMap.REM){
            getNextToken();
            ID();
        }
    }
    
    //<ID> --> var | int | float | "(" <START> ")"
    private void ID(){
        switch(tokenSyn.get(currentIndex)){
            case tokenMap.INT_LIT:
                getNextToken();
                break;
            case tokenMap.FLOAT_LIT:
                getNextToken();
                break;
            case tokenMap.VAR:
                getNextToken();
                break;
            case tokenMap.LEFT_PAREN:
                getNextToken();
                STMT();
                if(tokenSyn.get(currentIndex) != tokenMap.RIGHT_PAREN) {
                    System.exit(invalid());
                } else {
                    getNextToken();
                }
                break;
            default:
                System.exit(invalid());               
        }
    }
    
    private void getNextToken(){
        currentIndex++;
        if(currentIndex >= tokenSyn.size()) System.exit(invalid());
    }
    
    private int valid(){
        System.out.println("Valid Syntax");
        return 0;
    }
    
    private int invalid(){
        System.out.println("Invalid Syntax");
        return 0;
    }
}
    

