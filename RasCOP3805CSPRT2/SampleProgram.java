public class SampleProgram {
    
    public static void main(String[] args) {
        System.out.println("Starting...");
        
        for (int i=0; i<1000000; i++) {
            innerLoop(i);
        }

        System.out.println("Finished.");
    }

    public static void innerLoop(int i){
        for (int j = 0; j < 10000; j++) {
            System.out.println("i=" + i + ", j=" + j);
            delay();
        }
    }
    
    //Change the value of the delay loop as needed
    //to match your computer's speed.
    public static void delay(){
        for (int k = 0; k < 1000; k++)
            ; //this is the empty statement which is covered later
    }
}
