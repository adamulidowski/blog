package adam.dao.core;

import adam.dao.api.GenericDAO;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    protected Class<? extends T> daoType;

    @Resource
    SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public void save(T object) {
        sessionFactory.getCurrentSession().save(object);
    }

    @Override
    public void update(T object) {
        sessionFactory.getCurrentSession().saveOrUpdate(object);
    }

    @Override
    public void delete(T object) {
        sessionFactory.getCurrentSession().delete(object);
    }

    @Override
    public List<T> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(daoType).list();
    }

    @Override
    public T getById(ID id) {
        return (T) sessionFactory.getCurrentSession().get(daoType, (Serializable) id);
    }

    @Override
    public List<String> getAllToString() {
        List<String> listOfString = new ArrayList<String>();
        for (Object value : sessionFactory.getCurrentSession().createCriteria(daoType).list()) {
            listOfString.add(value.toString());
        }
        return listOfString;
    }
}
