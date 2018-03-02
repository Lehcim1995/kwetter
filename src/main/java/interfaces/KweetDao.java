package interfaces;

import classes.Kweet;
import java.util.List;

public interface KweetDao {

	List<Kweet> getKweets();

	/**
	 * 
	 * @param username
	 */
	List<Kweet> getKweetsFromUser(String username);

	/**
	 * 
	 * @param username
	 * @param amount
	 */
	List<Kweet> getKweetsFromUser(String username, int amount);

	/**
	 * 
	 * @param mention
	 */
	List<Kweet> getKweetsFromMention(String mention);

	/**
	 * 
	 * @param mention
	 * @param amount
	 */
	List<Kweet> getKweetsFromMention(String mention, int amount);

	/**
	 * 
	 * @param trend
	 */
	List<Kweet> getKweetsFromTrend(String trend);

	/**
	 * 
	 * @param trend
	 * @param amount
	 */
	List<Kweet> getKweetsFromTrend(String trend, int amount);

	/**
	 *
     * @param message
     */
	Kweet addKweet(String message);

	/**
	 * 
	 * @param id
	 */
	boolean deleteKweet(long id);

	/**
	 * 
	 * @param userName
	 */
	List<Kweet> getKweetsForUserProfile(String userName);

	/**
	 * 
	 * @param sql
	 */
	List<Kweet> getKweetsWithSQL(String sql);

	/**
	 * 
	 * @param id
	 */
	Kweet getKweet(long id);

	List<String> getMentions();

	List<String> getTends();

}