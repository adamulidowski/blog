package adam.dao.api;

import adam.dto.PostDTO;

import java.util.List;

public interface PostDAO extends GenericDAO<PostDTO, Integer> {

    List <PostDTO> getAllPostsSortedByDate(String type);
}
