/**
 * 
 */
package sk.jazzman.brmi.domain.measurement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Measured Log
 * 
 * @author jano
 * 
 */
@Entity
@Table(name = "mlog_not_send")
@SequenceGenerator(name = "SEQ", sequenceName = "mlog_not_send_seq")
public class MLogNotSend extends MAEntityAbt {
	/** serial id */
	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "mlog")
	@Id
	private Long mlog;

	public Long getMlog() {
		return mlog;
	}

	public void setMlog(Long mlog) {
		this.mlog = mlog;
	}
}
