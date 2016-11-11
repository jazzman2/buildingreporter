package sk.jazzman.buildingreporter.repository.building;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import sk.jazzman.buildingreporter.domain.building.BPart;

/**
 * Created by jkovalcik on 11.11.2016.
 */
public interface BPartRepository extends CrudRepository<BPart, Long>, JpaSpecificationExecutor<BPart> {
}
