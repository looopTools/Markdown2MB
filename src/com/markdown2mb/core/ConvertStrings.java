package com.markdown2mb.core;

/**
 * Created with IntelliJ IDEA.
 * User: tools
 * Date: 04/01/14
 * Time: 21.04
 * To change this template use File | Settings | File Templates.
 */
public class ConvertStrings {

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
                    index_of_start = j;
                }else if(strings[i].charAt(j) == ']' && index_of_start != -1){
                    index_of_end = j;
                }else if(strings[i].charAt(j) == '(' && index_of_end == j - 1 &&  index_of_end != -1){
                    index_of_s_link = j;
                }else if(strings[i].charAt(j) == '(' && strings[i].charAt(j-1) != ']'){
                    index_of_start = -1;
                    index_of_end = -1;
                }else if(strings[i].charAt(j) == ')' && index_of_s_link != -1){
                    index_of_e_link = j;
                }

                        /*
                            MARKDOWN: [This link](http://example.net/)
                            MACBAY: [link=http://www.google.com]www.google.com[/link]
                        */
                if(index_of_start != -1 && index_of_end != -1 && index_of_s_link != -1 && index_of_e_link != -1){
                    String link_text = strings[i].substring(index_of_start+1, index_of_end);
                    String link_url = strings[i].substring(index_of_s_link+1, index_of_e_link);
                    String macbay_link = "[link=" + link_url + "]" + link_text + "[/link]";
                    strings[i]= strings[i].replace(strings[i].substring(index_of_start, index_of_e_link +1), macbay_link);
                    index_of_start = -1; index_of_end = -1; index_of_s_link = -1; index_of_e_link = -1;
                }
            }
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
            }
            text += strings[i] + "\n";
        }
        return text;
    }

    private String replaceCode(String text){
        boolean isCurrentLineInCodeBlock = false;
        String[] strings = text.split("\n");
        text = "";

        for(int i = 0; i < strings.length; i++){
            if(strings[i].startsWith("    ") || strings[i].startsWith("\t")){
                if(!isCurrentLineInCodeBlock){
                    text += "[code]\n";
                    System.out.println("i: " + i);
                    isCurrentLineInCodeBlock = true;
                }else{
                    System.out.println("j: " + i);
                }
            }else{
                if(isCurrentLineInCodeBlock){
                    //This means the previous line was last end codeblock
                    text += "[/code]\n";
                    isCurrentLineInCodeBlock = false;
                }
            }
            text += strings[i] + "\n";
            if (i == strings.length - 1 && isCurrentLineInCodeBlock){
                text += "[/code]\n";
            }
        }

        return text;
    }




}
