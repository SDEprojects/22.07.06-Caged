package com.caged;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameControl<K, V> {

    private boolean userInput = false;
    private final Scanner scanner = new Scanner(System.in);
    private boolean playGame = true;
    Scanner in = new Scanner(System.in);
    TextParser textParser = new TextParser();
    YAMLReader yamlReader = new YAMLReader();
    SplashScreen splashScreen = new SplashScreen();
    MainMenu mainMenu = new MainMenu();
    Console console = new Console();
    YAMLMapper mapper = new YAMLMapper();
    GameMap playerMap1 = new GameMap();
    GameMap playerMap2 = new GameMap();
    MusicPlayer music = new MusicPlayer();
    String lastAction = "";


    public void runGame() {
        console.clear();
        splashScreen.splash();
        HitEnter.enter();
        console.clear();
        mainMenu.mainMenu();
        mainMenuOptions();
        console.clear();
        yamlReader.objective();
        HitEnter.enter();
        playerMap1.build();
        playerMap2.build();
        music.play();
        playGame(yamlReader.playerLoader(), yamlReader.locationLoader(), yamlReader.doorLoader());
    }

    private void playGame(Player player, LocationGetter location, List<Doors> doors) {
        while (player.isPlayGame()) {
            console.clear();
            PlayerStatus.currentStatus(player);
            JsonNode node = mapper.valueToTree(location);
            String playerLocation = player.getCurrentLocation();
            if (node.get("room").get(playerLocation).get("Phase").intValue()==1){
                playerMap1.positionUpdate(player, location);
            }
            else {
                playerMap2.positionUpdate(player, location);
            }
            System.out.println("\nThings seen in room: ");
            KeyValueParser.key(node.get("room").get(playerLocation).get("Inventory"));
            System.out.println("\nPeople seen in room: ");
            KeyValueParser.key(node.get("room").get(playerLocation).get("NPCs"));
            System.out.println("\nDirections you can move: ");
            KeyValueParser.locationKeyValue(node.get("room").get(playerLocation).get("Moves"), player, doors);
            System.out.println("\nLast action taken: "+player.getLastAction().get(player.getLastAction().size()-1));
            System.out.print(">>>>");
            String userChoice = in.nextLine();
            String lowUser = userChoice.toLowerCase();
            String[] action = textParser.textParser(lowUser);
            player.playerActions(action[0], action[1], action[2], location, doors, playerMap1, playerMap2, music);
        }
        System.out.println("Congrats you made it to the " + player.getCurrentLocation() + "!");
        System.out.println("");
        quitConfirm();
        HitEnter.enter();
        runGame();
    }

    public void mainMenuOptions() {
        while (!userInput) {
            System.out.print("\n>>>> ");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("new game")) {
                yamlReader.introLoader();
                System.out.println("\n\u001b[36mHit enter to start....\u001b[0m");
                String enter = scanner.nextLine().toLowerCase();
                userInput = true;
            } else if (input.equals("quit")) {
                quitConfirm();
            }
            else {
                System.out.println("Invalid input, please enter valid input");
            }
        }
    }

    public void quitConfirm(){
        System.out.println("\u001b[30m\u001b[41mDo you really want to quit?\u001b[0m");
        String confirm = scanner.nextLine().toLowerCase();
        if (confirm.equals("yes")){
            System.exit(0);
        }
        else {
            System.out.println("Didn't say yes...Still caged...");
        }
    }
}