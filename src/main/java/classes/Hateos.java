package classes;

import javax.ws.rs.core.Link;

public class Hateos
{
    protected Link self;
    protected Link delete;
    protected Link update;

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

    public Link getDelete() {
        return delete;
    }

    public void setDelete(Link delete) {
        this.delete = delete;
    }

    public Link getUpdate() {
        return update;
    }

    public void setUpdate(Link update) {
        this.update = update;
    }
}
