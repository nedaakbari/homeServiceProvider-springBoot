package ir.maktab.homeserviceprovider.service.interfaces;

import java.util.List;

//@Service
public interface Services<T, G, ID> {
    public void save(T t);

    public void delete(T t);

    public List<G> getAll();

    public T getById(ID theId);
}
