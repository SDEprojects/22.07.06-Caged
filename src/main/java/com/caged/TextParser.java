package com.caged;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

class TextParser {



    public String[] textParser(String text){
        text = text.replaceAll("(?i)[^a-z]"," ");
        String sentence[] = text.split(" ");
        String verb = sentence[0].toLowerCase();
        String noun = sentence[sentence.length-1].toLowerCase();
        //String input = "grab";
        YAMLReader yamlReader = new YAMLReader();
        List move = yamlReader.dictionaryLoader().getMove();
        List take = yamlReader.dictionaryLoader().getTake();
        List look = yamlReader.dictionaryLoader().getLook();
        List help = yamlReader.dictionaryLoader().getHelp();
        List quit = yamlReader.dictionaryLoader().getQuit();
        List talk = yamlReader.dictionaryLoader().getTalk();
        if (move.contains(verb)){
            verb = "move";
        }
        else if (take.contains(verb)){
            verb = "take";
        }
        else if (look.contains(verb)){
            verb = "look";
        }
        else if (help.contains(verb)){
            verb = "help";
        }
        else if (quit.contains(verb)){
            verb = "quit";
        }
        else if (talk.contains(verb)){
            verb = "talk";
        }
        else {
            System.out.println("Unrecognized Command, type help for valid command");
        }
        String sentenceArray[] = {verb, noun};
        return sentenceArray;
    }

}
