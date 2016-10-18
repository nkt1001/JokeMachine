package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Nkt1001 on 17.10.2016.
 */

public class JokeBase {
    private List<Joke> mJokes = Arrays.asList(
            new Joke("Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all."),
            new Joke("Doctor: \"I'm sorry but you suffer from a terminal illness and have only 10 to live.\"\n" +
                    "\n" +
                    "Patient: \"What do you mean, 10? 10 what? Months? Weeks?!\"\n" +
                    "\n" +
                    "Doctor: \"Nine.\""),
            new Joke("A man asks a farmer near a field, “Sorry sir, would you mind if I crossed your field instead of going around it? You see, I have to catch the 4:23 train.”\n" +
                    "\n" +
                    "The farmer says, “Sure, go right ahead. And if my bull sees you, you’ll even catch the 4:11 one.” "),
            new Joke("Anton, do you think I’m a bad mother?\n" +
                    "\n" +
                    "My name is Paul."),
            new Joke("My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away."),
            new Joke("What is the difference between a snowman and a snowwoman?\n" +
                    "-\n" +
                    "Snowballs."),
            new Joke("Mother, “How was school today, Patrick?”\n" +
                    "\n" +
                    "Patrick, “It was really great mum! Today we made explosives!”\n" +
                    "\n" +
                    "Mother, “Ooh, they do very fancy stuff with you these days. And what will you do at school tomorrow?”\n" +
                    "\n" +
                    "Patrick, “What school?”"),
            new Joke("\"Mom, where do tampons go?\"\n" +
                    "\n" +
                    "\"Where the babies come from, darling.\"\n" +
                    "\n" +
                    "\"In a stork???!!!\""),
            new Joke("Chuck Norris doesn't pet any animals. Animals pet themselves when he approaches them."),
            new Joke("Chuck Norris threw a hand grenade and killed 50 people. Then the grenade exploded."),
            new Joke("When Chuck Norris enters the room, even the chairs are standing up."),
            new Joke("Chuck Norris doesn't need to flush the toilet. He simply goes \"Boo!\" and anything in the bowl promptly rushes away."),
            new Joke("Chuck Norris ordered a Big Mac at a Burger King. He got it without a single remark."),
            new Joke("Chuck Norris doesn't ever call the wrong number. You just answer the wrong phone."),
            new Joke("Chuck Norris tried to lose weight. But Chuck Norris NEVER loses."),
            new Joke("Chuck Norris’ cowboy boots are made of real cowboys!"),
            new Joke("Chuck Norris once had an arm-wrestling competition with Superman. The bet was that the loser has to then wear his underwear on top of his trousers."),
            new Joke("When Chuck Norris walks across the meadow, he doesn’t smell the flowers. The flowers smell him."),
            new Joke("Algorithm. Word used by programmers when they don't want to explain what they did."),
            new Joke("The 21st century: Deleting history is more important than making it."),
            new Joke("A system administrator has 2 problems: \n- dumb users \n- smart users"),
            new Joke("Redneck at the doctor: “Doc, I think I’m in trouble, I swallowed an ice cube 3 days ago and it ain’t come out yet.”"),
            new Joke("What should you put on the tomb stone of a mathematician?\n" +
                    "-\n" +
                    "He didn't count with this..."),
            new Joke("Q. What’s the worst thing about being lonely?\n" +
                    "\n" +
                    "A. Playing Frisbee."),
            new Joke("After many years of studying at a university, I’ve finally become a PhD… or Pizza Hut Deliveryman as people call it.")) ;


    public Joke getNextJoke() {

        return mJokes.get(new Random().nextInt(mJokes.size()));
    }
}
