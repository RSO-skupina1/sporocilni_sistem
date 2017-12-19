package entity;


import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "sporocila")
@Table(name = "sporocila")
@UuidGenerator(name = "idGenerator")
public class Sporocilo {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String vsebina;

    private Date poslano;

    @Column(name = "posiljatelj_id")
    private String posiljateljId;

    @Column(name = "prejemnik_id")
    private String prejemnikId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVsebina() {
        return vsebina;
    }

    public void setVsebina(String vsebina) {
        this.vsebina = vsebina;
    }

    public Date getPoslano() {
        return poslano;
    }

    public void setPoslano(Date poslano) {
        this.poslano = poslano;
    }

    public String getPosiljateljId() {
        return posiljateljId;
    }

    public void setPosiljateljId(String posiljateljId) {
        this.posiljateljId = posiljateljId;
    }

    public String getPrejemnikId() {
        return prejemnikId;
    }

    public void setPrejemnikId(String prejemnikId) {
        this.prejemnikId = prejemnikId;
    }
}
