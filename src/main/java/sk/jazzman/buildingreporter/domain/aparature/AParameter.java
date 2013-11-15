package sk.jazzman.buildingreporter.domain.aparature;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class AParameter {

    @NotNull
    @Column(unique = true)
    private Long id;

    @NotNull
    private String paramName;

    private String paramValue;

    @NotNull
    @ManyToOne
    private AAparature aparature;
}
