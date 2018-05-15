package bean;

import classes.Group;
import classes.User;
import exceptions.CouldNotCreateUser;
import exceptions.IdAlreadyExistsException;
import exceptions.UserNotFoundException;
import services.KwetterService;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;
import javax.inject.Inject;

@Singleton
//@Startup
public class StartUpBean
{
    @Inject
    KwetterService kwetterService;


    // Using a timer because postconstruct with startup doesnt work :/
    @Schedule(hour = "*", minute = "*", persistent = false)
    protected void init(Timer timer)
    {
        try
        {
            for(int i = 0; i < 15; i++)
            {

                User u = kwetterService.createUser("Jan_" + i, "password", Group.USER_GROUP);

                kwetterService.addKweet("message1", u);
                kwetterService.addKweet("message2", u);
                kwetterService.addKweet("message3", u);
                kwetterService.addKweet("message4", u);
                kwetterService.addKweet("message5", u);
            }

            System.out.println("Finished adding users and messages");
        }
        catch (IdAlreadyExistsException e)
        {
            e.printStackTrace();
        }
        catch (CouldNotCreateUser couldNotCreateUser)
        {
            couldNotCreateUser.printStackTrace();
        }
        catch (UserNotFoundException e)
        {
            e.printStackTrace();
        }

        timer.cancel();
    }

//    @PostConstruct
//    public void init()
//    {
//        try
//        {
//            User u = kwetterService.createUser("Jan", "password");
//
//            kwetterService.addKweet("message1", u);
//            kwetterService.addKweet("message2", u);
//            kwetterService.addKweet("message3", u);
//            kwetterService.addKweet("message4", u);
//            kwetterService.addKweet("message5", u);
//
//            System.out.println("Finished adding users and messages");
//        }
//        catch (IdAlreadyExistsException e)
//        {
//            e.printStackTrace();
//        }
//        catch (CouldNotCreateUser couldNotCreateUser)
//        {
//            couldNotCreateUser.printStackTrace();
//        }
//        catch (UserNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        catch (Exception e)
//        {
//            System.out.println("General exception");
//            e.printStackTrace();
//        }
//    }
}
