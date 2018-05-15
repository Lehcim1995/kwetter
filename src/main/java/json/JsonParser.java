package json;

import classes.Group;
import classes.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// https://stackoverflow.com/questions/4802887/gson-how-to-exclude-specific-fields-from-serialization-without-annotations
public class JsonParser
{
    public static void main(String[] args) {
        User u = new User("user", new Group("lol"));
        String s = new JsonParser().createJsonString(u);

        System.out.println(s);
    }

    private Gson gson;

    public JsonParser() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        gson = builder.addSerializationExclusionStrategy(new AnnotationExclusionStrategy()).create();
    }

    public <F> String createJsonString(F o)
    {
        return gson.toJson(o);
    }
}
