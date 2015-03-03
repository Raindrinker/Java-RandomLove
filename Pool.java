import java.util.ArrayList;

/**
 * Created by Raindrinker on 27/02/2015.
 */

/**
 * Group of objects from which to take or select one of them randomly. All objects have the same chance to be taken or selected.
 * @param <T> Type of the objects in the Pool. Not restricted, can be Object.
 */
public class Pool<T>{

    protected ArrayList<T> pool = new ArrayList<T>();

    /**
     * Constructor. Creates an empty Pool
     */
    public Pool() {
    }

    /**
     * Add an object to the pool
     * @param r object to add
     */
    public void add(T r){
        pool.add(r);
    }

    /**
     * Add aa ArrayList of objects to the pool
     * @param list ArrayList of objects
     */
    public void addList(ArrayList<T> list){
        for(T r: list){
            pool.add(r);
        }
    }

    /**
     * Add another Pool to this one
     * @param p Pool to add to this one
     */
    public void addPool(Pool<T> p){
        for(T r: p.pool){
            pool.add(r);
        }
    }

    /**
     * Randomly return one of the objects in the pool without removing it
     * @return selected object
     */
    public T select(){
        if(pool.size() > 0) {
            int selection = (int)(Math.random() * pool.size());
            return pool.get(selection);
        }
        throw new EmptyPoolException();
    }

    /**
     * Randomly return one of the objects in the pool, then remove it from the pool
     * @return taken object
     */
    public T take(){
        if(pool.size() > 0) {
            int selection = (int)(Math.random() * pool.size());
            T r = pool.get(selection);
            pool.remove(selection);
            return (r);
        }
        throw new EmptyPoolException();

    }
}
