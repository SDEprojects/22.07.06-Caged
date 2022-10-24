package com.caged;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.*;

class YAMLReader {

    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    FileGetter fileGetter = new FileGetter();


    public Player playerLoader(){
        try {
            Player player = mapper.readValue(fileGetter.yamlGetter("player.yml"), Player.class);
            return player;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DictionaryGetter dictionaryLoader(){
        try {
            DictionaryGetter dictionary =  mapper.readValue(fileGetter.yamlGetter("dictionary.yml"), DictionaryGetter.class);
            return dictionary;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CharacterNPCGetter characterNPCLoader(){
        try {
            CharacterNPCGetter CharacterNPC =  mapper.readValue(fileGetter.yamlGetter("CharacterNPC.yml"), CharacterNPCGetter.class);
            return CharacterNPC;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ItemGetter itemLoader(){
        try {
            ItemGetter item =  mapper.readValue(fileGetter.yamlGetter("Items.yml"), ItemGetter.class);
            return item;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LocationGetter locationLoader(){
        try {
            LocationGetter location =  mapper.readValue(fileGetter.yamlGetter("location.yml"), LocationGetter.class);
            return location;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void introLoader() {
        try {
            Information info = mapper.readValue(fileGetter.yamlGetter("Game_info.yml"), Information.class);
            String result = info.getStory().get(0);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}