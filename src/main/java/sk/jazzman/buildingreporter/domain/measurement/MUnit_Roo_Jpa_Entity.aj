// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sk.jazzman.buildingreporter.domain.measurement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;
import sk.jazzman.buildingreporter.domain.measurement.MUnit;

privileged aspect MUnit_Roo_Jpa_Entity {
    
    declare @type: MUnit: @Entity;
    
    @Version
    @Column(name = "version")
    private Integer MUnit.version;
    
    public Integer MUnit.getVersion() {
        return this.version;
    }
    
    public void MUnit.setVersion(Integer version) {
        this.version = version;
    }
    
}
