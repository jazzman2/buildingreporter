package sk.jazzman.buildingreporter.repository.measurement;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sk.jazzman.buildingreporter.domain.measurement.MLog;

import java.util.Date;
import java.util.List;

/**
 * Created by jkovalcik on 11.11.2016.
 */
@RepositoryRestResource(collectionResourceRel = "mlog", path = "mlog")
public interface MLogRepository extends CrudRepository<MLog, Long>, JpaSpecificationExecutor<MLog> {
	
	@Query(value = "select distinct l from Mlog l where l.itemId=:itemId and l.logDate<= (:date + HOUR) and l.logDate >=:date order by l.logDate")
	List<MLog> findLogsByHour(@Param("date") Date date, @Param("itemId") Long itemId);
	
	@Query(value = "select distinct l from Mlog l where l.itemId=:itemId and l.logDate >=:date order by l.logDate")
	List<MLog> findLogsGreaterThenDate(@Param("date") Date date, @Param("itemId") Long itemId);
	
}
