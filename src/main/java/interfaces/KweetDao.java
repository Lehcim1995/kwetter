package interfaces;

import classes.Kweet;
import classes.User;
import exceptions.KweetNotFoundException;

import java.util.List;

public interface KweetDao {

    String MENTION_TOKEN = "@";
    String TREND_TOKEN = "#";

    /**
     *  Returns all kweets sorted by date
     *
     * @return all kweets in the Dao
     */
	List<Kweet> getKweets();

    /**
     *  Returns all kweets sorted by date
     *
     * @param limit the limit on kweets
     * @return all kweets in the Dao
     */
    List<Kweet> getKweets(int limit);

	/**
	 * Get all kweets from a user sorted by date
     *
	 * @param username User's username
	 */
	List<Kweet> getKweetsFromUser(String username);

	/**
	 *  Get all kweets from a user limited by size, and sorted by date
     *
	 * @param username
	 * @param amount Amount of kweets you want
	 */
	List<Kweet> getKweetsFromUser(String username, int amount);

	/**
	 * Get all kweets with a certain mentions
     *
	 * @param mention the mention
	 */
	List<Kweet> getKweetsFromMention(String mention);

	/**
	 * Get all kweets with a certain mentions limited by size
     *
	 * @param mention The mention in the tweet
	 * @param amount Amount of kweets
	 */
	List<Kweet> getKweetsFromMention(String mention, int amount);

	/**
	 *
     *  Get all kweets with a trend
     *
	 * @param trend The trend
	 */
	List<Kweet> getKweetsFromTrend(String trend);

	/**
	 *  Get all kweets with a trend limited by size
     *
	 * @param trend The trend
	 * @param amount Amount of kweets
	 */
	List<Kweet> getKweetsFromTrend(String trend, int amount);

    /**
     * Creates a new kweet
     * @param message the message for the kweet
     * @param user the user for the kweet
     */
    Kweet addKweet(String message, User user);

    /**
	 * Deletes a kweet by id
	 * @param id Kweet id
	 */
	boolean deleteKweet(long id) throws KweetNotFoundException;

	/**
	 * Gets the kweets nessasery for user profile
     *
	 * @param userName
	 */
	List<Kweet> getKweetsForUserProfile(String userName);

	/**
	 * returns a list with kweets selected with a sql selector
     *
	 * @param sql
	 */
	List<Kweet> getKweetsWithSQL(String sql);

	/**
	 *  Gets a kweet by id
     *
	 * @param id
	 */
	Kweet getKweet(long id) throws KweetNotFoundException;


    /**
     * Get all mentiond trends
     *
     * @return
     */
	List<String> getTends();

    /**
     * Get all mentiond trends limited by limit
     *
     * @param limit the limit
     * @return
     */
    List<String> getTends(int limit);

    /**
     * Gets all kweets with the search query
     *
     * @param search the sting that is being searched
     * @return a list with the kweets with this string
     */
    List<Kweet> searchKweets(String search);

}