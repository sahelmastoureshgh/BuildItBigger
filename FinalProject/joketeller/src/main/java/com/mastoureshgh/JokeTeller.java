package com.mastoureshgh;

import java.util.Random;

public class JokeTeller {
    Random ran = new Random();
    public String getJoke(){
        String[] someJokes = { "People who say they sleep like a baby haven't got one.",
                "Three kinds of people:  those who can count and those who can't.",
                "Ignorance is no excuse.  Almost invariably it's the real thing."
        };
        return someJokes[ran.nextInt(3)];
    }
}
