package adam.dao.core;

import adam.dao.api.PostDAO;
import adam.dto.PostDTO;
import org.hibernate.criterion.Order;

import java.util.List;

public class PostDAOImpl extends GenericDAOImpl<PostDTO, Integer> implements PostDAO {

    @Override
    public List<PostDTO> getAllPostsSortedByDate() {
        return sessionFactory.getCurrentSession().createCriteria(PostDTO.class).addOrder(Order.desc("postDate")).list();
    }
}
