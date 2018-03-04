package services;

import interfaces.KweetDao;

import javax.inject.Inject;

public class KweetService
{
    private KweetDao kweetDao;

    @Inject
    public void setKweetDao(KweetDao kweetDao)
    {
        this.kweetDao = kweetDao;
    }
}
