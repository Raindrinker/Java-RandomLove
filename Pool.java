import java.util.ArrayList;

/**
 * Created by Ferran on 27/02/2015.
 */
public class Pool<T>{

    protected ArrayList<T> pool = new ArrayList<T>();

    public Pool() {
    }

    public void add(T r){
        pool.add(r);
    }

    public void addList(ArrayList<T> list){
        for(T r: list){
            pool.add(r);
        }
    }

    public void addPool(Pool<T> p){
        for(T r: p.pool){
            pool.add(r);
        }
    }

    public T select(){
        if(pool.size() > 0) {
            int selection = (int)(Math.random() * pool.size());
            return pool.get(selection);
        }
        throw new EmptyPoolException();
    }

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
