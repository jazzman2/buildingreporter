package sk.jazzman.buildingreporter.domain.measurement;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class MUnit {

	@Id
    @NotNull
    @Column(unique = true)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String name_en;

    @NotNull
    private String symbol;

    @NotNull
    private Integer multiple;
}
