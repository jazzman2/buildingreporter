// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sk.jazzman.buildingreporter.domain.instrument;

import sk.jazzman.buildingreporter.domain.building.BPart;
import sk.jazzman.buildingreporter.domain.instrument.Instrument;

privileged aspect Instrument_Roo_JavaBean {
    
    public String Instrument.getName() {
        return this.name;
    }
    
    public void Instrument.setName(String name) {
        this.name = name;
    }
    
    public BPart Instrument.getPart() {
        return this.part;
    }
    
    public void Instrument.setPart(BPart part) {
        this.part = part;
    }
    
    public Long Instrument.getId() {
        return this.id;
    }
    
    public void Instrument.setId(Long id) {
        this.id = id;
    }
    
}
