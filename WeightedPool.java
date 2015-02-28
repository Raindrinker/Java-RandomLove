import java.util.ArrayList;

/**
 * Created by Ferran on 27/02/2015.
 */
public class WeightedPool<T extends Weighted> extends Pool<T> implements Weighted{

    private double r_Weight;

    public WeightedPool(Double d) {
        r_Weight = d;
    }

    @Override
    public void addPool(Pool<T> p){
        for(T r: p.pool){
            if(r instanceof Weighted) {
                pool.add(r);
            }
        }
    }

    @Override
    public T select(){

        ArrayList<Double> ranges = new ArrayList<Double>();
        double sum = 0;
        ranges.add(sum);

        for(T r: pool){
            sum += r.getRandomWeight();
            ranges.add(sum);
        }

        double selection = Math.random()*sum;

        for(int i = 1; i < ranges.size(); i++){
            if(ranges.get(i) > selection && ranges.get(i-1) < selection){
                return (pool.get(i-1));
            }
        }
        throw new EmptyPoolException();
    }

    @Override
    public T take(){
        ArrayList<Double> ranges = new ArrayList<Double>();
        double sum = 0;
        ranges.add(sum);

        for(T r: pool){
            sum += r.getRandomWeight();
            ranges.add(sum);
        }

        double selection = Math.random()*sum;

        for(int i = 1; i < ranges.size(); i++){
            if(ranges.get(i) > selection && ranges.get(i-1) < selection){
                T r = pool.get(i);
                pool.remove(i);
                return (r);
            }
        }
        throw new EmptyPoolException();
    }

    @Override
    public double getRandomWeight(){
        return r_Weight;
    }

    public void setRandomWeight(double d){
        r_Weight = d;
    }
}
