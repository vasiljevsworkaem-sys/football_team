public class speletajs {
    String name;
    int age;
    int height;
    int rating;

    public speletajs(String name, int age, int height, int rating){
        this.name = name;
        this.age = age;
        this.height = height;
        this.rating = rating;
    }
    public void print(){
        System.out.println(name + " age: " + age + " height: " + height + " rate: " + rating);
    }
    public void act(){
        System.out.println("I'm running");
    }

    @Override
    public String toString() {
        return name;
    }
}