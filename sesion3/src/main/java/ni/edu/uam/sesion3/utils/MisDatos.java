package ni.edu.uam.sesion3.utils;

public class MisDatos {
    private int[] numbers = new int[10];
    private int pos = 0;


    public void add(int number){
        numbers[pos] = number;
        pos++;
    }

    public int getMax(){
        int max = numbers[0];
        for(int i = 1; i < pos; i++){
            if(numbers[i] > max){
                max = numbers[i];
            }
        }
        return max;
    }

    public int getMin(){
        int min = numbers[0];
        for(int i = 1; i < pos; i++){
            if(numbers[i] < min){
                min = numbers[i];
            }
        }
        return min;
    }
}
