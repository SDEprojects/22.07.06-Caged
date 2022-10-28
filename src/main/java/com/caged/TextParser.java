package com.caged;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

class TextParser {



    public String[] textParser(String text){
        try {
            text = text.replaceAll("(?i)[^a-z]"," ");
            String[] sentence = text.split(" ");
            String verb = sentence[0].toLowerCase();
            String noun = sentence[sentence.length-1].toLowerCase();
            String nounPrefix;
            if (sentence.length>1){
                nounPrefix = sentence[sentence.length-2].toLowerCase();
            }
            else {
                nounPrefix = sentence[sentence.length-1].toLowerCase();
            }
            YAMLReader yamlReader = new YAMLReader();
            List move = yamlReader.dictionaryLoader().getMove();
            List take = yamlReader.dictionaryLoader().getTake();
            List look = yamlReader.dictionaryLoader().getLook();
            List help = yamlReader.dictionaryLoader().getHelp();
            List map = yamlReader.dictionaryLoader().getMap();
            List quit = yamlReader.dictionaryLoader().getQuit();
            List use = yamlReader.dictionaryLoader().getUse();
            List talk = yamlReader.dictionaryLoader().getTalk();
            List inventory = yamlReader.dictionaryLoader().getInventory();
            List drop = yamlReader.dictionaryLoader().getDrop();
            List open = yamlReader.dictionaryLoader().getOpen();
            List attack = yamlReader.dictionaryLoader().getAttack();
            List play = yamlReader.dictionaryLoader().getPlay();
            List stop = yamlReader.dictionaryLoader().getStop();
            List equip = yamlReader.dictionaryLoader().getEquip();

            if (move.contains(verb)){
                verb = "move";
            }
            else if (take.contains(verb)){
                verb = "take";
            }
            else if (look.contains(verb)){
                verb = "look";
            }
            else if (use.contains(verb)) { //new
                verb = "use";
            }
            else if (help.contains(verb)){
                verb = "help";
            }
            else if (map.contains(verb)){
                verb = "map";
            }
            else if (quit.contains(verb)){
                verb = "quit";
            }
            else if (talk.contains(verb)){
                verb = "talk";
            }
            else if (inventory.contains(verb)){
                verb = "inventory";
            }
            else if (drop.contains(verb)){
                verb = "drop";
            }
            else if (open.contains(verb)){
                verb = "open";
            }
            else if (attack.contains(verb)){
                verb = "attack";
            }
            else if (play.contains(verb)){
                verb = "play";
            }
            else if (stop.contains(verb)){
                verb = "stop";
            }
            else if (equip.contains(verb)){
                verb = "equip";
            }
            else {
                System.out.println("Unrecognized Command, type help for valid command");
            }
            return new String[]{verb, noun, nounPrefix};
        } catch (Exception e) {
            System.out.println("That doesn't make sense, here is some help...");
            return new String[] {"help", "me", "please"};
        }
    }

}
