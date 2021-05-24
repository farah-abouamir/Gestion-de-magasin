package GestionProduit;

import java.util.List;

public interface IDao <T>{
    public T find(long id);
    public void create(T obj);
    public void delete(long id) ;
    public void update(T obj);
    public List<T> findAll();

}
