package sk.jazzman.buildingreporter.domain.measurement;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import sk.jazzman.buildingreporter.domain.instrument.Instrument;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class MLog {

    @NotNull
    @Column(unique = true)
    private Long id;

    @NotNull
    private Long valueMeasured;

    private Long valueTransformed;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date logDate;

    @ManyToOne
    private MUnit unitTransformed;

    @NotNull
    @ManyToOne
    private MUnit unitMeasured;

    @NotNull
    @ManyToOne
    private Instrument aparature;
}