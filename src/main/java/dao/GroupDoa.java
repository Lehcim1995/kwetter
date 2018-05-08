package dao;

import classes.Group;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless //stateless
@JPA
public class GroupDoa
{

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager entityManager;

    public Group getGroup(String group)
    {

        try
        {
            Group p = entityManager.createQuery("SELECT g FROM GROUPS g WHERE g.groupName = :groupname", Group.class)
                                   .setParameter("groupname", group)
                                   .getSingleResult();

            return p;
        }
        catch (NoResultException e)
        {
            Group newGroup = new Group(group);

            entityManager.persist(newGroup);

            return newGroup;
        }


    }

}
