package exceptions;

public class IdAlreadyExistsException extends Exception
{
    public IdAlreadyExistsException(String message)
    {
        super(message);
    }
}
