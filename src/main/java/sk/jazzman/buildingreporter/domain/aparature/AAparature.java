package sk.jazzman.buildingreporter.domain.aparature;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import sk.jazzman.buildingreporter.domain.building.BPart;
import sk.jazzman.buildingreporter.domain.measurement.MLog;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class AAparature {

    @NotNull
    @Column(unique = true)
    @Id
    private Number id;

    @NotNull
    @Column(unique = true)
    @Size(min = 2)
    private String name;

    @NotNull
    @ManyToOne
    private BPart part;

    
}
