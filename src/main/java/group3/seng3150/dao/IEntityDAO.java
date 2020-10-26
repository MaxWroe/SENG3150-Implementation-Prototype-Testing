/**Interface object that specifies the functionality that must be implemented in a Entity database access object**/
package group3.seng3150.dao;

import java.util.List;
import java.util.Map;

public interface IEntityDAO<EntityType, KeyType> {

    void create(EntityType entity);

    List<EntityType> findAll(int page, int length);
    EntityType findById(KeyType id);

    List<EntityType> queryAll(String query, Map<String, Object> params, int page, int length);
    EntityType queryOne(String query, Map<String, Object> params);

    EntityType update(EntityType entity);

    void delete(EntityType entity);
    void deleteById(KeyType key);
}
