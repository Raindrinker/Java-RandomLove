import java.util.ArrayList;

/**
 * Created by Raindrinker on 27/02/2015.
 */

/**
 * Group of Weighted objects from which to take or select one of them randomly. All objects have a chance to be picked which is proportional to their weight.
 * Is Weighted itself.
 * @param <T> Type of objects in the pool. Must implement Weighted.
 */
public class WeightedPool<T extends Weighted> extends Pool<T> implements Weighted{

    /**
     * Weight value of the WeightedPool itself, so it can be added to another WeightedPool.
     */
    private double r_Weight;

    /**
     * Constructor. Creates an empty WeightedPool with the given weight value
     * @param d
     */
    public WeightedPool(Double d) {
        r_Weight = d;
    }

    /**
     * Add another Pool to this one. Will only add the objects that are Weighted
     * @param p Pool to add to this one
     */
    @Override
    public void addPool(Pool<T> p){
        for(T r: p.pool){
            if(r instanceof Weighted) {
                pool.add(r);
            }
        }
    }

    /**
     * Randomly return one of the objects in the pool without removing it. The chance of every object to be selected is proportional to its weight.
     * @return selected object
     */
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

    /**
     * Randomly return one of the objects in the pool, then remove it from the pool
     * @return taken object
     */
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

    /**
     * Get the weight of the WeightedPool
     * @return Weight of the WeightedPool
     */
    @Override
    public double getRandomWeight(){
        return r_Weight;
    }

    /**
     * Set the weight of the WeightedPool
     * @param d weight value to set
     */
    public void setRandomWeight(double d){
        r_Weight = d;
    }
}
