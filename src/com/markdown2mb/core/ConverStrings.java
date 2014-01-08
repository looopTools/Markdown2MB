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
        String[] strings = text.split("\n");
        text = "";
        for(int i = 0; i < strings.length; i++){
            //strings[i].contains("\\\\[.*?\\\\]\\\\(.*?\\\\)")
            int index_of_start = -1, index_of_end = -1, index_of_s_link = -1, index_of_e_link = -1;

            for(int j = 0; j < strings[i].length(); j++){

                if (strings[i].charAt(j) == '['){
                    System.out.println("#1");
                    index_of_start = j;
                }else if(strings[i].charAt(j) == ']' && index_of_start != -1){
                    System.out.println("#2");
                    index_of_end = j;
                }else if(strings[i].charAt(j) == '(' && index_of_end == j - 1 &&  index_of_end != -1){
                    System.out.println("#3");
                    index_of_s_link = j;
                }else if(strings[i].charAt(j) == '(' && strings[i].charAt(j-1) != ']'){
                    System.out.println("#5");
                    index_of_start = -1;
                    index_of_end = -1;
                }else if(strings[i].charAt(j) == ')' && index_of_s_link != -1){
                    System.out.println("#4");
                    index_of_e_link = j;
                }

                        /*
                            MARKDOWN: [This link](http://example.net/)
                            MACBAY: [link=http://www.google.com]www.google.com[/link]
                        */
                if(index_of_start != -1 && index_of_end != -1 && index_of_s_link != -1 && index_of_e_link != -1){
                    String link_text = strings[i].substring(index_of_start+1, index_of_end);
                    String link_url = strings[i].substring(index_of_s_link+1, index_of_e_link);
                    String macbay_link = "[link=" + link_url + "](" + link_text + "[/link]";
                    /*System.out.println(link_text);
                    System.out.println(link_url);
                    System.out.println(strings[i].substring(index_of_start, index_of_e_link +1));*/
                    System.out.println(macbay_link);
                    System.out.println(strings[i].substring(index_of_start, index_of_e_link +1));
                    strings[i]= strings[i].replace(strings[i].substring(index_of_start, index_of_e_link +1), macbay_link);
                    index_of_start = -1; index_of_end = -1; index_of_s_link = -1; index_of_e_link = -1;
                }
            }
            System.out.println(strings[i]);
            text += strings[i] + "\n";

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
