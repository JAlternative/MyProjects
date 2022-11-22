package main;
public class EntityNotFoundException extends RuntimeException
{
    public EntityNotFoundException()
    {

    }

    public EntityNotFoundException(String description)
    {
        super(description);
    }

}