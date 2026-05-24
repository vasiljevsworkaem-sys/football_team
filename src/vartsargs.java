public class vartsargs extends speletajs {
    boolean defend;

    public vartsargs(String name, int age, int height, int rating, boolean defend){
        super(name, age, height, rating);
        this.defend = defend;
    }
    public void move(){
        if(defend){
            System.out.println(name + " defends the net!");
        } else {
            System.out.println(name + " cannot defend the net");
        }
    }
    @Override
    public void print(){
        System.out.println(name + " goalkeepers age: " + age + " goalkeepers height: " + height + " rate: " + rating);
    }
}