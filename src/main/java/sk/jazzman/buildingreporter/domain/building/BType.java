package sk.jazzman.buildingreporter.domain.building;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class BType {

    /**
     */
    @NotNull
    @Column(unique = true)
    private Long id;

    /**
     */
    @NotNull
    @Size(min = 2)
    private String name;
}
