// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sk.jazzman.buildingreporter.domain.building;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import sk.jazzman.buildingreporter.domain.building.BPart;

privileged aspect BPart_Roo_Jpa_Entity {
    
    declare @type: BPart: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_")
    private Long BPart.id_;
    
    @Version
    @Column(name = "version")
    private Integer BPart.version;
    
    public Long BPart.getId_() {
        return this.id_;
    }
    
    public void BPart.setId_(Long id) {
        this.id_ = id;
    }
    
    public Integer BPart.getVersion() {
        return this.version;
    }
    
    public void BPart.setVersion(Integer version) {
        this.version = version;
    }
    
}