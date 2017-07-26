package kz.djunglestones.speaky;

import android.media.Image;

/**
 * Created by yelaman on 22.07.17.
 */

public class Words {
    private int id;
    private String word="";
    private int image;

//    public Words(int id, String word) {
//        this.id = id;
//        this.word = word;
//    }
//
//    public Words(){
//    }
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getWord() {
//        return word;
//    }
//
//    public void setWord(String word) {
//        this.word = word;
//    }


    public Words(int id, String word, int image) {
        this.id = id;
        this.word = word;
        this.image = image;
    }

    public Words(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
