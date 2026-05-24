public class pussargs extends aizsargs {
    boolean score;

    public pussargs(String name, int age, int height, int rating, boolean block, boolean score){
        super(name, age, height, rating, block);
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
        System.out.println(name + " midfielders age: " + age + " midfielders height: " + height + " rate: " + rating);
    }
}