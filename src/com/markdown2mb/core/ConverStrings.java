package com.markdown2mb.core;

/**
 * Created with IntelliJ IDEA.
 * User: tools
 * Date: 04/01/14
 * Time: 21.04
 * To change this template use File | Settings | File Templates.
 */
public class ConverStrings {

    public String convertString(String text){

        text = replaceLinks(text);
        text = replaceCite(text);
        text = replaceCode(text);
        text = replaceBold(text);
        text = replaceItalic(text);


        return text;
    }

    public String replaceBold(String text){
        int startPos = 0;

        int j = 0;

        for(int i = 0; i < text.length()-1; i++){
          if((text.charAt(i) == '*' && text.charAt(i+1) == '*') ||
                  (text.charAt(i) == '_' && text.charAt(i+1) == '_')){
              String regEx = "";
              if(text.charAt(i) == '*'){
                  regEx = "\\*\\*";
              }else{
                  regEx = "__";
              }
              if(j == 0){
                  text = replaceFromIndexToIndexWith(text, regEx, "[b]");
                  j = 1;
              }else if(j == 1){
                  text = replaceFromIndexToIndexWith(text, regEx, "[/b]");
                  j = 0;
              }
          }
        }

        return text;
    }

    public String replaceItalic(String text){

        int j = 0;
        for(int i = 0; i < text.length()-1; i++){
            if((text.charAt(i) == '*' && text.charAt(i+1) != '*') ||
                    (text.charAt(i) == '_' && text.charAt(i+1) != '_')){
                String regEx = "";
                if(text.charAt(i) == '*'){
                    regEx = "\\*";
                }else{
                    regEx = "_";
                }
                if(j == 0){
                    text = replaceFromIndexToIndexWith(text, regEx, "[i]");
                    j = 1;
                }else if(j == 1){
                    text = replaceFromIndexToIndexWith(text, regEx, "[/i]");
                    j = 0;
                }

            }
        }

        return text;
    }


    private String replaceFromIndexToIndexWith(String text, String reg_ex, String toReplaceWith){
        return text.replaceFirst(reg_ex, toReplaceWith);
    }

    private String replaceLinks(String text){

        /*
            MARKDOWN: [This link](http://example.net/)
            MACBAY: [link=http://www.google.com]www.google.com[/link]
        */
        String[] strings = text.split("\\s");
        for(int i = 0; i < strings.length; i++){
            if(strings[i].matches("\\[.*?\\]\\(.*?\\)")){
                System.out.println("John");
            }
        }


        return text;
    }

    private String replaceCite(String text){

        String[] strings = text.split("\n");
        text = "";
        for(int i = 0; i < strings.length; i++){
            if(strings[i].startsWith(">")){
                strings[i] = "[citat]" + strings[i].substring(1, strings[i].length()) + "[/citat]";
                System.out.println(strings[i]);
            }
            text += strings[i] + "\n";
        }
        return text;
    }

    //w white spaces

    private String replaceCode(String text){
        String[] strings = text.split("\n");
        text = "";
        boolean code_started = false;

        for(int i = 0; i < strings.length; i++){
            //strings[i].startsWith("\\s\\s\\s\\s")

            if(strings[i].startsWith("    ")){
                System.out.println("JJ: " + code_started);
                if(code_started == true){
                    if(i == strings.length - 1 || !strings[i + 1].startsWith("    ") ){
                        strings[i] = strings[i] + "\n[/code]";
                        code_started = false;
                    }
                }else{

                    strings[i] = "[code]\n" + strings[i];
                    code_started = true;
                    if(i < strings.length - 1 && !strings[i + 1].startsWith("    ")){
                        strings[i] = strings[i] + "\n[/code]";
                        code_started = false;
                    }
                }
            }

            text += strings[i] + "\n";
        }

        return text;
    }


}
