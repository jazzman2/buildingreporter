package sk.jazzman.buildingreporter.domain.aparature;

/**
 * Aparature
 * 
 * @author jkovalci
 * 
 */
public class AAparature implements AAparatureInf {
	/** Serial id */
	private static final long serialVersionUID = 1L;
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
