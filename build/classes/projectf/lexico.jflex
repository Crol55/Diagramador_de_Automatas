package projectf;
import java_cup.runtime.Symbol;
import java.util.ArrayList;

%%
%class AnalizadorLexico
%cupsym sym
%cup
%public
%unicode
%line
%char
%column 

%{
 public static ArrayList<String> ErroresLexicos=new ArrayList<String>();
%}
numeros   =[0-9]+
letras    =[a-zA-Z]+
decimal   =[0-9]+[.][0-9]+
rango =[!-~]
com=[\\][\"]
comS=[\\][']
%%
"error"      {System.out.println("lexico "+yytext()); return new Symbol(sym.eror,yyline,yychar,new String(yytext())); }
"[:todo:]"   {System.out.println("lexico "+yytext());return new Symbol(sym.todo,yyline,yychar,new String(yytext())); }
"yytext"     {System.out.println("lexico "+yytext());return new Symbol(sym.ytext,yyline,yychar,new String(yytext())); }
"yyrow"      {System.out.println("lexico "+yytext());return new Symbol(sym.yrow,yyline,yychar,new String(yytext())); }
"yycol"      {System.out.println("lexico "+yytext());return new Symbol(sym.ycol,yyline,yychar,new String(yytext())); }
"CONJ"       {System.out.println("lexico "+yytext());return new Symbol(sym.CONJ,yyline,yychar,new String(yytext())); }
"retorno"    {System.out.println("lexico "+yytext());return new Symbol(sym.retorno,yyline,yychar,new String(yytext())); }
"RESERV"     {System.out.println("lexico "+yytext());return new Symbol(sym.res,yyline,yychar,new String(yytext())); }
{com}      {System.out.println("lexico com "+yytext());return new Symbol(sym.com,yyline,yychar,new String(yytext())); }
"\\n"   {System.out.println("lexico salto "+yytext());return new Symbol(sym.saltodelinea,yyline,yychar,new String(yytext())); }
"\\t"   {System.out.println("lexico tabulacion "+yytext());return new Symbol(sym.tabulacion,yyline,yychar,new String(yytext())); }
"\""  {System.out.println("lexico comilla doble"+yytext()); return new Symbol(sym.comillasD,yychar,yyline,new String(yytext()));} 
{comS}    {System.out.println("lexico Comilla simple"+yytext());return new Symbol(sym.comillaS,yyline,yychar,new String(yytext()));}  
"."   {System.out.println("lexico concat "+yytext());return new Symbol(sym.concat,yychar,yyline,new String(yytext()));}
"+"   {System.out.println("lexico 1oMas "+yytext());return new Symbol(sym.unaomas,yyline,yychar,new String(yytext())); }
"?"   {System.out.println("lexico 0o1 "+yytext());return new Symbol(sym.ceroouna,yyline,yychar,new String(yytext())); }
"_"   {System.out.println("lexico  "+yytext());return new Symbol(sym.guionbajo,yyline,yychar,new String(yytext())); }
","   {System.out.println("lexico  "+yytext());return new Symbol(sym.coma,yyline,yychar,new String(yytext())); }
"->"  {System.out.println("lexico  "+yytext());return new Symbol(sym.seguido,yyline,yychar,new String(yytext())); } 
";"   {System.out.println("lexico  "+yytext());return new Symbol(sym.puntoycoma,yyline,yychar,new String(yytext())); }
"%%"  {System.out.println("lexico  "+yytext());return new Symbol(sym.iniciofin,yyline,yychar,new String(yytext())); }
"*"   {System.out.println("lexico 0oMas "+yytext());return new Symbol(sym.ceroomas,yyline,yychar,new String(yytext())); }
"|"    {System.out.println("lexico  "+yytext());return new Symbol(sym.orr,yyline,yychar,new String(yytext())); }
"~"   {System.out.println("lexico  "+yytext());return new Symbol(sym.hasta,yyline,yychar,new String(yytext())); }
":"   {System.out.println("lexico  "+yytext());return new Symbol(sym.dospuntos,yyline,yychar,new String(yytext())); }
"["   {System.out.println("lexico  "+yytext());return new Symbol(sym.corcheteAbierto,yyline,yychar,new String(yytext())); }
"]"   {System.out.println("lexico  "+yytext());return new Symbol(sym.corcheteCerrado,yyline,yychar,new String(yytext())); }
"("   {System.out.println("lexico "+yytext());return new Symbol(sym.parentesisA,yyline,yychar,new String(yytext())); } 
")"   {System.out.println("lexico  "+yytext());return new Symbol(sym.parentesisC,yyline,yychar,new String(yytext())); }
{rango}      {System.out.println("lexico rango "+yytext());return new Symbol(sym.rang,yyline,yychar,new String(yytext())); }
{numeros}    {System.out.println("lexico digit  "+yytext());return new Symbol(sym.num,yyline,yychar,new String(yytext())); }
{letras}    {System.out.println("lexico  "+yytext());return new Symbol(sym.identificador,yyline,yychar,new String(yytext()));} 
{decimal}    {System.out.println("lexico "+yytext());return new Symbol(sym.decimal,yyline,yychar,new String(yytext())); }


[ \t\r\f\n]+        { /* Se ignoran */}


.      { 
         ErroresLexicos.add(yytext()+" "+yyline+" "+yychar); 
         System.out.println("Error lexico: "+ErroresLexicos.get(0));
         
       }


