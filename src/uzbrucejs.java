public class uzbrucejs extends speletajs {
    boolean score;

    public uzbrucejs(String name, int age, int height, int rating, boolean score){
        super(name, age, height, rating);
        this.score = score;
    }
    public void move(){
        if(score){
            System.out.println(name + " scores a goal!");
        } else {
            System.out.println(name + " cannot score");
        }
    }
    @Override
    public void print(){
        System.out.println(name + " attackers age: " + age + " attackers height: " + height + " rate: " + rating);
    }
}