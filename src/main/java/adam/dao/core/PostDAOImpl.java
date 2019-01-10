package adam.dao.core;

import adam.dao.api.PostDAO;
import adam.dto.PostDTO;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostDAOImpl extends GenericDAOImpl<PostDTO, Integer> implements PostDAO {

    @Override
    public List<PostDTO> getAllPostsSortedByDate(String type) {
        return sessionFactory.getCurrentSession().createCriteria(PostDTO.class).
                add(Restrictions.eq("type", type)).addOrder(Order.desc("postDate")).list();
    }
}
