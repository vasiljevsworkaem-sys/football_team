public class aizsargs extends speletajs {
    boolean block;

    public aizsargs(String name, int age, int height, int rating, boolean block){
        super(name, age, height, rating);
        this.block = block;
    }
    public void move(){
        if(block){
            System.out.println(name + " blocks the ball!");
        } else {
            System.out.println(name + " cannot block");
        }
    }
    @Override
    public void print(){
        System.out.println(name + " defenders age: " + age + " defenders height: " + height + " rate: " + rating);
    }
}