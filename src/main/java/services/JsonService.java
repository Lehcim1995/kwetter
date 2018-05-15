package services;

import json.JsonParser;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JsonService
{
    private JsonParser jsonParser;

    @PostConstruct
    public void init()
    {
        jsonParser = new JsonParser();
    }

    public <F> String parse(F object)
    {
        return jsonParser.createJsonString(object);
    }

    public JsonParser getJsonParser() {
        return jsonParser;
    }
}
