package utils;

import game.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created on: 22-Jun-19, 22:08
 *
 * @author: Katy Bucșa
 */

public class GPlayer {
    private Player player;
    private String word;
    private String codedWord;
    private String literePropuse;
    private Map<String, List<String>> cuv_listLit;
    private int nrPoints;

    public GPlayer(Player player, String word) {
        this.player = player;
        this.word = word;
        this.codedWord = "";
        this.literePropuse = "";
        this.cuv_listLit = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            if ("aeiou".contains("" + word.charAt(i)))
                codedWord += "V";
            else
                codedWord += "C";
        }
        nrPoints = 0;
    }

    public void addTryLetter(String letter) {
        this.literePropuse += letter + ",";
    }

    public void addCuvToList(String cuv) {
        this.cuv_listLit.put(cuv, new ArrayList<>());
    }

    public void appendToListLit(String cuv, String letter) {
        List<String> l = this.cuv_listLit.get(cuv);
        l.add(letter);
        this.cuv_listLit.put(cuv, l);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCodedWord() {
        return codedWord;
    }

    public void setCodedWord(String codedWord) {
        this.codedWord = codedWord;
    }

    public Map<String, List<String>> getCuv_listLit() {
        return cuv_listLit;
    }

    public void setCuv_listLit(Map<String, List<String>> cuv_listLit) {
        this.cuv_listLit = cuv_listLit;
    }

    public int getNrPoints() {
        return nrPoints;
    }

    public void setNrPoints(int nrPoints) {
        this.nrPoints = nrPoints;
    }

    public String getLiterePropuse() {
        return literePropuse;
    }

    public void setLiterePropuse(String literePropuse) {
        this.literePropuse = literePropuse;
    }
}
