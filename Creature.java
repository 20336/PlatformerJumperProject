import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Creature here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Creature extends Enemy
{
    private int dX = 1;
    private int dY = 2;
    private int acceleration = 1;
    private int startX;
    
    public Creature(int startX){
        this.startX = startX;
    }
    
    /**
     * Act - do whatever the Creature wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        checkForFall();
        move();
        moveSidetoSide();
    }
    
    /**
     * Moves the creature a certain amount in the x direction.
     */
    public void move(){
        setLocation(getX()+dX, getY());
    }
    
    /**
     * 
     */
    public void moveSidetoSide(){
        if(getX() >= startX + 20){
            dX = -1;
        }else if(getX() <= startX - 20){
            dX = 1;
        }
    }
    
    /**
     * Makes the emeny fall if not on platform.
     */
    public void fall(){
        detectPlatform();
        setLocation(getX(), getY()+dY);
        dY += acceleration;
    }
    
    /**
     * Checks if the emeny is on the ground or playform, if not then it will fall.
     */
    public void checkForFall(){
        if(isOnGround() || isOnPlatform()){
            dY = 0;
        }else{  
            fall();
        }
    }
    
    /**
     * Detects if the creature is on the platform.
     */
    public boolean isOnPlatform(){
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
        return under != null;
    }

    /**
     * Detects if the creature is on the ground.
     */
    public boolean isOnGround(){
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Ground.class);
        return under != null;
    }
    
    /**
     * Detects how far the platform is from the bottom of the player.
     */
    public void detectPlatform(){
        for(int i = 0; i < dY; i++){
            Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2+i, Platform.class);
            if(under != null){
                dY = i;
            }
        }
    }
}
