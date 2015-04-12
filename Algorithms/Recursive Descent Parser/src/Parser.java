package a2;

import java.util.LinkedList;
import java.util.ListIterator;

/**

     This class provides a recursive descent parser              
     for the grammar with productions
       Expr -> Literal | Var | FCall | LetExpr | IfExpr
       Literal -> ListLiteral | SymbolLiteral
       ListLiteral -> [ {Literal} ]
       FCall -> FName ( {Expr} )
       FName -> UserFName | PrimFName
       LetExpr -> let {Def} Expr
       Def -> define Sig Expr
       Sig -> UserFName ( {Var} )
       IfExpr -> if Expr Expr Expr
       
    This version of the parser produces syntax trees instead of
    * parse trees.
    
     @author Kevin Johnson
     @version for Assignment 2, CS 152, SJSU, Spring 2014 
 */
class Parser
{
   //list of tokens to be looked at
   private LinkedList<Token> tokens = null;
   //iterator that iterates through the list of tokens
   private ListIterator<Token> iterator = null;
   //a lookahead to see what token is ahead by 1 spot
   private Token lookahead = null;
   
   
   /**
       Parses a given string using the given language
       @param input the string of language to be parsed.
       @return a tree of the parsed language
    */
   public OrderedTree<Token> parse(String input)
   {
      tokens = Token.tokenize(input);
      OrderedTree<Token> tree = new OrderedTree(tokens);
      iterator = tokens.listIterator();
      nextToken(); // initilize iterator to first element of tokens
      tree = parseExpr();
      if (!lookahead.getType().equals("end-of-input"))
      {
         throw new IllegalArgumentException(errorString("end-of-file"));
      }


      return tree;
   }

   /**
       Parses the rules of an Expression
       @return the children of the parsed expression
    */   
   private OrderedTree<Token> parseExpr()
   {
      LinkedList<OrderedTree<Token>> children = new LinkedList<OrderedTree<Token>>();

      if (lookahead.getType().equals("SymbolLiteral") || lookahead.getType().equals("["))
      {
         return parseLiteral();
      } else if (lookahead.getType().equals("Var"))
      {
         return parseVar();
      } else if (lookahead.getType().equals("UserFName") || lookahead.getType().equals("PrimFName"))
      {
         return parseFCall();
      } else if (lookahead.getType().equals("if"))
      {
         return parseIfExpr();
      } else if (lookahead.getType().equals("let"))
      {
         return parseLetExpr();
      } else
      {
         throw new IllegalArgumentException(errorString("Expr"));
      }
   }
   /**
       Parses the rules for a Variable
       @return the children of the parsed variable
    */
   private OrderedTree<Token> parseVar()
   {
      return new OrderedTree<Token>(match("Var"));

   }
   /**
       Parses the rules for a Literal
       @return the children of the parsed Literal
    */
   private OrderedTree<Token> parseLiteral()
   {
      LinkedList<OrderedTree<Token>> children = new LinkedList<OrderedTree<Token>>();
      if (lookahead.getType().equals("SymbolLiteral"))
      {
         return new OrderedTree<Token>(match("SymbolLiteral"));
      } // else it is a ListLiteral from before call check
      else
      {
         match("[");
         while (lookahead.getType().equals("SymbolLiteral"))
         {
            children.add(new OrderedTree<Token>(match("SymbolLiteral")));
         }
         match("]");
         return new OrderedTree<Token>(new Token("List"), children);
      }


   }
   /**
       Parses the rules for a FName
       @return the children of the parsed FName
    */
   private Token parseFName()
   {
      if (lookahead.getType().equals("PrimFName"))
      {
         return match("PrimFName");
      } else
      {
         return match("UserFName");
      }

   }
   /**
       Parses the rules for a Sig
       @return the children of the parsed Sig
    */
   private OrderedTree<Token> parseSig()
   {
      LinkedList<OrderedTree<Token>> children = new LinkedList<OrderedTree<Token>>();
      Token temp = match("UserFName");
      match("(");
      while (lookahead.getType().equals("Var"))
      {
         children.add(parseVar());
      }
      match(")");
      return new OrderedTree<Token> (temp,children);
   }
   /**
       Parses the rules for a define statment
       @return the children of the parsed define statment
    */
   private OrderedTree<Token> parseDef()
   {
      LinkedList<OrderedTree<Token>> children = new LinkedList<OrderedTree<Token>>();
      match("define");
      children.add(parseSig());
      children.add(parseExpr());
      return new OrderedTree<Token>(new Token("Def"), children);
   }
   /**
       Parses the rules for a let expression
       @return the children of the parsed let expression
    */
   private OrderedTree<Token> parseLetExpr()
   {
      LinkedList<OrderedTree<Token>> children = new LinkedList<OrderedTree<Token>>();
      match("let");
      while (lookahead.getType().equals("define"))
      {
         children.add(parseDef());
      }
      children.add(parseExpr());
      return new OrderedTree<Token>(new Token("LetExpr"), children);
   }
   /**
       Parses the rules for an FCall
       @return the children of the parsed FCall
    */
   private OrderedTree<Token> parseFCall()
   {
      LinkedList<OrderedTree<Token>> children = new LinkedList<OrderedTree<Token>>();
      Token temp = parseFName();
      match("(");
      while (!lookahead.getType().equals(")"))
      {
         children.add(parseExpr());
      }

      match(")");
      return new OrderedTree<Token>(temp,children);
   }
   /**
       Parses the rules for a If expression
       @return the children of the parsed if expression
    */
   private OrderedTree<Token> parseIfExpr()
   {
      LinkedList<OrderedTree<Token>> children = new LinkedList<OrderedTree<Token>>();
      match("if");
      children.add(parseExpr());
      children.add(parseExpr());
      children.add(parseExpr());
      return new OrderedTree<Token>(new Token("IfExpr"), children);
   }
   /**
       sets the lookahead to the next token of the iterator
    */
   private void nextToken()
   {
      if (iterator.hasNext())
      {
         lookahead = iterator.next();
      } else
      {
         lookahead = new Token();
      }


   }
   /**
       match an expected type of token and advances the lookahead.
       Throws exception of invalid type
       @param expectedType a string of the type expected to match the lookahead.
       @return a Token object that matches the expected type
    */
   private Token match(String expectedType)
   {
      if (!lookahead.getType().equals(expectedType))
      {
         throw new IllegalArgumentException(errorString(expectedType));
      }
      Token output = lookahead;
      nextToken();
      return output;
   }
   /**
       an errorString to print when an exception is found.
       @param expectedType a string of what type was expected
       @return a String of the error
    */
   private String errorString(String expectedType)
   {
      return lookahead.getSpelling() + " found, " + expectedType + " expected";
   }
   /**
       a main method used for testing
    */
   public static void main(String[] args)
   {
      Parser parser = new Parser();
      OrderedTree<Token> tree = parser.parse("let define append ( X Y ) if X cons ( car ( X ) append ( cdr ( X ) Y ) ) Y append ( [ `a ] [ `b ] )");
      System.out.println(tree);

   }
}