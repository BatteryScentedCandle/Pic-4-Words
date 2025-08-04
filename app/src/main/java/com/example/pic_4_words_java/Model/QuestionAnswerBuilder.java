package com.example.pic_4_words_java.Model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QuestionAnswerBuilder {


    //helper functions
    public static <K, V> void addToMap(Map<K, List<V>> source, K key, V... values){
        List<V> imageList = source.get(key);
        if(imageList == null){
            imageList = new ArrayList<>();
            source.put(key, imageList);
        }
        imageList.addAll(Arrays.asList(values));
    }










    //code for population
    public static QuestionAnswerModel easyBrainrotQA(){

        // Reset the model and source images for a fresh start
        QuestionAnswerModel qaModel = new QuestionAnswerModel();
        LinkedHashMap<String, List<String>> sourceImages = new LinkedHashMap<>();

        //answers
        qaModel.setAnswer(Arrays.asList(
                "Tung Tung Tung Sahur",
                "Sus",
                "Grimace"
                )
        );



        //images
        addToMap(sourceImages,
                "ebQ1",
                "https://i.imgur.com/QDwm0TK.jpeg",
                "https://i.imgur.com/SaKRwon.jpeg",
                "https://i.imgur.com/7BK86Eu.png",
                "https://i.imgur.com/QrqdTSB.jpeg");

        addToMap(sourceImages,
                "ebQ2",
                "https://i.imgur.com/5imhCMs.jpeg",
                "https://i.imgur.com/w6wdCz9.png",
                "",
                "");

        addToMap(sourceImages,
                "ebQ3",
                "https://i.imgur.com/Nd66UdA.jpeg",
                "https://i.imgur.com/B30vAyg.jpeg",
                "https://i.imgur.com/qXbjXkJ.jpeg",
                "");
        qaModel.setImages(sourceImages);



        return qaModel;
    }

    public static QuestionAnswerModel hardBrainrot(){

        QuestionAnswerModel qaModel = new QuestionAnswerModel();
        LinkedHashMap<String, List<String>> sourceImages = new LinkedHashMap<>();

        qaModel.setAnswer(Arrays.asList(
                        "Sigma",
                        "Crashout",
                        "Mukbang"
                )
        );




        addToMap(sourceImages,
                "hbQ1",
                "https://i.imgur.com/ngcAxu0.jpeg",
                "https://i.imgur.com/ptoZqL0.jpeg",
                "https://i.imgur.com/aDr7GF0.jpeg",
                "");

        addToMap(sourceImages,
                "hbQ2",
                "https://i.imgur.com/WUzXtfF.jpeg",
                "https://i.imgur.com/M9tBDXP.jpeg",
                "https://i.imgur.com/oipSzGB.jpeg",
                "");

        addToMap(sourceImages,
                "hbQ3",
                "https://i.imgur.com/IM2ptmw.jpeg",
                "https://i.imgur.com/Snbu7oQ.jpeg",
                "https://i.imgur.com/egoBmKK.jpeg",
                "");
        qaModel.setImages(sourceImages);



        return qaModel;
    }

    public static QuestionAnswerModel easySuperhero(){

        QuestionAnswerModel qaModel = new QuestionAnswerModel();
        LinkedHashMap<String, List<String>> sourceImages = new LinkedHashMap<>();

        qaModel.setAnswer(Arrays.asList(
                        "Batman",
                        "Spider-Man",
                        "Thor"
                )
        );




        addToMap(sourceImages,
                "esQ1",
                "https://i.imgur.com/JJS0gFv.jpeg",
                "https://i.imgur.com/pbWu92c.jpeg",
                "",
                "");

        addToMap(sourceImages,
                "esQ2",
                "https://i.imgur.com/dtvXILx.jpeg",
                "https://i.imgur.com/TgqIfsH.jpeg",
                "https://i.imgur.com/dnXwJW7.jpeg",
                "");

        addToMap(sourceImages,
                "esQ3",
                "https://i.imgur.com/PmwvNFa.jpeg",
                "https://i.imgur.com/7HldHuZ.jpeg",
                "https://i.imgur.com/9WxETQY.jpeg",
                "https://i.imgur.com/ycM0Qqm.jpeg" );
        qaModel.setImages(sourceImages);



        return qaModel;
    }
    public static QuestionAnswerModel hardSuperhero(){

        QuestionAnswerModel qaModel = new QuestionAnswerModel();
        LinkedHashMap<String, List<String>> sourceImages = new LinkedHashMap<>();

        qaModel.setAnswer(Arrays.asList(
                        "Deadpool",
                        "Shazam",
                        "Daredevil"
                )
        );




        addToMap(sourceImages,
                "hsQ1",
                "https://i.imgur.com/CrtAR4i.jpeg",
                "https://i.imgur.com/OAjzPa3.jpeg",
                "",
                "" );

        addToMap(sourceImages,
                "hsQ2",
                "https://i.imgur.com/VhfKji4.jpeg",
                "https://i.imgur.com/PUJDSXu.jpeg",
                "https://i.imgur.com/4W6mIUk.png",
                "https://i.imgur.com/JTMCuiX.jpeg"
                );

        addToMap(sourceImages,
                "hsQ3",
                "https://i.imgur.com/OVvd8ai.jpeg",
                "https://i.imgur.com/uPyXma7.gif",
                "https://i.imgur.com/UmGMJVI.jpeg",
                "" );
        qaModel.setImages(sourceImages);



        return qaModel;
    }
}
