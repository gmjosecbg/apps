package pojo;
// Generated 15-feb-2016 11:57:27 by Hibernate Tools 4.3.1



/**
 * Direccion generated by hbm2java
 */
public class Direccion  implements java.io.Serializable {


     private Integer idEmail;
     private Persona persona;
     private String direccion;

    public Direccion() {
    }

	
    public Direccion(Persona persona) {
        this.persona = persona;
    }
    public Direccion(Persona persona, String direccion) {
       this.persona = persona;
       this.direccion = direccion;
    }
   
    public Integer getIdEmail() {
        return this.idEmail;
    }
    
    public void setIdEmail(Integer idEmail) {
        this.idEmail = idEmail;
    }
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String toString(){
        return "\n"+this.getDireccion();
    }


}


