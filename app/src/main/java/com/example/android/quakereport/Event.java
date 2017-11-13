package com.example.android.quakereport;

/**
 * Created by kvaib on 13-11-2017.
 */

/**
 * {@Event} represents an earthquake event.
 */
public class Event {

    /** Title of the earthquake event */
    public final String title;

    /** Number of people who felt the earthquake and reported how strong it was */
    public final String numOfPeople;

    /** Perceived strength of the earthquake from the people's responses */
    public final String perceivedStrength;

    /**
     * Constructs a new {@link Event}.
     *
     * @param eventTitle is the title of the earthquake event
     * @param eventNumOfPeople is the number of people who felt the earthquake and reported how
     *                         strong it was
     * @param eventPerceivedStrength is the perceived strength of the earthquake from the responses
     */
    public Event(String eventTitle, String eventNumOfPeople, String eventPerceivedStrength) {
        title = eventTitle;
        numOfPeople = eventNumOfPeople;
        perceivedStrength = eventPerceivedStrength;
    }
}
