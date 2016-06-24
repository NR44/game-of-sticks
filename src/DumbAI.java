import java.util.Random;

/**
 * Created by Nigel on 6/22/16.
 */
public class DumbAI extends AbstractPlayer{
    private int choose;

    public DumbAI(String name){
        super (name);
    }

    @Override
    public int makeChoice(){
        int num;
        Random randomNumber = new Random();
        num = randomNumber.nextInt(3)+1;
        System.out.format("%s chose %d.\n", getName(), num);
        return num;
    }
}
