package classes;

public class Privileges {

	private boolean canDelete;
	private boolean canPost;
	private boolean canDeleteAll;
	private boolean canAssign;

    public Privileges(
            boolean canDelete,
            boolean canPost,
            boolean canDeleteAll,
            boolean canAssign)
    {
        this.canDelete = canDelete;
        this.canPost = canPost;
        this.canDeleteAll = canDeleteAll;
        this.canAssign = canAssign;
    }

    public boolean getCanDelete()
    {
        return canDelete;
    }

    public boolean getCanPost()
    {
        return canPost;
    }

    public boolean getCanDeleteAll()
    {
        return canDeleteAll;
    }

    public boolean getCanAssign()
    {
        return canAssign;
    }
}